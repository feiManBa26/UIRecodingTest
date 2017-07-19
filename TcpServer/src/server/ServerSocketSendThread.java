package server;

import com.google.gson.Gson;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
public class ServerSocketSendThread extends Thread {
    private List<User> mList;
    private User mUser;
    public ServerSocketSendThread(List<User> list, User user) {
        mList = list;
        mUser = user;
    }

    @Override
    public void run() {
        try {
            while (true) {
                /*发送信息至客户端*/
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String line = br.readLine();
                sendMessage(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("与客户端连接已经断开");
        } finally {
            //关闭资源
            try {
                mUser.getDataOutputStream().close();
                mUser.getSocket().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendMessage(String line) {
        TransferMessage transferMessage = new TransferMessage();
        transferMessage.setMessageType(TransferMessage.MessageTypes.SendString);
        transferMessage.setMessageContent(line);
        Gson gson = new Gson();
        String json = gson.toJson(transferMessage);
        try {
            byte[] jsonBytes = json.getBytes("UTF-8");
            int length = jsonBytes.length;
            byte[] intToBytes = IOUtils.intToBytes(length);
            DataOutputStream outputStream = mUser.getDataOutputStream();
            outputStream.write(intToBytes);
            outputStream.flush();
            outputStream.write(jsonBytes);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
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
