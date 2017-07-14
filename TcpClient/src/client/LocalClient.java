package client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import method.TransferFile;
import method.TransferMessage;

/**
 * Created by ejiang on 2017-07-14.
 */
public interface LocalClient {
    int connection();

    void sendMethod(TransferFile transferFile, SendCallback callback);

    void sendMessgae(TransferMessage message, SendCallback callback);

    void sendStr(String str, SendCallback callback);

    void sendByteList(String filePath, SendCallback callback);

    void disconnect() throws IOException;
}
