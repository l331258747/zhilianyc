package com.zqlc.www.bean.message;

public class AppUpdateBean {


    /**
     * id : 5
     * createTime : 1593277549000
     * updateTime : 1599405703000
     * status : null
     * isForce : false
     * updateType : 0
     * wgtUrl : http://download.zhilianyc.cn/package/__UNI__AD05C76_2007.wgt|http://download.zhilianyc.cn/package/__UNI__AD05C76_2007.wgt
     * pkgUrl :
     * version : 2.1.1
     * features : 优化部分内容
     不要在此点击“立即更新”
     下载网址: http://download.zhilianyc.cn
     */

    private String id;
    private long createTime;
    private long updateTime;
    private String status;
    private boolean isForce;
    private int updateType;
    private String wgtUrl;
    private String pkgUrl;
    private String version;
    private String features;


    public String getId() {
        return id;
    }

    public long getCreateTime() {
        return createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public String getStatus() {
        return status;
    }

    public boolean isForce() {
        return isForce;
    }

    public int getUpdateType() {
        return updateType;
    }

    public String getWgtUrl() {
        return wgtUrl;
    }

    public String getPkgUrl() {
        return pkgUrl;
    }

    public String getVersion() {
        return version;
    }

    public String getFeatures() {
        return features;
    }
}
