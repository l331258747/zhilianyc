package com.play.zhilianyc;

import android.app.Application;
import android.content.Context;

import com.play.zhilianyc.util.AppUtils;
import com.play.zhilianyc.util.SPUtils;
import com.play.zhilianyc.util.log.LogUtil;

/**
 * Created by LGQ
 * Time: 2018/7/17
 * Function:
 */

public class MyApplication extends Application {

    private static MyApplication instance;

    private static Context context;


    /**
     * 屏幕尺寸
     */
    public static int displayWidth = 0;
    public static int displayHeight = 0;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        context = getApplicationContext();

        if (displayWidth <= 0) {
            displayWidth = getResources().getDisplayMetrics().widthPixels;
        }

        if (displayHeight <= 0) {
            displayHeight = getResources().getDisplayMetrics().heightPixels;
        }

        //本地日志获取和bugly有冲突
//        ExceptionCrashHandler.getInstance().init(context);


        SPUtils.init(context);
        AppUtils.init(this);
        LogUtil.setShowLog(true);

    }

}
