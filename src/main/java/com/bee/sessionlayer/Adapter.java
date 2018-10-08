package com.bee.sessionlayer;

import com.bee.*;
import com.bee.SessionLayerMsg;

public class Adapter {

    public static void convertToApProccess(SessionLayerMsg sessionLayerMsg, InvokeContext invokeContext) {
        /*SessionLayerMsg sessionLayerMsg = new SessionLayerMsg();
        sessionLayerMsg.setMsgType(MessageType.REQUEST.messageType);
        sessionLayerMsg.setRequestId(tmsg.getRequestId());
        invokeContext.getChannel().writeAndFlush(sessionLayerMsg);*/


        int msgType = sessionLayerMsg.getMsgType();
        if(MessageType.HEARTBEAT.messageType == msgType) {

        } else if (MessageType.REQUEST.messageType == msgType) {
            //调用应用层处理并返回结果
            invokeContext.getChannel().writeAndFlush("");
        } else if (MessageType.RESPONSE.messageType == msgType) {
            SimpleResponseFuture.handleReceived(sessionLayerMsg);
        }

    }

}
