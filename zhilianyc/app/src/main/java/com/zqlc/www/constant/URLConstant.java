package com.zqlc.www.constant;

/**
 * Created by LGQ
 * Time: 2018/7/17
 * Function: url常量
 */

public class URLConstant {

    public static final String BASE_URL = getUrl();//本地

    public static String getUrl(){
//        if(AppUtils.getVersionCodeInt() % 100 == 0){
//            return "http://180.101.185.20:38080/";
//        }else{
//            return "http://180.101.185.20:38080/";//测试地址
//        }
//        return "https://api.zqlc.net.cn/";
        return "http://180.101.185.20:38080/";
    }
}
