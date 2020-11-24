package com.zqlc.www.bean.otc;

public class OtcDetailBean {


    /**
     * id : 525078532259319808
     * createTime : 2020-11-14 19:55:43
     * updateTime : 2020-11-14 20:35:40
     * status : 1
     * sendId : 434728571521470464
     * sendName : 15035860544
     * sendAccount : 15035860544
     * receiveId : 436640978615406592
     * receiveName : 16679096325
     * unitPrice : 0.3
     * count : 300
     * totalPrice : 90
     * fee : 75
     * payImgUrl : https://wall123-1301456511.cos.ap-guangzhou.myqcloud.com/payproof525087433549680640.jpg
     * sendStatus : 0
     * version : 2
     * orderType : 0
     * lockUid : 434728571521470464
     * countDownTime : 0
     */

    private String id;
    private String createTime;
    private String updateTime;
    private int status;
    private String sendId;
    private String sendName;
    private String sendAccount;
    private String receiveId;
    private String receiveName;
    private double unitPrice;
    private int count;
    private double totalPrice;
    private double fee;
    private String payImgUrl;
    private int sendStatus;
    private int version;
    private int orderType;
    private String lockUid;
    private long countDownTime;

    public String getId() {
        return id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public int getStatus() {
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getCount() {
        return count;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getFee() {
        return fee;
    }

    public String getPayImgUrl() {
        return payImgUrl;
    }

    public int getSendStatus() {
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

    public long getCountDownTime() {
        return countDownTime;
    }

    public String getName(){
        if(orderType == 0){
            return "求购单";
        }else{
            return "转让单";
        }
    }

    //0已成交1订单已发起2订单已锁定3卖方已放豆4买方已付款5卖方确认7卖方申诉中9用户撤回10系统撤回11系统解除申诉
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
}
