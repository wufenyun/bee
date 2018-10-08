package com.bee;

/**
 * 应用层操作：
 * 封装应用层请求，然后调用传输层发送信息；
 * 应用层不负责连接健康监测；
 * 应用层请求对象封装由用户自定义，比如rpc请求则包含接口名，入参；
 *
 */
public interface Server {
}
