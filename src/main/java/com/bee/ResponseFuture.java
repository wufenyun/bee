package com.bee;

import com.bee.sessionlayer.SessionLayerMsg;

public interface ResponseFuture {

    ApplicationLayerMsg get();

    ApplicationLayerMsg get(long timeoutMills);

}
