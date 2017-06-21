package socket;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by ejiang on 2017-06-16.
 */
public class ServerSocketHelper {
    public ServerSocketHelper() {
        initServerSocket();
    }

    private void initServerSocket() {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            Socket socket = null;
//            //记录客户端的数量
//            int count=0;
//            System.out.println("***服务器即将启动，等待客户端的连接***");
//            //循环监听等待客户端的连接
//            while(true){
//                //调用accept()方法开始监听，等待客户端的连接
//                socket=serverSocket.accept();
//                //创建一个新的线程
//                ServerSocketThread serverThread=new ServerSocketThread(socket);
//                //启动线程
//                serverThread.start();
//                count++;//统计客户端的数量
//                System.out.println("客户端的数量："+count);
//                InetAddress address=socket.getInetAddress();
//                System.out.println("当前客户端的IP："+address.getHostAddress());
//                System.out.println("当前本地的IP："+socket.getLocalSocketAddress());
//                System.out.println("当前本地的IP："+socket.getLocalAddress());
//            }

            //调用accept()方法开始监听，等待客户端的连接
            socket = serverSocket.accept();
            //线程执行的操作，响应客户端的请求
            InputStream is = null;
            InputStreamReader isr = null;
            BufferedReader br = null;
            OutputStream os = null;
            PrintWriter pw = null;
            try {
                //获取输入流，并读取客户端信息
                is = socket.getInputStream();
                isr = new InputStreamReader(is);
                br = new BufferedReader(isr);
                String info = null;
                while ((info = br.readLine()) != null) {//循环读取客户端的信息
                    System.out.println("我是服务器，客户端说：" + info);
                }
                socket.shutdownInput();//关闭输入流
                //获取输出流，响应客户端的请求
                os = socket.getOutputStream();
                pw = new PrintWriter(os);
                pw.write("欢迎您！");
                pw.flush();//调用flush()方法将缓冲输出
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //关闭资源
                try {
                    if (pw != null)
                        pw.close();
                    if (os != null)
                        os.close();
                    if (br != null)
                        br.close();
                    if (isr != null)
                        isr.close();
                    if (is != null)
                        is.close();
                    if (socket != null)
                        socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
