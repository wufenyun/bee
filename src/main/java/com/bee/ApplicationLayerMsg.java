package com.bee;

import java.io.Serializable;

public class ApplicationLayerMsg implements Serializable {

    private String interfaceName;
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
