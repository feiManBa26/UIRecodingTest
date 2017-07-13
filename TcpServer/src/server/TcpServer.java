package server;

import java.io.IOException;

/**
 * Created by ejiang on 2017-06-20.
 */
public class TcpServer {
    public static void main(String[] args) {
        try {
            new TcpServerHelper();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
