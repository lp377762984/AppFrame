package com.nettech.appframe.network_frame;

import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;


public class BaseActivity extends AppCompatActivity implements ListenerCallback {
    NetworkManager manager = new NoHttpNetworkManager();
    String TAG = this.getClass().getSimpleName();
    private NetBroadcastReceiver mNetReceiver;
    private FinishBroadcastReceiver mFinishReceiver;
    protected String finishAction = "com.nettech.appFrame.exit";
    private PermissionCallback requestPermissionAndBack;

    /**
     * 网络状态广播 销毁界面广播
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNetReceiver = new NetBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mNetReceiver, intentFilter);

        mFinishReceiver = new FinishBroadcastReceiver();
        IntentFilter intentFilter2 = new IntentFilter(finishAction);
        registerReceiver(mFinishReceiver, intentFilter2);
    }

    /**
     * 网络请求回调
     */
    @Override
    public void onSStart(int what) {
        Log.d(TAG, "onSStart: ");
    }

    @Override
    public void onFFinish(int what) {
        Log.d(TAG, "onFinish: ");
    }

    @Override
    public void noNet(int what) {
        Log.d(TAG, "noNet: ");
    }

    @Override
    public void handle200(int what, String data) {
        Log.d(TAG, "handle200: " + data);
    }

    @Override
    public void handle400(int what, String data) {
        Log.d(TAG, "handle400: ");
    }

    @Override
    public void handleFailed(int what, Exception e) {
        Log.d(TAG, "handleFailed: " + e.getMessage());
    }

    /**
     * 取消当前页面所有网络请求
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.removeRequest();
        unregisterReceiver(mNetReceiver);
        unregisterReceiver(mFinishReceiver);
    }

    public interface PermissionCallback {
        void requestPermissionAndBack(boolean isOk);
    }

    /**
     * 申请权限
     */
    public void checkAndRequestAllPermission(String[] permissions, PermissionCallback requestPermissionAndBack) {
        this.requestPermissionAndBack = requestPermissionAndBack;
        if (permissions == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            if (!checkAllPermissions(permissions)) {//没有所有的权限
                ActivityCompat.requestPermissions(this, permissions, 1);
            } else {
                if (requestPermissionAndBack != null) {
                    requestPermissionAndBack.requestPermissionAndBack(true);//有权限
                }
            }
        } else {
            if (requestPermissionAndBack != null) {
                requestPermissionAndBack.requestPermissionAndBack(true);//有权限
            }
        }
    }

    /**
     * 检查是否有权限
     */
    private boolean checkAllPermissions(@NonNull String[] permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] per,
                                           @NonNull int[] grantResults) {
        if (requestCode == 1) {
            boolean isAll = true;
            for (int i = 0; i < per.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    isAll = false;
                    break;
                }
            }
            if (!isAll) {
                Toast.makeText(this, "部分功能可能无法使用，因为你拒绝了一些权限", Toast.LENGTH_SHORT).show();
            }
            if (requestPermissionAndBack != null) {
                requestPermissionAndBack.requestPermissionAndBack(isAll);//isAll 用户是否拥有所有权限
            }
        }
        super.onRequestPermissionsResult(requestCode, per, grantResults);
    }
}
