package client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import method.TransferFile;

/**
 * Created by ejiang on 2017-07-13.
 */
public class TcpClientHelper {
    public TcpClientHelper() throws IOException {
//        Socket socket = new Socket("localhost", 8888);
//        TcpClientThread clientThread = new TcpClientThread(socket);
//        clientThread.setName("thread1");
//        clientThread.start();
//        //主线程用来发送信息
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter out = new PrintWriter(socket.getOutputStream());
//        while (true) {
//            String s = br.readLine();
//            out.println(s);
//            //out.write(s+"\n");
//            out.flush();
//        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                LocalClient client = new TcpClientThreadTest();
                client.connection();
//                while (true) {
//                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//                    try {
//                        TransferMessage message = new TransferMessage();
//                        message.setMessageTypes(TransferMessage.MessageTypes.SendString);
//                        message.setMessageContent(br.readLine());
//                        client.sendMessgae(message, new SendCallback() {
//                            @Override
//                            public void sendSuccess() {
//
//                            }
//
//                            @Override
//                            public void sendError(Exception e) {
//
//                            }
//                        });
////                        client.sendStr(br.readLine(), new SendCallback() {
////                            @Override
////                            public void sendSuccess() {
////                            }
////
////                            @Override
////                            public void sendError(Exception e) {
////                            }
////                        });
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//                client.sendMethod(getTranferFile("F:\\黑客壁纸.jpeg"), new SendCallback() {
//                    @Override
//                    public void sendSuccess() {
//
//                    }
//
//                    @Override
//                    public void sendError(Exception e) {
//
//                    }
//                });
//
//                client.sendByteList("F:\\黑客壁纸.jpeg", new SendCallback() {
//                    @Override
//                    public void sendSuccess() {
//
//                    }
//
//                    @Override
//                    public void sendError(Exception e) {
//
//                    }
//                });
            }
        }).start();
    }
    private TransferFile getTranferFile(String filePath) {

        File file = new File(filePath);
        if (file.exists()) {
            TransferFile transferFile = new TransferFile();
            String name = file.getName();
            transferFile.setFileName(name);
            try {
                InputStream inputStream = new FileInputStream(file);
                int log = inputStream.available();
                transferFile.setFileSize(log);
                transferFile.setFileType(0);
                transferFile.setBagSize(1024);
                transferFile.setBagCont(getBagNum(log));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private int getBagNum(int log) {
        int num = 0;
        if (log < 1024) {
            num = 1;
            return num;
        } else {
            int blog = log % 1024;
            if (blog == 0) {
                return log / 1024;
            } else {
                int lo = log - blog;
                return (lo / 1024) + 1;
            }
        }
    }
}
