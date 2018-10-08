package com.bee.sessionlayer;

import com.bee.SessionLayerMsg;

public interface ServerSessionProtocol extends SessionProtocol {

    void acceptInvoke(SessionLayerMsg sessionLayerMsg);
}
