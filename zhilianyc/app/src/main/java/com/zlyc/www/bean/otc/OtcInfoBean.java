package com.zlyc.www.bean.otc;

public class OtcInfoBean {


    /**
     * todayBuyNum : 100
     * todaySellNum : 0
     * todayTradeNum : 0
     * todayMaxPrice : 0
     * todayMinPrice : 0
     * lineData : {"tradeNums":[0,0,0,0,0,0],"categories":["11.15","11.16","11.17","11.18","11.19","11.20"]}
     * ndayMaxNum : 200
     */

    private float todayBuyNum;
    private float todaySellNum;
    private float todayTradeNum;
    private float todayMaxPrice;
    private float todayMinPrice;
    private OtcLineBean lineData;
    private float ndayMaxNum;

    public float getTodayBuyNum() {
        return todayBuyNum;
    }

    public float getTodaySellNum() {
        return todaySellNum;
    }

    public float getTodayTradeNum() {
        return todayTradeNum;
    }

    public float getTodayMaxPrice() {
        return todayMaxPrice;
    }

    public float getTodayMinPrice() {
        return todayMinPrice;
    }

    public OtcLineBean getLineData() {
        return lineData;
    }

    public float getNdayMaxNum() {
        return ndayMaxNum;
    }
}
