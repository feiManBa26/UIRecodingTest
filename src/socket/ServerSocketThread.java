package socket;

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
            dataOutputStream.writeUTF("草泥马socket");
            dataOutputStream.flush();
            socket.shutdownOutput();

            //获取输入流，并读取客户端信息
            dataInputStream = new DataInputStream(socket.getInputStream());
            String info = null;
            info = dataInputStream.readUTF();
            System.out.println("我是服务器，客户端说：" + info);
            socket.shutdownInput();//关闭输入流


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
