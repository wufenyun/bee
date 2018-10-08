package com.bee;

public class SessionLayerMsg {

    private int headerLen;
    private int invokeWay;
    //心跳、请求数据，响应数据
    private int msgType;
    private long requestId;

    private int bodyLen;
    private ApplicationLayerMsg msg;

    public int getHeaderLen() {
        return headerLen;
    }

    public void setHeaderLen(int headerLen) {
        this.headerLen = headerLen;
    }

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

    public int getBodyLen() {
        return bodyLen;
    }

    public void setBodyLen(int bodyLen) {
        this.bodyLen = bodyLen;
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
