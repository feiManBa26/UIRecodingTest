package method;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by ejiang on 2017-07-13.
 */
public class User {
    private String userName;
    private String account;
    private Socket socket;
    private DataInputStream mDataInputStream;
    private DataOutputStream mDataOutputStream;

    public User(String userName, Socket socket) throws IOException {
        this.userName = userName;
        this.socket = socket;
        this.mDataInputStream = new DataInputStream(socket.getInputStream());
        this.mDataOutputStream = new DataOutputStream(socket.getOutputStream());
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public DataInputStream getDataInputStream() {
        return mDataInputStream;
    }

    public void setDataInputStream(DataInputStream dataInputStream) {
        mDataInputStream = dataInputStream;
    }

    public DataOutputStream getDataOutputStream() {
        return mDataOutputStream;
    }

    public void setDataOutputStream(DataOutputStream dataOutputStream) {
        mDataOutputStream = dataOutputStream;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", account='" + account + '\'' +
                ", socket=" + socket +
                ", mDataInputStream=" + mDataInputStream +
                ", mDataOutputStream=" + mDataOutputStream +
                '}';
    }
}
