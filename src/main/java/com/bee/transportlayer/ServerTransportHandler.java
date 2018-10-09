package com.bee.transportlayer;

import com.alibaba.fastjson.JSON;
import com.bee.sessionlayer.SessionLayerCallbacker;
import com.bee.sessionlayer.SessionLayerMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerTransportHandler extends ChannelInboundHandlerAdapter {

    private SessionLayerCallbacker sessionLayerCallbacker;

    public ServerTransportHandler(SessionLayerCallbacker sessionLayerCallbacker) {
        this.sessionLayerCallbacker = sessionLayerCallbacker;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        System.out.println("接收到客户端消息：" + JSON.toJSONString(msg));

        DefaultInvokeContext invokeContext = new DefaultInvokeContext();
        invokeContext.setChannel(ctx.channel());
        //交由应用层处理
        sessionLayerCallbacker.callback((SessionLayerMsg)msg,invokeContext);
    }

}
