package com.bee.sessionlayer;

import com.bee.*;
import com.bee.custom.MyUserProcessor;
import com.bee.custom.UserProcessor;
import com.bee.transportlayer.InvokeContext;
import com.bee.transportlayer.ServerTransporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicLong;

public class ServerSessionLayerProtocol implements SessionProtocol {
    private static final Logger logger = LoggerFactory.getLogger(ServerSessionLayerProtocol.class);

    private final UserProcessor userProcessor = new MyUserProcessor();
    private AtomicLong requestId;
    private ServerTransporter serverTransporter;
    private ExecutorService executorService;

    public ServerSessionLayerProtocol(int port) {
        serverTransporter = new ServerTransporter(port, new SessionLayerCallbacker() {
            @Override
            public void callback(SessionLayerMsg sessionLayerMsg, InvokeContext invokeContext) {
                acceptInvoke(sessionLayerMsg,invokeContext);
            }
        });
    }

    @Override
    public void start() throws InterruptedException {
        serverTransporter.start();
    }

    @Override
    public void startHeartBeat() {

    }

    @Override
    public void oneway(ApplicationLayerMsg applicationRequest, InvokeContext invokeContext) {

    }

    @Override
    public Object sendSyn(ApplicationLayerMsg applicationRequest, InvokeContext invokeContext) {
        return null;
    }

    @Override
    public Object sendWithFuture(ApplicationLayerMsg applicationRequest, InvokeContext invokeContext) {
        return null;
    }

    @Override
    public Object sendWithCallback(ApplicationLayerMsg applicationRequest, InvokeContext invokeContext) {
        return null;
    }

    @Override
    public void acceptInvoke(SessionLayerMsg sessionLayerMsg, InvokeContext invokeContext) {
        if(MessageType.HEARTBEAT.messageType == sessionLayerMsg.getMsgType()) {
            invokeContext.getChannel().write(SessionLayerMsgUtil.newHeartbeatRspMsg(sessionLayerMsg.getRequestId()));
        } else if(MessageType.HEARTBEAT_RSP.messageType == sessionLayerMsg.getMsgType()) {
            //处理心跳响应包
        } else if(MessageType.REQUEST.messageType == sessionLayerMsg.getMsgType()) {
            ApplicationLayerMsg am = sessionLayerMsg.getMsg();
            Object result = userProcessor.process(am);
            serverTransporter.send(SessionLayerMsgUtil.newRspMsg(sessionLayerMsg.getRequestId(),result),invokeContext.getChannel());
        } else {
            logger.error("Unknown message");
        }
    }
}
