package client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import method.TransferFile;

/**
 * Created by ejiang on 2017-07-13.
 */
public class TcpClientHelper implements SendCallback {
    public TcpClientHelper() throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                LocalClient client = new TcpClientThreadTest();
                client.connection();
                client.sendFileByteList("F:\\黑客壁纸.jpeg", TcpClientHelper.this);
            }
        }).start();
    }

    @Override
    public void sendSuccess() {
        System.out.println("上传完成~");
    }

    @Override
    public void sendError(Exception e) {
        System.err.println(e.toString());
    }
}
