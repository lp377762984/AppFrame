package com.nettech.appframe.network_frame;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.RequestQueue;

/**
 * Created by ASUS on 2018/2/25.
 */

public class NoHttpNetworkManager extends EmptyNetworkManager {
    private Object cacelObj = new Object();
    private RequestQueue requestQueue;

    @Override
    public NetRequest buildRequest(String url) {
        NetRequestImpl stringRequest = new NetRequestImpl(url, RequestMethod.POST);
        stringRequest.setCancelSign(cacelObj);
        return stringRequest;
    }

    @Override
    public void doRequest(int what, NetRequest request, ResponseListener responseListener) {
        if (requestQueue == null)
            requestQueue = NoHttp.newRequestQueue();
        requestQueue.add(what, ((NetRequestImpl) request), ((OnResponseListener<String>) responseListener));
    }

    @Override
    public void removeRequest() {
        if (requestQueue != null) {
            requestQueue.cancelBySign(cacelObj);
            requestQueue.stop();
        }
    }
}
