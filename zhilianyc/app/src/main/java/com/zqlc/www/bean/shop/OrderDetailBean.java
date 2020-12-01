package com.zqlc.www.bean.shop;

import android.text.TextUtils;

import com.zqlc.www.util.StringUtils;

public class OrderDetailBean {


    /**
     * id : 1326795072288624641
     * imgUrl : https://wall123-1301456511.cos.ap-guangzhou.myqcloud.com/518440481630195712.png
     * name : 小白取暖器立卧两用暖风机小型电暖气
     * num : 1
     * price : 450
     * sum : 450
     * totalSum : 570
     * type : 1
     * postage : 120
     * receiveName : 周凯
     * receiveMobile : 15616367868
     * receiveAddress : 湖南省株洲市株洲县渌口镇渌口村
     * deliverOrder : null
     * deliverCompany : null
     * createTime : 2020-11-12 15:52:50
     * payTime : null
     * deliverTime : null
     * receiveTime : null
     * cancelTime : null
     * refundTime : null
     * autoCancel : 30
     * autoReceive : 7
     */

    private String id;
    private String imgUrl;
    private String name;
    private int num;
    private float price;
    private float sum;
    private float totalSum;
    private int type;
    private float postage;
    private String receiveName;
    private String receiveMobile;
    private String receiveAddress;
    private String deliverOrder;
    private String deliverCompany;
    private String createTime;
    private String payTime;
    private String deliverTime;
    private String receiveTime;
    private String cancelTime;
    private String refundTime;
    private int autoCancel;
    private int autoReceive;

    public String getId() {
        return id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getName() {
        return name;
    }

    public int getNum() {
        return num;
    }


    public int getType() {
        return type;
    }

    public float getPrice() {
        return price;
    }

    public float getSum() {
        return sum;
    }

    public String getSumStr() {
        return StringUtils.getStringNum(sum) + "金豆";
    }

    public float getTotalSum() {
        return totalSum;
    }

    public String getTotalSumStr() {
        return StringUtils.getStringNum(totalSum)  + "金豆";
    }

    public float getPostage() {
        return postage;
    }

    public String getPostageStr() {
        return StringUtils.getStringNum(postage)  + "金豆";
    }

    public String getReceiveName() {
        return receiveName;
    }

    public String getReceiveMobile() {
        return receiveMobile;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public String getDeliverOrder() {
        return deliverOrder;
    }

    public String getDeliverCompany() {
        return deliverCompany;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getPayTime() {
        if(TextUtils.isEmpty(payTime)) return "未付款";
        return payTime;
    }

    public String getDeliverTime() {
        if(TextUtils.isEmpty(deliverTime)) return "未发货";
        return deliverTime;
    }

    public String getReceiveTime() {
        if(TextUtils.isEmpty(receiveTime)) return "未确认收货";
        return receiveTime;
    }

    public String getCancelTime() {
        return cancelTime;
    }

    public String getRefundTime() {
        return refundTime;
    }

    public int getAutoCancel() {
        return autoCancel;
    }

    public int getAutoReceive() {
        return autoReceive;
    }

    public String getTypeStr() {
        switch (type){
            case 1:
                return "待付款";
            case 2:
                return "待发货";
            case 3:
                return "待收货";
            case 4:
                return "已收货";
            case 5:
                return "已取消";
            case 6:
                return "已退款";
        }
        return "" + type;
    }

    public String getNumStr() {
        return "数量："+num+"件";
    }

    public String getPriceStr() {
        return "单价："+ StringUtils.getStringNum(price) +"金豆";
    }
}
