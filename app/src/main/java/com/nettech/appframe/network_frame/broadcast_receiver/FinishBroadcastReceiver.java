package com.nettech.appframe.network_frame.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.nettech.appframe.network_frame.BaseActivity;

/**
 * Created by Administrator on 2018/2/26.
 */
public class FinishBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(context instanceof BaseActivity){
            ((BaseActivity) context).finish();
        }
    }
}
