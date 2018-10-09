package com.bee;

public enum MessageType {

    REQUEST((byte)1),
    RESPONSE((byte)2),
    HEARTBEAT((byte)3),
    HEARTBEAT_RSP((byte)4);

    public byte messageType;
    MessageType(byte messageType) {
        this.messageType = messageType;
    }
}
