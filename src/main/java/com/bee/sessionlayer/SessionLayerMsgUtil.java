package com.bee.sessionlayer;

import com.bee.ApplicationLayerMsg;
import com.bee.MessageType;

public class SessionLayerMsgUtil {

    public static SessionLayerMsg newHeartbeatRspMsg(long requestId) {
        SessionLayerMsg sessionLayerMsg = new SessionLayerMsg();
        sessionLayerMsg.setMsgType(MessageType.HEARTBEAT_RSP.messageType);
        sessionLayerMsg.setRequestId(requestId);
        return sessionLayerMsg;
    }

    public static SessionLayerMsg newRspMsg(long requestId,Object result) {
        SessionLayerMsg sessionLayerMsg = new SessionLayerMsg();
        sessionLayerMsg.setMsgType(MessageType.RESPONSE.messageType);
        sessionLayerMsg.setRequestId(requestId);

        ApplicationLayerMsg applicationLayerMsg = new ApplicationLayerMsg();
        applicationLayerMsg.setData(result);
        sessionLayerMsg.setMsg(applicationLayerMsg);
        return sessionLayerMsg;
    }
}
