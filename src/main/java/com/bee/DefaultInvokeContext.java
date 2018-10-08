package com.bee;

import io.netty.channel.Channel;

public class DefaultInvokeContext implements InvokeContext{

    private Channel channel;
    private Class applicationLayerResultClass;

    @Override
    public Class getApplicationLayerResultClass() {
        return applicationLayerResultClass;
    }

    @Override
    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public void setApplicationLayerResultClass(Class applicationLayerResultClass) {
        this.applicationLayerResultClass = applicationLayerResultClass;
    }
}
