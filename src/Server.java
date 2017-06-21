import socket.ServerSocketHelper;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by ejiang on 2017-06-20.
 */
public class Server {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost.getHostAddress()); //获取计算机Ip地址
        new ServerSocketHelper();
    }
}
