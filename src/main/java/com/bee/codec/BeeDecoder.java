package com.bee.codec;

import com.bee.SessionLayerMsg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class BeeDecoder extends ByteToMessageDecoder {

    //private final static int HEADER_LENGTH = 12;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        int readable = in.readableBytes();
        if(readable < 4) {
            return;
        }

        int bodyLen = in.readInt();
        /*if(readable < headerLen) {
            in.readerIndex(0);
            return;
        }*/

        long reqId = in.readLong();
        /*byte[] temp = new byte[(headerLen)];
        in.readBytes(temp);*/

        SessionLayerMsg msg = new SessionLayerMsg();
        msg.setRequestId(reqId);
        msg.setBodyLen(bodyLen);
        out.add(msg);
    }
}
