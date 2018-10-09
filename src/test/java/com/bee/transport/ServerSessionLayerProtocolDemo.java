package com.bee.transport;

import com.bee.sessionlayer.ServerSessionLayerProtocol;
import com.bee.transportlayer.ServerTransporter;
import org.junit.Test;

public class ServerSessionLayerProtocolDemo  extends TestBase {

    private ServerSessionLayerProtocol serverSessionLayerProtocol = new ServerSessionLayerProtocol(8888);

    @Test
    public void startServer() {
        try {
            serverSessionLayerProtocol.start();
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
