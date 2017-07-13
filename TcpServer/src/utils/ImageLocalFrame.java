package utils;

import java.awt.HeadlessException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javafx.scene.media.MediaView;

/**
 * Created by ejiang on 2017-07-13.
 */
public class ImageLocalFrame extends JFrame {
    public ImageLocalFrame(String filePath) throws HeadlessException {
        setTitle("显示client传递的照片");
        JLabel label = new JLabel();
        add(label);
        ImageIcon icon = new ImageIcon(filePath);
        label.setIcon(icon);
        setSize(icon.getIconWidth(), icon.getIconHeight());
    }
}
