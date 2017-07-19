package server;

import com.google.gson.Gson;

import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import method.TransferMessage;
import method.User;
import utils.IOUtils;
import utils.OpenImageUtils;

/**
 * Created by ejiang on 2017-06-20.
 * 服务端读取连接客户端发送信息线程
 */
public class ServerSocketReceiveThread extends Thread {
    private List<User> mList;
    private User mUser;

    public ServerSocketReceiveThread(List<User> list, User user) {
        mList = list;
        mUser = user;
    }

    @Override
    public void run() {
        try {
            while (true) {
                /**
                 * 读取客户端信息：
                 * 1.读取客户端信息类型 0--文件类型 1--字符串 2--投屏信息
                 */
                int toInt = mUser.getDataInputStream().readInt();
                switch (toInt) {
                    case 0: //文件流
                        //获取文件名称
                        String fileName = mUser.getDataInputStream().readUTF();
                        System.out.println(fileName);
                        //获取文件长度
                        long readLong = mUser.getDataInputStream().readLong();
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
                        while (readLong > 0 && ((num = mUser.getDataInputStream().read(bytes, 0, bytes.length)) != -1)) {
                            fos.write(bytes, 0, num);
                            fos.flush();
                            readLong -= num;
                        }
                        fos.flush();
                        fos.close();
                        fos = null;
                        if (readLong > 0) {
                            continue;
                        }
                        showImage(file);
                        break;
                    case 1: //string字符流
                        byte[] bytes1 = new byte[1024];
                        int length = 0;
                        StringBuffer stringBuffer = new StringBuffer();
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
                        while ((length = mUser.getDataInputStream().read(bytes1, 0, bytes1.length)) != -1) {
                            byteArrayOutputStream.write(bytes1, 0, length);
                            byteArrayOutputStream.flush();
                        }
                        stringBuffer.append(byteArrayOutputStream);
                        System.out.println(stringBuffer.toString());
                        stringBuffer.append("");
                        break;
                    case 2: //开始接收投屏信息
                        startCastScreen();
                        break;
                    case 3:
                        remove(mUser);
                        break;
                    case 4:
                        byte[] bytes2 = new byte[4];
                        mUser.getDataInputStream().read(bytes2);
                        int leght = IOUtils.bytesToInt(bytes2, 0);
                        int total = 0;
                        byte[] bytes3 = new byte[leght];
                        StringBuffer buffer = new StringBuffer();
                        while (leght > 0 && ((total = mUser.getDataInputStream().read(bytes3, 0, bytes3.length)) != -1)) {
                            buffer.append(new String(bytes3, 0, total));
                            leght -= total;
                        }
                        System.out.println(buffer.toString());
                        break;
                    default:
                        break;
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("与客户端连接已经断开");
        } finally {
            //关闭资源
            try {
                mUser.getDataInputStream().close();
                mUser.getSocket().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void showImage(File file) {
        new OpenImageUtils(file.getAbsolutePath());
    }

    private void remove(User user) {
        this.mList.remove(user);
    }

    private void startCastScreen() {
        System.out.println("开始接收客户端投屏信息");
        try {
            Desktop.getDesktop().browse(new URI("http://192.168.0.38:8080"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
