package com.zqlc.www.constant;

/**
 * Created by LGQ
 * Time: 2018/7/19
 * Function: 常量
 */

public class Constant {

    //文件路径
    public static final String BASE_PATH = "zhilianyc";
    public static final String LOG_PATH = "log";
    public static final String IMAGE_PATH = "image";

    //banner图默认时间 轮播
    public static final int BANNER_RUNNING_TIME = 4000;

    //分页默认参数
    public static final int DEFAULT_SIZE = 10;
    public static final int DEFAULT_PAGE = 1;

    //我的-tab
    public static final int MY_TAB_TEAM = 1;
    public static final int MY_TAB_SECURITY = 2;
    public static final int MY_TAB_ORDER = 3;
    public static final int MY_TAB_TRANSACTION_DTS = 4;

    public static final int MY_TAB_INVITAT = 5;
    public static final int MY_TAB_STUDIO = 6;
    public static final int MY_TAB_NOTIFY = 7;
    public static final int MY_TAB_CHANGE = 8;

    public static final int MY_TAB_CLOUD = 9;
    public static final int MY_TAB_SHOP = 10;
    public static final int MY_TAB_RED_PACKAGE = 11;
    public static final int MY_TAB_TASK = 12;


    //realName 0:审核不通过,1：已实名,2：审核中,3：未实名
    //otc sendStatus 0已成交1订单已发起2订单已锁定3卖方已放豆4买方已付款5卖方确认7卖方申诉中9用户撤回10系统撤回11系统解除申诉
    //orderType
    //TYPE_CREATE(1, "待付款"),
    //TYPE_PAID(2, "已支付待发货"),
    //TYPE_DELIVER(3, "已发货待收货"),
    //TYPE_RECEIVE(4, "已收货"),
    //TYPE_CANCEL(5, "已取消"),
    //TYPE_REFUND(6, "已退款");

    /**
     * url 跳转链接
     **/
    public static final String EXTRA_URL = "url";
    public static final String EXTRA_TITLE = "title";
    public static final String IS_USE_WIDE_VIEW_PORT = "isUseWideViewPort";

}
