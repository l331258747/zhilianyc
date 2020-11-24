package com.zqlc.www.util.rxbus.busEvent;

public class SetLoginMobileEvent {
    String mobile;

    public SetLoginMobileEvent(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

}
