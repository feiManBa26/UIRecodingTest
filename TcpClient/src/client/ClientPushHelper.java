package client;

import com.sun.istack.internal.NotNull;
import utils.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by 明正 on 2017/6/25.
 */
public class ClientPushHelper {
    private LClient client; //socket控制接口

    public ClientPushHelper(LClient client) {
        this.client = client;
    }


    /**
     * 发送字符串消息
     */

    public void pushStr(@NotNull String pushStr, ISendCallBack callBack) throws IOException {
        if (pushStr == null) {
            return;
        }
        byte[] pushStrBytes = pushStr.getBytes("UTF-8");
        client.send(pushStrBytes, callBack);
    }

    /**
     * 发送文件
     */

    public void pushFile(File file, ISendCallBack callBack) throws IOException {
        if (file.exists()) {
            //上传文件类型
            client.send(IOUtils.intToBytes(TransferCommandTypes.SendFile.ordinal()), callBack);

            //文件的名称
//            client.send();

//            fileName.getBytes("UTF-8");
//            client.send(fileName.getBytes("UTF-8"), callBack);

            //文件的长度
            long length = file.length();
            String fileLength = length+"";
            client.send(fileLength.getBytes("UTF-8"), callBack);

            //  包的长度
            byte[] bytes = new byte[4096]; //4KB
            long packageBytes = bytes.length;

            //循环上传文件数据流
            if (length > packageBytes) {
                //分片上传
            } else {

            }
        }
    }

    /**
     * Created by ejiang on 2017-06-23.
     */
    private enum TransferCommandTypes {
        SendFile,
        SendString,
        Screen
    }
}
