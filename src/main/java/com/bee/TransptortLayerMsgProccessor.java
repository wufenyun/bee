package com.bee;

import com.bee.transportlayer.InvokeContext;

public interface TransptortLayerMsgProccessor<R> {

    void handleMsg(ApplicationLayerMsg msg,InvokeContext invokeContext);
}
