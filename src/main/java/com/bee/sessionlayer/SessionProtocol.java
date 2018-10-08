package com.bee.sessionlayer;

import com.bee.ApplicationLayerMsg;
import com.bee.InvokeContext;

public interface SessionProtocol {

    void start() throws InterruptedException;

    void startHeartBeat();

    public void oneway(ApplicationLayerMsg applicationRequest, InvokeContext invokeContext);

    public Object sendSyn(ApplicationLayerMsg applicationRequest, InvokeContext invokeContext);

    public Object sendWithFuture(ApplicationLayerMsg applicationRequest, InvokeContext invokeContext);

    public Object sendWithCallback(ApplicationLayerMsg applicationRequest, InvokeContext invokeContext) ;

}
