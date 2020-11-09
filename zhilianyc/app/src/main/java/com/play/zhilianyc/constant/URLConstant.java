package com.play.zhilianyc.constant;

import com.play.zhilianyc.util.AppUtils;

/**
 * Created by LGQ
 * Time: 2018/7/17
 * Function: url常量
 */

public class URLConstant {

    public static final String BASE_URL = getUrl();//本地

    public static final String SHARE_GUIDE = BASE_URL+"/share/guideEscortData.html";
    public static final String SHARE_DYNAMIC = BASE_URL+"/share/moveDeta.html";
    public static final String SHARE_SERVER = BASE_URL+"/share/guideDetaServe.html";
    public static final String SHARE_ACTIVITY = BASE_URL+"/share/eventShare.html";

    public static String getUrl(){
        if(AppUtils.getVersionCodeInt() % 100 == 0){
            return "http://www.njzou.net/travel-framework/";
        }else{
//            return "http://192.168.100.115:8089/";//本地 //18826420934  //941740
//            return "http://www.njzou.cn/travel-framework/";//测试地址
            return "http://114.115.161.62/travel-framework/";//测试地址



//            return "http://www.njzou.net/travel-framework/";

        }
    }
}
