package rpc.common;

/**
 * Created by noodles on 16/9/8 上午1:51.
 */
public interface ResponseCallBack {

    void done(Object response);

    void caught(Throwable exception);

}
