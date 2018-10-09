package com.bee.transport;

import com.bee.ApplicationLayerMsg;
import com.bee.sessionlayer.ClientSessionLayerProtocol;
import org.junit.Test;

public class ClientSessionLayerProtocolDemo extends TestBase {
    private String ip = "localhost";
    private int port = 8888;
    private ClientSessionLayerProtocol clientSessionLayerProtocol = new ClientSessionLayerProtocol(ip,port);

    @Test
    public void start() throws InterruptedException {
        clientSessionLayerProtocol.start();

        ApplicationLayerMsg appllicationRequest = new ApplicationLayerMsg();
        print(clientSessionLayerProtocol.sendSyn(appllicationRequest,null));
    }
}
