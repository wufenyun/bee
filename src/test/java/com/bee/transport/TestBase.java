package com.bee.transport;


import com.alibaba.fastjson.JSON;

public class TestBase {

    protected void print(Object object) {
        System.out.println(JSON.toJSONString(object));
    }
}
