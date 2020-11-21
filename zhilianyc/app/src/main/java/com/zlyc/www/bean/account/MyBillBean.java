package com.zlyc.www.bean.account;

import java.util.ArrayList;
import java.util.List;

public class MyBillBean {


    /**
     * contribution : 270
     * contributionYesterday : 0
     * list : [{"time":"2020-11-06 15:16:11","source":"今日抽奖","count":10},{"time":"2020-11-06 10:40:08","source":null,"count":10},{"time":"2020-11-05 19:05:50","source":"今日抽奖","count":10}]
     */

    private float contribution;
    private float contributionYesterday;
    private List<MyBillListBean> list;


    public float getContribution() {
        return contribution;
    }

    public float getContributionYesterday() {
        return contributionYesterday;
    }

    public List<MyBillListBean> getList() {
        if(list == null) return new ArrayList<>();
        return list;
    }
}
