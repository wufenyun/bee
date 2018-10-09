package com.bee.sessionlayer;

import com.bee.ApplicationLayerMsg;
import com.bee.transportlayer.InvokeContext;

public interface SessionProtocol {

    void start() throws InterruptedException;

    void startHeartBeat();

    void oneway(ApplicationLayerMsg applicationRequest, InvokeContext invokeContext);

    Object sendSyn(ApplicationLayerMsg applicationRequest, InvokeContext invokeContext);

    Object sendWithFuture(ApplicationLayerMsg applicationRequest, InvokeContext invokeContext);

    Object sendWithCallback(ApplicationLayerMsg applicationRequest, InvokeContext invokeContext) ;

    void acceptInvoke(SessionLayerMsg sessionLayerMsg, InvokeContext invokeContext);
}
