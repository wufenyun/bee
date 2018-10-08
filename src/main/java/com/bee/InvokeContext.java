package com.bee;

import io.netty.channel.Channel;

public interface InvokeContext {

    Class getApplicationLayerResultClass();

    Channel getChannel();
}
