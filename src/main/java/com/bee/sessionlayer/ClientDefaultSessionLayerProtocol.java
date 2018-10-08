package com.bee.sessionlayer;

import com.bee.*;

import java.util.concurrent.atomic.AtomicLong;

public class ClientDefaultSessionLayerProtocol implements SessionProtocol {

    private static final AtomicLong requestId = new AtomicLong(0);
    private ClientTransporter clientTransporter;

    public ClientDefaultSessionLayerProtocol(String ip,int port) {
        this.clientTransporter = new ClientTransporter(ip,port);
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
        sessionLayerMsg.setBodyLen(10);
        this.clientTransporter.write(sessionLayerMsg);
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
        this.clientTransporter.write(sessionLayerMsg);
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
}
