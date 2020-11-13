package com.zlyc.www.bean.address;

import android.text.TextUtils;

import java.io.Serializable;

public class AddressBean implements Serializable {
    /**
     * id : 1249690845888765953
     * uid : 434720845688999936
     * name : 周凯
     * province : 湖南省
     * city : 株洲市
     * region : 株洲县
     * address : 渌口镇渌口村
     * mobile : 15616367868
     * type : 1
     */

    private String id;
    private String uid;
    private String name;
    private String province;
    private String city;
    private String region;
    private String address;
    private String mobile;
    private int type;

    public String getId() {
        return id;
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
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

    public String getAddress() {
        return address;
    }

    public String getMobile() {
        return mobile;
    }

    public int getType() {
        return type;
    }

    public String getNameHead() {
        if (TextUtils.isEmpty(name))
            return "";
        return name.substring(0, 1);
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

    public String getAddressAll() {
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
        if (!TextUtils.isEmpty(address)) {
            str = str + address;
        }
        return str;
    }

}
