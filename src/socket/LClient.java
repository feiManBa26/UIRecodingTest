package socket;


/**
 * File: LClient.java
 * Author: ejiang
 * Version: V100R001C01
 * Create: 2017-06-22 13:16
 */

public interface LClient {

    /**
     * 创建socket连接，并准备接收数据。
     * @param ip
     * @param port
     */
    void connect(String ip, int port);

    /**
     * 断开连接。
     */
    void disConnect();

    /**
     * 是否是连接状态
     * @return
     */
    boolean isConnected();

    void send(byte[] bytes, ISendCallBack callback);

    void send(String strData);
    /**
     * @return 返回socket连接状态
     */
    ConnectEnum connect();
}
