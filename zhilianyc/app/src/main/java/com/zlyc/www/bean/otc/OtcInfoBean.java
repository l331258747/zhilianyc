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

    private int todayBuyNum;
    private int todaySellNum;
    private int todayTradeNum;
    private int todayMaxPrice;
    private int todayMinPrice;
    private OtcLineBean lineData;
    private int ndayMaxNum;

    public int getTodayBuyNum() {
        return todayBuyNum;
    }

    public void setTodayBuyNum(int todayBuyNum) {
        this.todayBuyNum = todayBuyNum;
    }

    public int getTodaySellNum() {
        return todaySellNum;
    }

    public void setTodaySellNum(int todaySellNum) {
        this.todaySellNum = todaySellNum;
    }

    public int getTodayTradeNum() {
        return todayTradeNum;
    }

    public void setTodayTradeNum(int todayTradeNum) {
        this.todayTradeNum = todayTradeNum;
    }

    public int getTodayMaxPrice() {
        return todayMaxPrice;
    }

    public void setTodayMaxPrice(int todayMaxPrice) {
        this.todayMaxPrice = todayMaxPrice;
    }

    public int getTodayMinPrice() {
        return todayMinPrice;
    }

    public void setTodayMinPrice(int todayMinPrice) {
        this.todayMinPrice = todayMinPrice;
    }

    public OtcLineBean getLineData() {
        return lineData;
    }

    public void setLineData(OtcLineBean lineData) {
        this.lineData = lineData;
    }

    public int getNdayMaxNum() {
        return ndayMaxNum;
    }

    public void setNdayMaxNum(int ndayMaxNum) {
        this.ndayMaxNum = ndayMaxNum;
    }
}
