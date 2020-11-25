package com.zqlc.www.bean.user;

public class AuthRealNameVO {

    String webankAppId;
    String nonce;

    String orderNo;
    String h5faceId;
    String userId;
    String sign;
    String version;

    String payUrl;
    String url;

    public String getWebankAppId() {
        return webankAppId;
    }

    public String getNonce() {
        return nonce;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public String getH5faceId() {
        return h5faceId;
    }

    public String getUserId() {
        return userId;
    }

    public String getSign() {
        return sign;
    }

    public String getVersion() {
        return version;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public String getUrl() {
        return url;
    }
}
