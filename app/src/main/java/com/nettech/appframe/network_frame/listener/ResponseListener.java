package com.nettech.appframe.network_frame.listener;

/**
 * Created by ASUS on 2018/2/25.
 */

public interface ResponseListener extends BaseResponseListener {
    void onSStart(int what);
    void onFFinish(int what);
    void noNet(int what);
    void handle200(int what, String data);
    void handle400(int what, String data);
    void handleFailed(int what, Exception e);
}
