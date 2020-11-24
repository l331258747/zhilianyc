package com.zqlc.www.bean.coupon;

public class MyCouponList {


    /**
     * totalCount : 120
     * time : 2020-10-28 01:14:57
     * dailyRelease : 3
     * labor : 10
     * remainDay : 40
     * beans : 0
     * name : 区县仓储
     * type : 2
     */

    private float totalCount;
    private String time;
    private float dailyRelease;
    private float labor;
    private int remainDay;
    private float beans;
    private String name;
    private int type;

    public float getTotalCount() {
        return totalCount;
    }

    public float getDailyRelease() {
        return dailyRelease;
    }

    public float getLabor() {
        return labor;
    }

    public float getBeans() {
        return beans;
    }

    public String getTotalCountStr() {
        return "总产量" + totalCount + "京豆";
    }

    public String getTime() {
        return time;
    }

    public String getDailyReleaseStr() {
        return dailyRelease + "京豆/天";
    }

    public String getLaborStr() {
        if(labor > 0){
            return "+" + labor;
        }else if(labor < 0){
            return "-" + labor;
        }else{
            return ""+labor;
        }
    }

    public int getRemainDay() {
        return remainDay;
    }
    public String getRemainDayStr() {
        return "剩余" + remainDay + "天";
    }


    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }
}
