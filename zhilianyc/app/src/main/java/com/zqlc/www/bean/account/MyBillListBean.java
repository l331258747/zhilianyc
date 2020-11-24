package com.zqlc.www.bean.account;

import com.zqlc.www.util.StringUtils;

public class MyBillListBean {
    /**
     * time : 2020-11-06 15:16:11
     * source : 今日抽奖
     * count : 10
     */

    private String time;
    private String source;
    private float count;

    public String getTime() {
        return time;
    }

    public String getSource() {
        return source;
    }

    public float getCount() {
        return count;
    }

    public String getCountStr(){
        if (count > 0) {
            return "+" + StringUtils.getStringNum(count);
        } else if (count < 0) {
            return "-" + StringUtils.getStringNum(count);
        } else {
            return StringUtils.getStringNum(count);
        }
    }
}