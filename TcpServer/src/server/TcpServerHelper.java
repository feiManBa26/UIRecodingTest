package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import method.User;

/**
 * Created by ejiang on 2017-06-16.
 */
public class TcpServerHelper {

    private User mClientUser;

    public TcpServerHelper() throws IOException {
        initServerSocket();
    }

    private void initServerSocket() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        if (serverSocket == null) return;
        System.err.println("***服务器即将启动，等待客户端的连接***");
        List<User> clientUsers = new ArrayList<>();
        while (true) {
            Socket socket = serverSocket.accept();
            mClientUser = new User("User" + Math.round(Math.random() * 1000), socket);
            System.out.println(mClientUser.getUserName() + "正在登录~");
            clientUsers.add(mClientUser);
            InetAddress address = socket.getInetAddress();
            System.out.println("当前客户端的IP：" + address.getHostAddress());
            System.out.println("当前本地的IP：" + socket.getLocalSocketAddress());
            System.out.println("当前本地的IP：" + socket.getLocalAddress());

            //接收信息线程
            ServerSocketReceiveThread serverThread = new ServerSocketReceiveThread(clientUsers, mClientUser);
            serverThread.start();
            //发送信息线程
            ServerSocketSendThread serverSocketSendThread = new ServerSocketSendThread(clientUsers, mClientUser);
            serverSocketSendThread.start();
        }
    }
}
