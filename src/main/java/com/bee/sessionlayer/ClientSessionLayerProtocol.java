package com.bee.sessionlayer;

import com.bee.*;
import com.bee.transportlayer.ClientTransporter;
import com.bee.transportlayer.InvokeContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicLong;

public class ClientSessionLayerProtocol implements SessionProtocol {
    private static final Logger logger = LoggerFactory.getLogger(ClientSessionLayerProtocol.class);

    private static final AtomicLong requestId = new AtomicLong(0);
    private ClientTransporter clientTransporter;

    public ClientSessionLayerProtocol(String ip, int port) {
        this.clientTransporter = new ClientTransporter(ip,port,new SessionLayerCallbacker() {
            @Override
            public void callback(SessionLayerMsg sessionLayerMsg, InvokeContext invokeContext) {
                acceptInvoke(sessionLayerMsg,invokeContext);
            }
        });
    }

    public void start() throws InterruptedException {
        clientTransporter.start();
    }

    @Override
    public void startHeartBeat() {

    }


    @Override
    public void oneway(ApplicationLayerMsg appllicationRequest, InvokeContext invokeContext) {
        SessionLayerMsg sessionLayerMsg = new SessionLayerMsg();
        sessionLayerMsg.setMsg(appllicationRequest);
        sessionLayerMsg.setMsgType(MessageType.REQUEST.messageType);
        sessionLayerMsg.setRequestId(requestId.getAndIncrement());
        this.clientTransporter.send(sessionLayerMsg,null);
    }

    @Override
    public Object sendSyn(ApplicationLayerMsg appllicationRequest, InvokeContext invokeContext) {
        //生成传输层协议对象
        SessionLayerMsg sessionLayerMsg = new SessionLayerMsg();
        sessionLayerMsg.setMsg(appllicationRequest);
        sessionLayerMsg.setMsgType(MessageType.REQUEST.messageType);
        sessionLayerMsg.setRequestId(requestId.getAndIncrement());

        return send(sessionLayerMsg).get();
    }

    private SimpleResponseFuture send(SessionLayerMsg sessionLayerMsg) {
        this.clientTransporter.send(sessionLayerMsg,null);
        SimpleResponseFuture future = new SimpleResponseFuture(sessionLayerMsg.getRequestId());
        return future;
    }

    @Override
    public Object sendWithFuture(ApplicationLayerMsg appllicationRequest, InvokeContext invokeContext) {
        return null;
    }

    @Override
    public Object sendWithCallback(ApplicationLayerMsg appllicationRequest, InvokeContext invokeContext) {
        return null;
    }

    @Override
    public void acceptInvoke(SessionLayerMsg sessionLayerMsg,InvokeContext invokeContext) {
        if(MessageType.HEARTBEAT.messageType == sessionLayerMsg.getMsgType()) {
            invokeContext.getChannel().write(SessionLayerMsgUtil.newHeartbeatRspMsg(sessionLayerMsg.getRequestId()));
        } else if(MessageType.HEARTBEAT_RSP.messageType == sessionLayerMsg.getMsgType()) {
            //处理心跳响应包
        } else if(MessageType.RESPONSE.messageType == sessionLayerMsg.getMsgType()) {
            SimpleResponseFuture.handleReceived(sessionLayerMsg);
        } else {
            logger.error("Unknown message");
        }
    }
}
