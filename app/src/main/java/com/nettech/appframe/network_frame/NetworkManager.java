package com.nettech.appframe.network_frame;

/**
 * Created by ASUS on 2018/2/25.
 */

public interface NetworkManager {
    NetRequest buildRequest(String url);
    void doRequest(int what, NetRequest request, BaseResponseListener responseListener);
    void removeRequest();
}
