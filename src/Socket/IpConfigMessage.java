package Socket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by ejiang on 2017-06-16.
 */
public class IpConfigMessage implements  ActionListener{
    public IpConfigMessage() throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.print(localHost.getHostAddress());


        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton jButton = new JButton("我是个按钮");
        jPanel.add(jButton);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("哈哈哈哈");
            }
        });
        JFrame jFrame = new JFrame();
        jFrame.setTitle("测试窗体");
        jFrame.setSize(600, 300);
        jFrame.setResizable(Boolean.FALSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.add(jPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
