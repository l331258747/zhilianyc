package com.zqlc.www.view.user;

import android.content.res.Configuration;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import com.mediamain.android.nativead.Ad;
import com.mediamain.android.nativead.AdCallBack;
import com.zqlc.www.R;
import com.zqlc.www.base.BaseActivity;
import com.zqlc.www.bean.ConfigInfo;
import com.zqlc.www.bean.MySelfInfo;
import com.zqlc.www.util.log.LogUtil;

//public class ExcitationActivity extends BaseActivity implements AwardContract.View, SigninContract.View {
public class ExcitationActivity extends BaseActivity {

    private FrameLayout mContainer;
//    AwardPresenter mPresenter;
//    SigninPresenter mPresenterSignin;

    Ad ad;

    boolean isSignin;

    @Override
    public int getLayoutId() {
        return R.layout.activity_excitation;
    }

    @Override
    public void initView() {
        isSignin = intent.getBooleanExtra("isSignin",false);

        String title = "今日抽奖";
        if(isSignin){
            title = "今日签到";
        }

        showLeftAndTitle(title);
        mContainer = $(R.id.container);

    }

    @Override
    public void initData() {


//        mPresenter = new AwardPresenter(context,this);
//        mPresenterSignin = new SigninPresenter(context,this);

        initAd();
        //点击某个按钮展示广告或者进入页面直接展现广告
        ad.loadAd(activity, false);
    }

    private void initAd() {
        //s:appkey s1：slotId  s2:userId  s3:deviceId

        String taAlotId = ConfigInfo.getInstance().getTaAwardAlotId();
        if(isSignin){
            taAlotId = ConfigInfo.getInstance().getTaSiginAlotId();
        }

        ad = new Ad(ConfigInfo.getInstance().getTaAppKey(), taAlotId, MySelfInfo.getInstance().getUserId());
        ad.init(activity, mContainer, Ad.AD_URL_NEW, new AdCallBack()     {

            @Override
            public void onReceiveAd() {
                //活动弹窗关闭回调 只针对弹窗类型广告有效
                LogUtil.e( "onReceiveAd");
            }

            @Override
            public void onFailedToReceiveAd(int i, String s) {
                //活动弹窗关闭回调 只针对弹窗类型广告有效
                LogUtil.e( "onFailedToReceiveAd");
            }

            @Override
            public void onActivityClose() {
                //活动弹窗关闭回调 只针对弹窗类型广告有效
                LogUtil.e( "onActivityClose");
            }

            @Override
            public void onActivityShow() {
                //活动弹窗show回调 只针对弹窗类型广告有效
                LogUtil.e( "onActivityShow");
            }

            @Override
            public void onRewardClose() {
                //奖励弹窗关闭回调
                LogUtil.e( "onRewardClose");
            }

            @Override
            public void onRewardShow() {
                //奖励弹窗show回调
                LogUtil.e( "onRewardShow");

//                mPresenter.awardCallback();
            }

            @Override
            public void onPrizeClose() {
                //我的奖品页弹窗关闭回调
                LogUtil.e( "onPrizeClose");
            }

            @Override
            public void onPrizeShow() {
                //我的奖品页弹窗show回调
                LogUtil.e( "onPrizeShow");
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (ad != null) {
            ad.resetAdSize(newConfig.orientation);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean isConsume = false;
        if (ad != null) {
            isConsume = ad.onKeyBack(keyCode, event);
        }
        if (!isConsume) {
            return super.onKeyDown(keyCode, event);
        }
        return isConsume;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (ad != null) {
            ad.destroy();
        }
    }


//    @Override
//    public void awardCallbackSuccess(EmptyModel datas) {
//
//    }
//
//    @Override
//    public void awardCallbackFailed(String msg) {
//
//    }
//
//
//    @Override
//    public void signinSuccess(String data) {
//        showShortToast("签到成功");
//    }
//
//    @Override
//    public void signinFailed(String msg) {
//        showShortToast(msg);
//    }
}
