package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by ejiang on 2017-07-13.
 */
public class TcpClientHelper {
    public TcpClientHelper() throws IOException {
        Socket socket = new Socket("localhost", 8888);
        TcpClientThread clientThread = new TcpClientThread(socket);
        clientThread.setName("thread1");
        clientThread.start();
        //主线程用来发送信息
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        while (true) {
            String s = br.readLine();
            out.println(s);
            //out.write(s+"\n");
            out.flush();
        }
    }
}
