package com.bee;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientTransportHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        System.out.println("接收到服务端消息：" + JSON.toJSONString(msg));
        SessionLayerMsg tmsg = (SessionLayerMsg)msg;
        SimpleResponseFuture.handleReceived(tmsg);
    }
}
