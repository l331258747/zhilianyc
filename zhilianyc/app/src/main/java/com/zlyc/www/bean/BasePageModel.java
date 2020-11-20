package com.zlyc.www.bean;

import java.util.List;

/**
 * Created by LGQ
 * Time: 2018/9/12
 * Function:
 */

public class BasePageModel<T> {

    private  int total;
    private int current;
    private int pages;
    private int size;
    private List<T> list;


    public int getTotal() {
        return total;
    }

    public int getCurrent() {
        return current;
    }

    public int getPages() {
        return pages;
    }

    public int getSize() {
        return size;
    }

    public List<T> getList() {
        return list;
    }
}
