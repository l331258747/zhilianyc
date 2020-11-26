package com.zqlc.www;

import android.app.Application;
import android.content.Context;

import com.mediamain.android.view.base.FoxSDK;
import com.zj.zjsdk.ZjSdk;
import com.zqlc.www.util.AppUtils;
import com.zqlc.www.util.SPUtils;
import com.zqlc.www.util.log.LogUtil;

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


        rewardInit();

        JLSPInit();
    }

    private void JLSPInit() {
        //激励视屏 TODO key
        ZjSdk.init(this,"zj_11120200724001");
        //String excludeProcess[] =new String[]{"com.xks.cartoon","com.xks.cartoon:****"};
        //excludeProcess 进程 不会初始化SDK
        //ZjSdk.init(this,"zj_1110616168",excludeProcess);
    }

    //抽奖 TODO key
    // android:value="kEzAJT4iRMMag29Z7yWcJGfcVgG"
    // FoxSDK.init(this,"","");
    //s:appkey s1：slotId  s2:userId  s3:deviceId
    private void rewardInit() {
        //在自定义的Application 的onCreate方法中，调用以下方法：（详细内容请参考demo中的代码示例）
        //基础SDK初始化
//        FoxSDK.init(this,appKey,appSecret);
        FoxSDK.init(this,"4UycwwZv41rwzne1ZXgtQBgDSnPH","3WpyTLfifQyGhvgivxtUjvzXxtkzdceETBU2n5g");
    }

    private void initRuishi() {

        VlionMulAdManager.getInstance().init(this).setAppid("50070")
                .setTid("5118785");//必选参数，瑞狮提供
        VlionNewsManager.getInstance().init(this);//必选参数
        //setUserReward true表示开启激励，false表示关闭激励
        VlionGameManager.getInstance().init(this).setUserReward(false).setXWebview(false);
    }

}
