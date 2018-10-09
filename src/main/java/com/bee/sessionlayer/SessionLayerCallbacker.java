package com.bee.sessionlayer;

import com.bee.transportlayer.InvokeContext;

public interface SessionLayerCallbacker {
    void callback(SessionLayerMsg sessionLayerMsg, InvokeContext invokeContext);
}
