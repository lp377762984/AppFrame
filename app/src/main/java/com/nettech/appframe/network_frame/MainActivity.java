package com.nettech.appframe.network_frame;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nettech.appframe.R;


public class MainActivity extends BaseActivity implements View.OnClickListener, BaseActivity.PermissionCallback {

    private TextView tv;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);
        NetRequest netRequest = manager.buildRequest("http://www.baidu.com");
        manager.doRequest(0, netRequest, new NoHttpResponseListener(this));

        tv = findViewById(R.id.tv);
        progressBar = findViewById(R.id.progressBar);
        Button btnFinish = findViewById(R.id.btn);
        btnFinish.setOnClickListener(this);
        View btnPer = findViewById(R.id.btnPer);
        btnPer.setOnClickListener(this);
    }

    @Override
    public void onSStart(int what) {
        super.onSStart(what);
        tv.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFFinish(int what) {
        super.onFFinish(what);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void handle200(int what, String data) {
        super.handle200(what, data);
        tv.setVisibility(View.VISIBLE);
        tv.setText(Html.fromHtml(data));
    }

    @Override
    public void handle400(int what, String data) {
        super.handle400(what, data);
    }

    @Override
    public void handleFailed(int what, Exception e) {
        super.handleFailed(what, e);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                Intent intent = new Intent(finishAction);
                sendBroadcast(intent);
                break;
            case R.id.btnPer:
                checkAndRequestAllPermission(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA}, this);
                break;
        }
    }

    @Override
    public void requestPermissionAndBack(boolean isOk) {
        Toast.makeText(this, isOk ? "有权限" : "无权限", Toast.LENGTH_SHORT).show();
    }
}
