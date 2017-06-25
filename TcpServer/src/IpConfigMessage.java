import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * Created by ejiang on 2017-06-16.
 */
public class IpConfigMessage {
    public IpConfigMessage() throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost.getHostName()); //获取计算机名称
        System.out.println(localHost.getHostAddress()); //获取计算机Ip地址
        byte[] address = localHost.getAddress();
        System.out.println(Arrays.toString(address)); //获取数组形式Ip地址
        System.out.println(localHost.toString()); // 输出InetAddress对象

    }
}
