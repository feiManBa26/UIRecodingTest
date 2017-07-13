package method;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by ejiang on 2017-07-13.
 */
public class ClientUser {
    private String userName;
    private String account;
    private Socket socket;
    private BufferedReader br;
    private PrintWriter pw;

    public ClientUser(String userName, final Socket socket) throws IOException {
        this.userName = userName;
        this.socket = socket;
        this.br = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        this.pw = new PrintWriter(socket.getOutputStream());
    }

    @Override
    public String toString() {
        return "ClientUser{" +
                "userName='" + userName + '\'' +
                ", account='" + account + '\'' +
                ", socket=" + socket +
                ", br=" + br +
                ", pw=" + pw +
                '}';
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

    public BufferedReader getBr() {
        return br;
    }

    public void setBr(BufferedReader br) {
        this.br = br;
    }

    public PrintWriter getPw() {
        return pw;
    }

    public void setPw(PrintWriter pw) {
        this.pw = pw;
    }
}
