package com.bee;

import com.alibaba.fastjson.JSON;
import com.bee.sessionlayer.Adapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Map;

public class ServerTransportHandler extends ChannelInboundHandlerAdapter {

    private Map<String,Invoker> service;
    private TransptortLayerMsgProccessor transptortLayerMsgProccessor;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        System.out.println("接收到客户端消息：" + JSON.toJSONString(msg));

        DefaultInvokeContext invokeContext = new DefaultInvokeContext();
        invokeContext.setChannel(ctx.channel());
        //交由应用层处理
        Adapter.convertToApProccess((SessionLayerMsg)msg,invokeContext);
        //mockProccess(invokeContext,(TransportLayerMsg)msg);
    }

}
