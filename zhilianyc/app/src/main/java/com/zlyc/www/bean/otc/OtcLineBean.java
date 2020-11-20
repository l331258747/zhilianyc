package com.zlyc.www.bean.otc;

import java.util.List;

public class OtcLineBean {
    private List<Integer> tradeNums;
    private List<String> categories;

    public List<Integer> getTradeNums() {
        return tradeNums;
    }

    public void setTradeNums(List<Integer> tradeNums) {
        this.tradeNums = tradeNums;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}