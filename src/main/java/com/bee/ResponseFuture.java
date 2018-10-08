package com.bee;

public interface ResponseFuture {

    SessionLayerMsg get();

    SessionLayerMsg get(long timeoutMills);


}
