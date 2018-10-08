package com.bee.custom;

import com.bee.ApplicationLayerMsg;

public interface UserProcessor<T> {

    void process(ApplicationLayerMsg msg);
}
