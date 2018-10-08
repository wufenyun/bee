package com.bee.codec;

import com.bee.SessionLayerMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class BeeEncoder extends MessageToByteEncoder<SessionLayerMsg> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, SessionLayerMsg transportLayerMsg, ByteBuf byteBuf) throws Exception {
        if(null == transportLayerMsg) {
            return;
        }

        byteBuf.writeInt(transportLayerMsg.getBodyLen());
        byteBuf.writeLong(transportLayerMsg.getRequestId());
        //byteBuf.writeInt(transportLayerMsg.getBodyLen());
    }
}
