package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import method.ClientUser;

/**
 * Created by ejiang on 2017-07-13.
 */
public class TcpServerScoketThread extends Thread {
    private List<ClientUser> mUsers;
    private ClientUser mUser;

    public TcpServerScoketThread(List<ClientUser> users, ClientUser user) {
        mUsers = users;
        mUser = user;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // 信息的格式：(login||logout||say),发送人,收发人,信息体
                //不断地读取客户端发过来的信息
                String msg = mUser.getBr().readLine();
                System.out.println(msg);
                String[] str = msg.split(",");
                switch (str[0]) {
                    case "logout":
                        remove(mUser);// 移除用户
                        break;
                    case "say":
                        sendToClient(str[1], msg); // 转发信息给特定的用户
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            System.out.print("异常");
        } finally {
            try {
                mUser.getBr().close();
                mUser.getSocket().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void remove(ClientUser clientUser) {
        mUsers.remove(clientUser);
    }

    private void sendToClient(String username, String msg) {
        for (ClientUser user : mUsers) {
            if (user.getUserName().equals(username)) {
                try {
                    PrintWriter pw =user.getPw();
                    pw.println(msg);
                    pw.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
