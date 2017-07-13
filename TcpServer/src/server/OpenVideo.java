package server;

import java.awt.HeadlessException;

import javax.swing.JFrame;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 * Created by ejiang on 2017-07-13.
 */
public class OpenVideo {
    public static void main(String[] args) {

    }

    static class VideoJFrame extends JFrame{
        public VideoJFrame(String videoFath) throws HeadlessException {
            MediaView mediaView = new MediaView();
            mediaView.setMediaPlayer(new MediaPlayer(new Media(videoFath)));

        }
    }
}
