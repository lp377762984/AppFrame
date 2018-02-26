package com.nettech.appframe.network_frame;

/**
 * Created by ASUS on 2018/2/25.
 */

public class EmptyNetworkManager implements NetworkManager {
    @Override
    public NetRequest buildRequest(String url) {
        return null;
    }

    @Override
    public void doRequest(int what ,NetRequest request, BaseResponseListener responseListener) {

    }

    @Override
    public void removeRequest() {

    }
}
