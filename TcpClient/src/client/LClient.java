package client;


import java.io.IOException;

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

    void send(byte[] bytes, ISendCallBack callback) throws IOException;
    void send(String strData);

    /**
     * 上传文件方法
     * @param fileName
     */
    void sendFile(String fileName) throws IOException;

    /**
     * @return 返回socket连接状态
     */
    ConnectEnum connect();
}
