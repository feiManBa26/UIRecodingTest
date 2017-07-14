package client;

/**
 * Created by ejiang on 2017-07-14.
 */
public abstract interface SendCallback {
    void sendSuccess();

    void sendError(Exception e);
}
