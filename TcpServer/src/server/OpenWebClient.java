package server;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by ejiang on 2017-07-12.
 */
public class OpenWebClient {
    private static final String url = "http://192.168.0.38:8080";
    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }
}
