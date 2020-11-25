package com.zqlc.www.util.rxbus.busEvent;

import android.text.TextUtils;

public class RegionSelEvent {
    String province;
    String city;
    String region;
    String pCode;

    public RegionSelEvent(String province, String city, String region, String pCode) {
        this.province = province;
        this.city = city;
        this.region = region;
        this.pCode = pCode;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getRegion() {
        return region;
    }

    public String getpCode() {
        return pCode;
    }

    public String getAddressDes() {
        String str = "";
        if (!TextUtils.isEmpty(province)) {
            str = str + province + " ";
        }
        if (!TextUtils.isEmpty(city)) {
            str = str + city + " ";
        }
        if (!TextUtils.isEmpty(region)) {
            str = str + region + " ";
        }
        return str;
    }

}
