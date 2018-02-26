package com.nettech.appframe.network_frame;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

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
