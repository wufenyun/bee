package com.bee.custom;

import com.bee.ApplicationLayerMsg;

public interface UserProcessor {

    Object process(ApplicationLayerMsg msg);
}
