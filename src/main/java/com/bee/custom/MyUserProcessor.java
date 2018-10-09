package com.bee.custom;

import com.bee.ApplicationLayerMsg;

public class MyUserProcessor implements UserProcessor{

    @Override
    public Object process(ApplicationLayerMsg msg) {
        return "hello";
    }
}
