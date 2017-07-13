package utils;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * Created by ejiang on 2017-07-13.
 */
public class OpenImageUtils {
    public OpenImageUtils(final String filePath) {

        try {
            JFrame jFrame = new ImageLocalFrame(filePath);
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension size = toolkit.getScreenSize();
            int width = size.width / 4;
            int height = size.height / 4;
            jFrame.setLocation(width, height);
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setVisible(true);
        } catch (HeadlessException e) {
            e.printStackTrace();
        }

//        EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
    }
}
