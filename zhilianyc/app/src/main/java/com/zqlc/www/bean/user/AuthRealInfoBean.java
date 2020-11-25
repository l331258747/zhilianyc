package com.zqlc.www.bean.user;

import android.text.TextUtils;

public class AuthRealInfoBean {

    /**
     * id : 483434180189884416
     * createTime : 1595426156000
     * updateTime : 1595479715000
     * status : 1
     * uid : 434720845688999936
     * name : 周凯
     * idCard : 430221198503110034
     * img : https://wall123-1301456511.cos.ap-guangzhou.myqcloud.com/file0483434390949466112.jpg;https://wall123-1301456511.cos.ap-guangzhou.myqcloud.com/file1483434390949466112.jpg;https://wall123-1301456511.cos.ap-guangzhou.myqcloud.com/file2483434390949466112.jpg;
     * realNameStatus : 1
     * location : 430200
     * realNameNum : 1
     * version : 3
     * provinceCode : 430000
     * provinceName : 湖南省
     * cityCode : 430200
     * cityName : 株洲市
     * locationName : 株洲市
     */

    private String id;
    private long createTime;
    private long updateTime;
    private int status;
    private String uid;
    private String name;
    private String idCard;
    private String img;
    private int realNameStatus;
    private String location;
    private int realNameNum;
    private String version;
    private String provinceCode;
    private String provinceName;
    private String cityCode;
    private String cityName;
    private String locationName;

    public String getId() {
        return id;
    }

    public long getCreateTime() {
        return createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public int getStatus() {
        return status;
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getIdCard() {
        return idCard;
    }

    public String getImg() {
        return img;
    }

    public int getRealNameStatus() {
        return realNameStatus;
    }

    public String getLocation() {
        return location;
    }

    public int getRealNameNum() {
        return realNameNum;
    }

    public String getVersion() {
        return version;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getAddress(){
        if(!TextUtils.isEmpty(provinceName) && !TextUtils.isEmpty(cityName) && !TextUtils.isEmpty(locationName))
            return provinceName + cityName + locationName;
        if(!TextUtils.isEmpty(provinceName) && !TextUtils.isEmpty(cityName))
            return provinceName + cityName ;
        if(!TextUtils.isEmpty(provinceName))
            return provinceName;
        return "";
    }
}
