package com.zlyc.www.bean.coupon;

import java.util.List;

public class MyCouponBean {


    /**
     * output : 3
     * labor : 10
     * list : [{"totalCount":120,"time":"2020-10-28 01:14:57","dailyRelease":3,"labor":10,"remainDay":40,"beans":0,"name":"区县仓储","type":2}]
     */

    private int output;
    private int labor;
    private List<MyCouponList> list;


    public int getOutput() {
        return output;
    }

    public String getOutputStr() {
        return output + "京豆/天";
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

    public List<MyCouponList> getList() {
        return list;
    }
}
