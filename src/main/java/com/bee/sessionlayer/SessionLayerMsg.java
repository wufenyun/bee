package com.bee.sessionlayer;

import com.bee.ApplicationLayerMsg;

import java.io.Serializable;

public class SessionLayerMsg implements Serializable {

    private long requestId;
    //心跳、请求数据，响应数据
    private int msgType;
    private int invokeWay;

    private ApplicationLayerMsg msg;

    public int getInvokeWay() {
        return invokeWay;
    }

    public void setInvokeWay(int invokeWay) {
        this.invokeWay = invokeWay;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public ApplicationLayerMsg getMsg() {
        return msg;
    }

    public void setMsg(ApplicationLayerMsg msg) {
        this.msg = msg;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }
}
