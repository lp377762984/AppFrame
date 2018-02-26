package com.nettech.appframe.network_frame;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;

import com.yanzhenjie.nohttp.tools.NetUtils;

/**
 * Created by Administrator on 2018/2/26.
 */
public class NetBroadcastReceiver extends BroadcastReceiver {
    private String TAG = "NetBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (null == action)
            return;
        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            if (!NetUtils.isNetworkAvailable()) {//没网络
                Log.e(TAG, "noNetwork: ");
            } else {
                Log.d(TAG, "network is available: ");
            }
        }
    }
}
