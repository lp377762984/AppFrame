package com.nettech.appframe.network_frame;

import android.app.Application;

import com.yanzhenjie.nohttp.NoHttp;


public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NoHttp.initialize(this);
    }
}
