package method;

/**
 * Created by ejiang on 2017-07-14.
 */
public class TransferMessage {


    public int getClientType() {
        return clientType;
    }

    public void setClientType(int clientType) {
        this.clientType = clientType;
    }

    public MessageTypes getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageTypes messageType) {
        this.messageType = messageType;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    private int clientType;
    private MessageTypes messageType;
    private String messageContent;

    /// <summary>
    /// 消息类型
    /// </summary>
    public enum MessageTypes {
        SendString,
        SendFile,
        StartScreen,
        EndScreen,
        BookControl,
        MusicControl
    }

    /// <summary>
    /// 绘本遥控器操作命令
    /// </summary>
    public enum BookControlCo {
        Previous,
        Next
    }

    /// <summary>
    /// 听音乐遥控器操作命令
    /// </summary>
    public enum MusicControlC {
        Previous,
        Next,
        VolumeIncrease,
        VolumeDecrease
    }

}
