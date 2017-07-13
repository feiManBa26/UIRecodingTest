package server;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;
import javax.xml.parsers.FactoryConfigurationError;

/**
 * Created by ejiang on 2017-07-12.
 */
public class OpenImage {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new ImageviewOpenFrame();
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Dimension size = toolkit.getScreenSize();
                int width = size.width / 4;
                int height = size.height / 4;
                frame.setLocation(width, height);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }

    static class ImageviewOpenFrame extends JFrame {
        public ImageviewOpenFrame() throws HeadlessException {
            setTitle("显示client传递的照片");
            JLabel label = new JLabel();
            add(label);
            ImageIcon icon = new ImageIcon("D:\\Ming_Project\\xiaoPush\\GrowingCastScreen\\app\\src\\main\\res\\drawable-xxhdpi\\castscreen.png");
            label.setIcon(icon);
            setSize(icon.getIconWidth(), icon.getIconHeight());

        }
    }


    static class ImageViewerFrame extends JFrame {
        public ImageViewerFrame() {
            setTitle("ImageViewer");
            label = new JLabel();
            add(label);
            chooser = new JFileChooser();
            chooser.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.getName().endsWith(".jpg");
                }

                @Override
                public String getDescription() {
                    return ".jpg";
                }
            });
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension size = toolkit.getScreenSize();

            chooser.setSize(size.width / 2, size.height / 2);
            chooser.setLocation(size.width / 4, size.height / 4);
            chooser.setCurrentDirectory(new File("."));

            JMenuBar menubar = new JMenuBar();
            setJMenuBar(menubar);
            JMenu menu = new JMenu("File");
            menubar.add(menu);
            JMenuItem openItem = new JMenuItem("Open");
            menu.add(openItem);
            JMenuItem exitItem = new JMenuItem("Close");
            menu.add(exitItem);
            openItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    int result = chooser.showOpenDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        String name = chooser.getSelectedFile().getPath();
                        label.setIcon(new ImageIcon(name));
                    }
                }
            });
            exitItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    System.exit(0);
                }
            });
        }

        private JLabel label;
        private JFileChooser chooser;
        private static final int DEFAULT_WIDTH = 300;
        private static final int DEFAULT_HEIGHT = 400;
    }

}
