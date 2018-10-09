package com.bee.transportlayer;

import com.bee.sessionlayer.SessionLayerCallbacker;
import com.bee.sessionlayer.SessionLayerMsg;
import com.bee.codec.BeeDecoder;
import com.bee.codec.BeeEncoder;
import com.bee.util.NamedThreadFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ServerTransporter implements Transporter {

    private final static Logger logger = LoggerFactory.getLogger(ServerTransporter.class);

    private ServerBootstrap serverBootstrap;
    private NioEventLoopGroup boss = new NioEventLoopGroup(1, new NamedThreadFactory("bee_boss_thread"));
    private NioEventLoopGroup worker = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors()*2, new NamedThreadFactory("bee_worker_thread"));
    private int port;
    private ChannelFuture channelFuture;
    //心跳检测时用到
    private List<Channel> currentChannels;

    public ServerTransporter(int port, SessionLayerCallbacker sessionLayerCallbacker) {
        this.port = port;
        serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boss,worker)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new BeeEncoder())
                                .addLast(new BeeDecoder())
                                .addLast(new ServerTransportHandler(sessionLayerCallbacker));
                    }
                });
    }

    @Override
    public void start() throws InterruptedException {
        channelFuture = serverBootstrap.bind(port).sync();
        logger.debug("本地端口:{}已开启网络监听服务",port);
    }

    @Override
    public void stop() {
        if(null != channelFuture) {
            channelFuture.channel();
            logger.debug("本地端口:{}已关闭网络监听服务",port);
        }
    }

    @Override
    public void send(SessionLayerMsg msg, Channel channel) {
        channel.writeAndFlush(msg);
    }

}
