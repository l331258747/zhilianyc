package com.zlyc.www.bean.coupon;

import android.text.TextUtils;

public class ShopCouponBean {


    /**
     * totalBeans : 12.0
     * dailyRelease : 0.3
     * day : 40
     * labor : 1.0
     * price : 10.0
     * name : 小镇仓储
     * hold : 0
     * max : 8
     * type : 1
     */

    private String totalBeans;
    private String dailyRelease;
    private String day;
    private String labor;
    private String price;
    private String name;
    private String hold;
    private String max;
    private String type;

    public String getTotalBeans() {
        return totalBeans;
    }

    public String getTotalBeansStr() {
        if(TextUtils.isEmpty(totalBeans))
            return "0";
        return totalBeans;
    }

    public String getDailyRelease() {
        return dailyRelease;
    }
    public String getDailyReleaseStr() {
        if(TextUtils.isEmpty(dailyRelease))
            return "";
        return dailyRelease+ "京豆/天";
    }

    public String getDay() {
        return day;
    }

    public String getDayStr() {
        if(TextUtils.isEmpty(day)){
            return "0天";
        }
        return day + "天";
    }

    public String getLabor() {
        return labor;
    }

    public String getLaborStr(){
        if(TextUtils.isEmpty(labor)){
            return "";
        }
        float laborInt;
        try{
            laborInt = Float.valueOf(labor);
        }catch (Exception e){
            laborInt = 0;
        }

        if(laborInt > 0){
            return "+" + laborInt;
        }else if(laborInt < 0){
            return "-" + laborInt;
        }else{
            return ""+laborInt;
        }
    }

    public String getPrice() {
        return price;
    }
    public String getPriceStr() {
        if(TextUtils.isEmpty(price))
            return "0京豆";
        return price + "京豆";
    }

    public String getName() {
        return name;
    }

    public String getHold() {
        return hold;
    }

    public String getMax() {
        return max;
    }

    public String getMaxHold(){
        if(TextUtils.isEmpty(max) && TextUtils.isEmpty(hold)){
            return "0/0";
        }else if(TextUtils.isEmpty(max)){
            return hold + "/0";
        }else if(TextUtils.isEmpty(hold)){
            return "0/" + max;
        }else {
            return hold + "/" + max;
        }
    }

    public String getType() {
        return type;
    }

    public int getTypeInt() {
        int typeInt;
        try{
            typeInt = Integer.parseInt(type);
        }catch (Exception e){
            typeInt = 0;
        }
        return typeInt;
    }
}
