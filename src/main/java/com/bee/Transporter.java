package com.bee;

/**
 * 传输层操作：
 * 传输应用层信息，比如rpc请求；
 * 接收网络信息并封装为应用层信息；
 * 负责序列化、反序列化；
 * 负责网络健康监测以及故障修复；
 *
 */
public interface Transporter {

    void start() throws InterruptedException;

    void stop();

    void write(SessionLayerMsg msg);

}
