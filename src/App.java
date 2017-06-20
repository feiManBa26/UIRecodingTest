import socket.SocketHelper;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by ejiang on 2017-06-16.
 */
public class App {
    public static void main(String[] arg) throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost.getHostAddress()); //获取计算机Ip地址
        new SocketHelper(localHost.getHostAddress());
//        new ServerSocketHelper();
    }
}
