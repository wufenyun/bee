package com.bee.transportlayer;

import com.alibaba.fastjson.JSON;
import com.bee.sessionlayer.SessionLayerCallbacker;
import com.bee.sessionlayer.SessionLayerMsg;
import com.bee.sessionlayer.ClientSessionLayerProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientTransportHandler extends ChannelInboundHandlerAdapter {

    private SessionLayerCallbacker sessionLayerCallbacker;

    public ClientTransportHandler(SessionLayerCallbacker sessionLayerCallbacker) {
        this.sessionLayerCallbacker = sessionLayerCallbacker;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        System.out.println("接收到服务端消息：" + JSON.toJSONString(msg));
        SessionLayerMsg sessionLayerMsg = (SessionLayerMsg)msg;

        DefaultInvokeContext invokeContext = new DefaultInvokeContext();
        invokeContext.setChannel(ctx.channel());
        //交由会话层处理
        sessionLayerCallbacker.callback(sessionLayerMsg,invokeContext);
    }
}
