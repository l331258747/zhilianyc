package com.zqlc.www.bean.otc;

import com.zqlc.www.util.StringUtils;

public class TradeRuleBean {


    /**
     * sell_big_max : 100
     * buy_big_max : 100
     * buy_small_max : 100
     * buy_small_min : 0.11
     * buy_big_min : 0.12
     * sell_small_min : 0.1
     * sell_small_max : 100
     * small_big_order_num : 50
     * sell_big_min : 0.1
     */

    private float sell_big_max;
    private float buy_big_max;
    private float buy_small_max;
    private float buy_small_min;
    private float buy_big_min;
    private float sell_small_min;
    private float sell_small_max;
    private float small_big_order_num;
    private float sell_big_min;

    public float getSell_big_max() {
        return sell_big_max;
    }

    public float getBuy_big_max() {
        return buy_big_max;
    }

    public float getBuy_small_max() {
        return buy_small_max;
    }

    public float getBuy_small_min() {
        return buy_small_min;
    }

    public float getBuy_big_min() {
        return buy_big_min;
    }

    public float getSell_small_min() {
        return sell_small_min;
    }

    public float getSell_small_max() {
        return sell_small_max;
    }

    public float getSmall_big_order_num() {
        return small_big_order_num;
    }

    public float getSell_big_min() {
        return sell_big_min;
    }




    public String getSell_big_max_str() {
        return StringUtils.getStringNum(sell_big_max);
    }

    public String getBuy_big_max_str() {
        return StringUtils.getStringNum(buy_big_max);
    }

    public String getBuy_small_max_str() {
        return StringUtils.getStringNum(buy_small_max);
        
    }

    public String getBuy_small_min_str() {
        return StringUtils.getStringNum(buy_small_min);
        
    }

    public String getBuy_big_min_str() {
        return StringUtils.getStringNum(buy_big_min);
        
    }

    public String getSell_small_min_str() {
        return StringUtils.getStringNum(sell_small_min);
        
    }

    public String getSell_small_max_str() {
        return StringUtils.getStringNum(sell_small_max);
        
    }

    public String getSmall_big_order_num_str() {
        return StringUtils.getStringNum(small_big_order_num);
        
    }

    public String getSell_big_min_str() {
        return StringUtils.getStringNum(sell_big_min);
        
    }


}
