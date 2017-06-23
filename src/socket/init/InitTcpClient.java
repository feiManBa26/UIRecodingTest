package socket.init;

import socket.LClient;
import socket.SocketIOCallback;
import socket.TcpClient;

/**
 * Created by ejiang on 2017-06-23.
 */
public class InitTcpClient implements SocketIOCallback {
    private String lcoalIp = "192.168.0.73";
    private int prot = 8888;

    public InitTcpClient() {
        LClient lClient = new TcpClient(this);
        lClient.connect(lcoalIp, prot); //初始化连接
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
