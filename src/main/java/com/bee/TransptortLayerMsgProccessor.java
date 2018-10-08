package com.bee;

public interface TransptortLayerMsgProccessor<R> {

    void handleMsg(ApplicationLayerMsg msg,InvokeContext invokeContext);
}
