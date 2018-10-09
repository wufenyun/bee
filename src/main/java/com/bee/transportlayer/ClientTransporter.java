package com.bee.transportlayer;

import com.bee.sessionlayer.SessionLayerCallbacker;
import com.bee.sessionlayer.SessionLayerMsg;
import com.bee.codec.BeeDecoder;
import com.bee.codec.BeeEncoder;
import com.bee.util.NamedThreadFactory;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.atomic.AtomicLong;

public class ClientTransporter implements Transporter {
    private AtomicLong requestId = new AtomicLong(0);

    private Bootstrap bootstrap;
    private NioEventLoopGroup worker = new NioEventLoopGroup(1,new NamedThreadFactory("bee_worker_thread"));

    private String ip;
    private int port;
    private ChannelFuture channelFuture;
    private Channel channel;

    public ClientTransporter(String ip, int port, SessionLayerCallbacker sessionLayerCallbacker) {
        this.ip = ip;
        this.port = port;

        bootstrap = new Bootstrap();
        bootstrap.group(worker)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new BeeDecoder())
                                .addLast(new BeeEncoder())
                                .addLast(new ClientTransportHandler(sessionLayerCallbacker));
                    }
                });
    }

    @Override
    public void start() throws InterruptedException {
        channelFuture = bootstrap.connect(ip,port).sync();
        channel = channelFuture.channel();
    }

    @Override
    public void stop() {
        if(null != channelFuture) {
            channelFuture.channel().close();
        }
    }

    @Override
    public void send(SessionLayerMsg msg, Channel channel) {
        this.channel.writeAndFlush(msg);
    }


}
