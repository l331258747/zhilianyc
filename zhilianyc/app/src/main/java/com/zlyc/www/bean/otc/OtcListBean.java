package com.zlyc.www.bean.otc;

import com.zlyc.www.util.StringUtils;

public class OtcListBean {


    /**
     * id : 525768965343940608
     * realName : 张忠平
     * headImg : null
     * status : 1
     * createTime : 2020-11-16 17:39:15
     * updateTime : null
     * sendId : null
     * sendName : null
     * sendAccount : null
     * receiveId : null
     * receiveName : null
     * unitPrice : 0.226
     * count : 48
     * fee : null
     * payImgUrl : null
     * sendStatus : null
     * version : 1
     * orderType : 0
     * lockUid : null
     */

    private String id;
    private String realName;
    private String headImg;
    private int status;
    private String createTime;
    private String updateTime;
    private String sendId;
    private String sendName;
    private String sendAccount;
    private String receiveId;
    private String receiveName;
    private double unitPrice;
    private int count;
    private String fee;
    private String payImgUrl;
    private String sendStatus;
    private int version;
    private int orderType;
    private String lockUid;

    public String getId() {
        return id;
    }

    public String getRealName() {
        return realName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public int getStatus() {
        return status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public String getSendId() {
        return sendId;
    }

    public String getSendName() {
        return sendName;
    }

    public String getSendAccount() {
        return sendAccount;
    }

    public String getReceiveId() {
        return receiveId;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }
    public String getUnitPriceStr() {
        return "单价：¥" + StringUtils.getStringNum(unitPrice);
    }

    public int getCount() {
        return count;
    }
    public String getCountStr() {
        return "数量：x" + count;
    }

    public String getFee() {
        return fee;
    }

    public String getPayImgUrl() {
        return payImgUrl;
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public int getVersion() {
        return version;
    }

    public int getOrderType() {
        return orderType;
    }

    public String getLockUid() {
        return lockUid;
    }
}
