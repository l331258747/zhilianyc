package com.zlyc.www;

import android.app.Application;
import android.content.Context;

import com.zlyc.www.util.AppUtils;
import com.zlyc.www.util.SPUtils;
import com.zlyc.www.util.log.LogUtil;

import vlion.cn.game.core.VlionGameManager;
import vlion.cn.manager.core.VlionMulAdManager;
import vlion.cn.news.core.VlionNewsManager;

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

    //@Override
    //    protected void attachBaseContext(Context base) {
    //        super.attachBaseContext(base);
    //        MultiDex.install(this);
    //    }

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

        initRuishi();
    }

    private void initRuishi() {

        VlionMulAdManager.getInstance().init(this).setAppid("50070")
                .setTid("5118785");//必选参数，瑞狮提供
        VlionNewsManager.getInstance().init(this);//必选参数
        //setUserReward true表示开启激励，false表示关闭激励
        VlionGameManager.getInstance().init(this).setUserReward(false).setXWebview(false);
    }

}
