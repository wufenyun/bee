package com.bee.codec;

import com.bee.serialize.HessianSerializer;
import com.bee.sessionlayer.SessionLayerMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class BeeEncoder extends MessageToByteEncoder<SessionLayerMsg> {

    private final HessianSerializer hessianSerializer = new HessianSerializer();

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, SessionLayerMsg sessionLayerMsg, ByteBuf byteBuf) throws Exception {
        if(null == sessionLayerMsg) {
            return;
        }

        byte[] buffer = hessianSerializer.serialize(sessionLayerMsg);
        byteBuf.writeInt(buffer.length);
        byteBuf.writeBytes(buffer);
    }
}
