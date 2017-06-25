package client;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by ejiang on 2017-06-23.
 */
public class InitTcpClient implements SocketIOCallback {
    private String lcoalIp = "192.168.253.2";
    private int prot = 6033;

    public InitTcpClient() {
        LClient lClient = new TcpClient(this);
        lClient.connect(lcoalIp, prot); //初始化连接
        try {
            lClient.sendFile("L:\\001\\Baptiste Giabiconi\\New York - Baptiste Giabiconi.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConnect(LClient transceiver) {

    }

    @Override
    public void onDisconnect() {

    }

    @Override
    public void onConnectFailed(Exception ex) {

    }

    @Override
    public void onReceive(String data) {

    }
}
