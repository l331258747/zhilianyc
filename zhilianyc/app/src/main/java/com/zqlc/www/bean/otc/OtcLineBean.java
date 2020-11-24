package com.zqlc.www.bean.otc;

import java.util.List;

public class OtcLineBean {
    private List<Float> tradeNums;
    private List<String> categories;

    public List<Float> getTradeNums() {
        return tradeNums;
    }

    public List<String> getCategories() {
        return categories;
    }
}