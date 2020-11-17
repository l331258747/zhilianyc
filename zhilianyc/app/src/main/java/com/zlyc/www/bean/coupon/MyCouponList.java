package com.zlyc.www.bean.coupon;

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

    private int totalCount;
    private String time;
    private int dailyRelease;
    private int labor;
    private int remainDay;
    private int beans;
    private String name;
    private int type;

    public int getTotalCount() {
        return totalCount;
    }

    public String getTotalCountStr() {
        return "总产量" + totalCount + "京豆";
    }

    public String getTime() {
        return time;
    }

    public int getDailyRelease() {
        return dailyRelease;
    }
    public String getDailyReleaseStr() {
        return dailyRelease + "京豆/天";
    }

    public int getLabor() {
        return labor;
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

    public int getBeans() {
        return beans;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }
}
