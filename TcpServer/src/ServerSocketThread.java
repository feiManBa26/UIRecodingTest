import javax.print.DocFlavor;

import java.awt.Desktop;
import java.io.*;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;

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
            dataOutputStream.writeUTF(serverPush);
            dataOutputStream.flush();
            socket.shutdownOutput();

            //获取输入流，并读取客户端信息
            dataInputStream = new DataInputStream(socket.getInputStream());

            /**
             * 读取客户端信息：
             * 1.读取客户端信息类型 0--文件类型 1--字符串 2--投屏信息
             */
            int toInt = dataInputStream.readInt();
            switch (toInt) {
                case 0: //文件流
                    //获取文件名称
                    String fileName = dataInputStream.readUTF();
                    System.out.println(fileName);
                    //获取文件长度
                    long readLong = dataInputStream.readLong();
                    System.out.println(readLong);
                    //获取文件输入流
                    File file = new File("D:/input/" + fileName);
                    if (file.exists()) {
                        file.delete();
                    } else {
                        file.createNewFile();
                    }

                    FileOutputStream fos = new FileOutputStream(file);
                    // 开始接收文件
                    byte[] bytes = new byte[1024];
                    int num = 0;
                    while ((num = dataInputStream.read(bytes, 0, bytes.length)) != -1) {
                        fos.write(bytes, 0, num);
                        fos.flush();
                    }
                    break;
                case 1: //string字符流
                    byte[] bytes1 = new byte[1024];
                    int length = 0;
                    StringBuffer stringBuffer = new StringBuffer();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
                    while ((length = dataInputStream.read(bytes1, 0, bytes1.length)) != -1) {
                        byteArrayOutputStream.write(bytes1, 0, length);
                        byteArrayOutputStream.flush();
                    }
                    System.out.println(byteArrayOutputStream.toString());
                    break;
                case 2: //开始接收投屏信息
                    startCastScreen();
                    break;
                default:
                    break;
            }
            socket.shutdownInput();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("与客户端连接已经断开");
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

    private void startCastScreen() {
        System.out.print("开始接收客户端投屏信息");
        try {
            Desktop.getDesktop().browse(new URI("http://192.168.0.38:8080"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


}
