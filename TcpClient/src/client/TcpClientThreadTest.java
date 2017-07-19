package client;

import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import method.TransferFile;
import method.TransferMessage;
import utils.IOUtils;

/**
 * Created by ejiang on 2017-07-14.
 * 发送信息线程
 */
public class TcpClientThreadTest implements LocalClient {

    private int type = -1;
    private Socket mSocket;
    private DataOutputStream mDataOutputStream;
    private DataInputStream mDataInputStream;

    @Override
    public int connection() {
        try {
            mSocket = new Socket("192.168.0.16", 8000);
            if (mSocket.isConnected()) {
                type = 1;
                mDataInputStream = new DataInputStream(mSocket.getInputStream());
                mDataOutputStream = new DataOutputStream(mSocket.getOutputStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return type;
        }
        return type;
    }

    @Override
    public void sendMethod(TransferFile transferFile, SendCallback sendCallback) {
        if (transferFile == null) return;
        synchronized (TcpClientThreadTest.class) {
            if (isConnection()) {
                Gson gson = new Gson();
                String json = gson.toJson(transferFile);
                try {
                    TransferMessage message = new TransferMessage();
                    message.setMessageType(TransferMessage.MessageTypes.SendFile);
                    message.setMessageContent(json);
                    String json1 = gson.toJson(message);
                    byte[] jsonBytes = json1.getBytes("UTF-8");
                    int length = jsonBytes.length;
                    byte[] intToBytes = IOUtils.intToBytes(length);
                    mDataOutputStream.write(intToBytes);
                    mDataOutputStream.flush();
                    mDataOutputStream.write(jsonBytes);
                    mDataOutputStream.flush();
                    sendCallback.sendSuccess();
                } catch (IOException e) {
                    e.printStackTrace();
                    sendCallback.sendError(e);
                }
            }
        }
    }

    @Override
    public void sendMessgae(TransferMessage message, SendCallback callback) {
        if (message == null) return;
        synchronized (TcpClientThreadTest.class) {
            if (isConnection()) {
                Gson gson = new Gson();
                String json = gson.toJson(message);
                try {
                    byte[] jsonBytes = json.getBytes("UTF-8");
                    int length = jsonBytes.length;
                    byte[] intToBytes = IOUtils.intToBytes(length);
                    mDataOutputStream.write(intToBytes);
                    mDataOutputStream.flush();
                    mDataOutputStream.write(jsonBytes);
                    mDataOutputStream.flush();
                    callback.sendSuccess();
                } catch (IOException e) {
                    e.printStackTrace();
                    callback.sendError(e);
                }
            }
        }
    }

    @Override
    public void sendStr(String str, SendCallback callback) {
        if (str == null && str.isEmpty()) return;
        synchronized (TcpClientThreadTest.class) {
            if (isConnection()) {
                try {
                    mDataOutputStream.writeInt(4);
                    mDataOutputStream.flush();
                    byte[] strBytes = str.getBytes("UTF-8");
                    int length = strBytes.length;
                    byte[] intToBytes = IOUtils.intToBytes(length);
                    mDataOutputStream.write(intToBytes);
                    mDataOutputStream.flush();
                    mDataOutputStream.write(strBytes);
                    mDataOutputStream.flush();
                    callback.sendSuccess();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void sendByteList(String filePath, SendCallback callback) {
//        if (filePath == null && filePath.isEmpty()) return;
//        synchronized (TcpClientThreadTest.class) {
//            if (isConnection()) {
//                File file = new File(filePath);
//                if (file.exists()) {
//                    try {
//                        InputStream inputStream = new FileInputStream(file);
//                        if (inputStream == null) return;
//                        int available = inputStream.available();
//                        int length = 0;
//                        byte[] bytes = new byte[1024];
//                        long progress = 0;
//                        while ((length = inputStream.read(bytes, 0, bytes.length)) != -1) {
//                            byte[] intToBytes = IOUtils.intToBytes(length);
//                            mDataOutputStream.write(intToBytes);
//                            mDataOutputStream.flush();
//                            mDataOutputStream.write(bytes, 0, length);
//                            mDataOutputStream.flush();
//                            progress += length;
//                            System.out.println("| " + (100 * progress / available) + "% |");
//                        }
//                        inputStream.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
    }

    @Override
    public void sendFileByteList(String filePath, SendCallback callback) {
        if (filePath == null && filePath.isEmpty()) return;
        synchronized (TcpClientThreadTest.class) {
            if (isConnection()) {
                Gson gson = new Gson();
                String json = gson.toJson(getTranferFile(filePath));
                try {
                    TransferMessage message = new TransferMessage();
                    message.setMessageType(TransferMessage.MessageTypes.SendFile);
                    message.setMessageContent(json);
                    String json1 = gson.toJson(message);
                    byte[] jsonBytes = json1.getBytes("UTF-8");
                    int length = jsonBytes.length;
                    byte[] intToBytes = IOUtils.intToBytes(length);
                    mDataOutputStream.write(intToBytes);
                    mDataOutputStream.flush();
                    mDataOutputStream.write(jsonBytes);
                    mDataOutputStream.flush();
                    callback.sendSuccess();
                    File file = new File(filePath);
                    if (file.exists()) {
                        InputStream inputStream = new FileInputStream(file);
                        if (inputStream == null) return;
                        int available = inputStream.available();
                        int FileLength = 0;
                        byte[] bytes = new byte[1024];
                        long progress = 0;
                        while ((FileLength = inputStream.read(bytes, 0, bytes.length)) != -1) {
                            byte[] fileIntToBytes = IOUtils.intToBytes(FileLength);
                            mDataOutputStream.write(fileIntToBytes);
                            mDataOutputStream.flush();
                            mDataOutputStream.write(bytes, 0, FileLength);
                            mDataOutputStream.flush();
                            progress += FileLength;
                            System.out.println("| " + (100 * progress / available) + "% |");
                        }
                        inputStream.close();
                    }
                    callback.sendSuccess();
                } catch (IOException e) {
                    callback.sendError(e);
                }
            }
        }
    }

    @Override
    public void disconnect() throws IOException {
        synchronized (TcpClientThreadTest.class) {
            if (isConnection()) {
                closeIntputStream();
                closeOutputStream();
                mSocket.close();
                mSocket = null;
            }
        }
    }

    private boolean isConnection() {
        return mSocket != null && mSocket.isConnected();
    }

    private void closeOutputStream() throws IOException {
        if (mDataOutputStream != null)
            mDataOutputStream.close();
    }

    private void closeIntputStream() throws IOException {
        if (mDataInputStream != null)
            mDataInputStream.close();
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
            return transferFile;
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
