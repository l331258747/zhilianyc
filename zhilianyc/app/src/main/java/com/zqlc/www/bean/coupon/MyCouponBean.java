package com.zqlc.www.bean.coupon;

import java.util.ArrayList;
import java.util.List;

public class MyCouponBean {


    /**
     * output : 3
     * labor : 10
     * list : [{"totalCount":120,"time":"2020-10-28 01:14:57","dailyRelease":3,"labor":10,"remainDay":40,"beans":0,"name":"区县仓储","type":2}]
     */

    private float output;
    private float labor;
    private List<MyCouponList> list;


    public float getOutput() {
        return output;
    }

    public float getLabor() {
        return labor;
    }

    public String getOutputStr() {
        return output + "京豆/天";
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
        if(list == null) return new ArrayList<>();
        return list;
    }
}
