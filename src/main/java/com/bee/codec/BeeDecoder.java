package com.bee.codec;

import com.bee.serialize.HessianSerializer;
import com.bee.sessionlayer.SessionLayerMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class BeeDecoder extends ByteToMessageDecoder {

    private final static int HEADER_LENGTH = 4;
    private final HessianSerializer hessianSerializer = new HessianSerializer();

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        int readable = in.readableBytes();
        if(readable < HEADER_LENGTH) {
            return;
        }

        int bodyLen = in.readInt();
        if(readable < bodyLen) {
            in.readerIndex(0);
            return;
        }

        byte[] temp = new byte[bodyLen];
        in.readBytes(temp);

        SessionLayerMsg msg = hessianSerializer.deserialize(temp);
        out.add(msg);
    }
}
