package com.zqlc.www.bean.otc;

public class MyOtcListBean {


    /**
     * id : 525078532259319808
     * createTime : 2020-11-14 19:55:43
     * updateTime : null
     * status : null
     * sendId : null
     * sendName : null
     * sendAccount : null
     * receiveId : null
     * receiveName : null
     * unitPrice : null
     * count : 300
     * totalPrice : null
     * fee : null
     * payImgUrl : null
     * sendStatus : 0
     * version : null
     * orderType : 0
     * lockUid : null
     * countDownTime : 0
     */

    private String id;
    private String createTime;
    private String updateTime;
    private String status;
    private String sendId;
    private String sendName;
    private String sendAccount;
    private String receiveId;
    private String receiveName;
    private String unitPrice;
    private int count;
    private String totalPrice;
    private String fee;
    private String payImgUrl;
    private int sendStatus;
    private String version;
    private int orderType;
    private String lockUid;
    private int countDownTime;

    public String getId() {
        return id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public String getStatus() {
        return status;
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

    public String getUnitPrice() {
        return unitPrice;
    }

    public int getCount() {
        return count;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public String getFee() {
        return fee;
    }

    public String getPayImgUrl() {
        return payImgUrl;
    }

    //0已成交1订单已发起2订单已锁定3卖方已放豆4买方已付款5卖方确认7卖方申诉中9用户撤回10系统撤回11系统解除申诉
    public int getSendStatus() {
        return sendStatus;
    }
    public String getSendStatusStr() {
        switch (sendStatus){
            case 0:
                return "已成交";
            case 1:
                return "订单已发起";
            case 2:
                return "订单已锁定";
            case 3:
                return "卖方已放豆";
            case 4:
                return "买方已付款";
            case 5:
                return "卖方确认";
//            case 6:
//                return "已成交";
            case 7:
                return "卖方申诉中";
//            case 8:
//                return "已成交";
            case 9:
                return "用户撤回";
            case 10:
                return "系统撤回";
            case 11:
                return "系统解除申诉";
        }
        return sendStatus + "";
    }

    public String getVersion() {
        return version;
    }

    public int getOrderType() {
        return orderType;
    }

    public String getName(){
        if(orderType == 0){
            return "求购单";
        }else{
            return "转让单";
        }
    }

    public String getLockUid() {
        return lockUid;
    }

    public int getCountDownTime() {
        return countDownTime;
    }
}
