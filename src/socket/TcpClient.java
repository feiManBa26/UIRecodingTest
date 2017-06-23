package socket;

import utils.IOUtils;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by ejiang on 2017-06-16.
 */
public class TcpClient implements LClient {
    private DataInputStream dataInputStream; //输入流
    private DataOutputStream dataOutputStream; //输出流
    private SocketIOCallback socketIOCallback;
    private Socket socket;

    public TcpClient(SocketIOCallback socketIOCallback) {
        this.socketIOCallback = socketIOCallback;
    }

    @Override
    public void connect(String ip, int port) {
        if (isConnected()) {
            disConnect();
        }
        int connectNum = 0;
        boolean isConnect = false;
        while (connectNum < 5) {
            try {
                connectNum++;
                //1.创建客户端Socket，指定服务器地址和端口
                socket = null;
                socket = new Socket("192.168.0.73", 8888);
                if (socket.isConnected()) {
                    //获取输出流
                    dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    //获取输入流
                    dataInputStream = new DataInputStream(socket.getInputStream());
                    isConnect = true;
                }
                break;
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
        if (isConnect) {
            readerServerPush();
        } else {
            System.err.println("连接失败请检查网络~");
        }

    }

    /**
     * 读取服务端推送数据流
     */
    private void readerServerPush() {
        if (socket != null) {
            synchronized (TcpClient.class) {
                if (isConnected()) {
                    try {
                        byte[] bytes = new byte[1];
                        dataInputStream.read(bytes);
                        byte[] bytes1 = new byte[Integer.parseInt(new String(bytes))];
                        dataInputStream.read(bytes1);
                        String serverPush = new String(bytes1);
                        System.out.print(serverPush);
                        socketIOCallback.onReceive(serverPush);
                    } catch (IOException e) {
                        e.printStackTrace();
                        socketIOCallback.onConnectFailed(e);
                    }
                }
            }
        }

    }

    @Override
    public void disConnect() {
        if (socket != null) {
            try {
                dataInputStream.close();
                dataOutputStream.close();
                socket.close();
                socket = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean isConnected() {
        return socket != null && socket.isConnected();
    }

    @Override
    public void send(byte[] bytes, ISendCallBack callback) {

    }

    @Override
    public void send(String strData) {

    }

    @Override
    public ConnectEnum connect() {
        return null;
    }
}
