package com.nettech.appframe.network_frame.listener;


import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by Administrator on 2018/2/26.
 * 解决activity内存泄漏问题
 */
public class NoHttpResponseListener implements OnResponseListener<String>,ResponseListener {
    private ResponseListener callback;

    public NoHttpResponseListener(ResponseListener callback) {
        this.callback = callback;
    }
    /**
     *  ResponseListener
     */
    @Override
    public void onSStart(int what) {
        callback.onSStart(what);
    }

    @Override
    public void onFFinish(int what) {
        callback.onFFinish(what);
    }

    @Override
    public void noNet(int what) {
        callback.noNet(what);
    }

    @Override
    public void handle200(int what,String data) {
        callback.handle200(what,data);
    }

    @Override
    public void handle400(int what,String data) {
        callback.handle400(what,data);
    }

    @Override
    public void handleFailed(int what,Exception e) {
        callback.handleFailed(what,e);
    }

    /**
     *  OnResponseListener
     */
    @Override
    public void onStart(int what) {
        onSStart(what);
    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        switch (response.responseCode()){
            case 200:
                handle200(what,response.get());
                break;

            case 404:
                handle400(what,response.get());
                break;
        }

    }

    @Override
    public void onFailed(int what, Response<String> response) {
        handleFailed(what,response.getException());
    }

    @Override
    public void onFinish(int what) {
        onFFinish(what);
    }
}
