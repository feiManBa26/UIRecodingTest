package socket;

import utils.IOUtils;

import java.io.*;
import java.net.Socket;

/**
 * Created by ejiang on 2017-06-20.
 */
public class ServerSocketThread extends Thread {
    //和本线程相关的socket
    Socket socket = null;
    //线程执行的操作，响应客户端的请求
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;

    public ServerSocketThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            //获取输出流，响应客户端的请求
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            String serverPush = "你说你说socket";
            String length = serverPush.length() + "";
            byte[] intToBytes = length.getBytes("UTF-8");
            byte[] serverPushBytes = serverPush.getBytes("UTF-8");
            dataOutputStream.write(intToBytes);
            dataOutputStream.write(serverPushBytes);
            dataOutputStream.flush();
            socket.shutdownOutput();

            //获取输入流，并读取客户端信息
            dataInputStream = new DataInputStream(socket.getInputStream());
            /**
             * 读取客户端信息：
             * 1.读取客户端信息类型 0--文件类型 1--字符串 2--投屏信息
             */
            byte[] bytes = new byte[4];
            dataInputStream.read(bytes);
            int bytesToInt = IOUtils.bytesToInt(bytes, 0);
            switch (bytesToInt) {
                case 0:
                    break;
                case 1: //string字符流
                    String info = null;
                    byte[] bytes1 = new byte[bytesToInt];
                    dataInputStream.read(bytes1);
                    info = new String(bytes1);
                    System.out.print(info);
                    break;
                case 2:
                    break;
            }
            socket.shutdownInput();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            try {
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
