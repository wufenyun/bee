package com.bee.transportlayer;

import io.netty.channel.Channel;

public interface InvokeContext {

    Class getApplicationLayerResultClass();

    Channel getChannel();
}
