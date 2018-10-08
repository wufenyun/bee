package com.bee.sessionlayer;

import com.bee.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicLong;

public class ServerDefaultSessionLayerProtocol implements ServerSessionProtocol {

    private AtomicLong requestId;
    private ServerTransporter serverTransporter;
    private ExecutorService executorService;

    @Override
    public void start() throws InterruptedException {

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
    public void acceptInvoke(SessionLayerMsg sessionLayerMsg) {
        ApplicationLayerMsg am = sessionLayerMsg.getMsg();
        executorService.submit(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
