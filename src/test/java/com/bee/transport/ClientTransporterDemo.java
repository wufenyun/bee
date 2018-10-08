package com.bee.transport;

import com.bee.ApplicationLayerMsg;
import com.bee.ClientTransporter;
import com.bee.util.TestBase;
import org.junit.Test;

public class ClientTransporterDemo extends TestBase {

    private String ip = "localhost";
    private int port = 8888;
    private ClientTransporter clientTransporter = new ClientTransporter(ip,port);

    @Test
    public void start() throws InterruptedException {
        clientTransporter.start();

        ApplicationLayerMsg appllicationRequest = new ApplicationLayerMsg();
        //clientTransporter.oneway(appllicationRequest,null);
       // print(clientTransporter.sendSyn(appllicationRequest,null));
        Thread.sleep(100000);
    }
}
