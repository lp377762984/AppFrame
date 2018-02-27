package com.nettech.appframe.network_frame.request;

import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.StringRequest;

/**
 * Created by ASUS on 2018/2/25.
 */

public class NetRequestImpl extends StringRequest implements NetRequest {
    public NetRequestImpl(String url) {
        super(url);
    }

    public NetRequestImpl(String url, RequestMethod requestMethod) {
        super(url, requestMethod);
    }
}
