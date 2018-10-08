package com.bee.transport;

import com.bee.ServerTransporter;
import com.bee.util.TestBase;
import org.junit.Test;

public class ServerTransporterDemo extends TestBase {

    private ServerTransporter serverTransporter = new ServerTransporter(8888);

    @Test
    public void startServer() {
        try {
            serverTransporter.start();
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
