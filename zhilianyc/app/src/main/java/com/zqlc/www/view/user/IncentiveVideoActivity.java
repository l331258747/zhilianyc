package com.zqlc.www.view.user;

import android.widget.TextView;

import com.zj.zjsdk.ad.ZjAdError;
import com.zj.zjsdk.ad.ZjRewardVideoAd;
import com.zj.zjsdk.ad.ZjRewardVideoAdListener;
import com.zqlc.www.R;
import com.zqlc.www.base.BaseActivity;
import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.mvp.ad.ReadVideoContract;
import com.zqlc.www.mvp.ad.ReadVideoPresenter;
import com.zqlc.www.util.log.LogUtil;

public class IncentiveVideoActivity extends BaseActivity implements ZjRewardVideoAdListener, ReadVideoContract.View {

    ZjRewardVideoAd rewardVideoAD;
    ReadVideoPresenter mPresenter;
    TextView tv_error;

    @Override
    public int getLayoutId() {
        return R.layout.acitivty_incentive_video;
    }

    @Override
    public void initView() {
        hideTitleLayout();
        tv_error = $(R.id.tv_error);

        rewardVideoAD = new ZjRewardVideoAd(activity, "zjad_3091624125775544", this);
        rewardVideoAD.setUserId("app_userID123456");

    }

    private boolean adLoaded;//广告加载成功标志
    private boolean videoCached;//视频素材文件下载完成标志

    @Override
    public void initData() {
        mPresenter = new ReadVideoPresenter(context,this);
        rewardVideoAD.loadAd();
    }

    /**
     * 广告展示出错
     */
    @Override
    public void onZjAdShowError(ZjAdError adError) {
        //adError.getErrorCode()
        //999001 "成功加载广告后再进行广告展示！"
        //999002 "此条广告已经展示过，请再次请求广告后进行广告展示！"
        //999003 "激励视频广告已过期，请再次请求广告后进行广告展示！"

        showStatus("onZjAdShowError:"+adError.getErrorCode()+"-"+adError.getErrorMsg());
        tv_error.setText("onZjAdShowError:"+adError.getErrorCode()+"-"+adError.getErrorMsg());
    }

    /**
     * 广告加载成功，可在此回调后进行广告展示
     **/


    @Override
    public void onZjAdLoaded(String adid) {
        adLoaded = true;
        showStatus("onZjAdLoad:广告加载成功，可在此回调后进行广告展示");
        if(adLoaded)
            rewardVideoAD.showAD();
    }

    /**
     * 视频素材缓存成功，可在此回调后进行广告展示
     */
    @Override
    public void onZjAdVideoCached() {

        videoCached = true;
        showStatus("onZjAdVideoCached:视频素材缓存成功，可在此回调后进行广告展示");
        if(adLoaded)
            rewardVideoAD.showAD();
    }

    /**
     * 激励视频广告页面展示
     */
    @Override
    public void onZjAdShow() {
        showStatus("onZjAdShow:激励视频广告页面展示");
    }



    /**
     * 激励视频广告曝光
     */
    @Override
    public void onZjAdExpose() {


        showStatus("onZjAdExpose:激励视频广告页面展示");
    }

    /**
     * 激励视频触发激励（观看视频大于一定时长或者视频播放完毕）
     */
    @Override
    public void onZjAdReward(String adid) {

        showStatus("onZjAdReward:激励视频触发激励（观看视频大于一定时长或者视频播放完毕）");
    }

    /**
     * 激励视频广告被点击
     */
    @Override
    public void onZjAdClick() {

        showStatus("onZjAdClick:激励视频广告被点击）");
    }

    /**
     * 激励视频播放完毕
     */
    @Override
    public void onZjAdVideoComplete() {
        showStatus("onZjAdVideoComplete:激励视频播放完毕）");
    }


    /**
     * 激励视频广告被关闭
     */
    @Override
    public void onZjAdClose() {
        showStatus("onZjAdClose:激励视频广告被关闭");
        mPresenter.viewVideoCallback();
        finish();
    }

    /**
     * 广告流程出错
     */
    @Override
    public void onZjAdError(ZjAdError adError) {
        showStatus("onZjAdError:"+adError.getErrorCode()+"-"+adError.getErrorMsg());
        tv_error.setText("onZjAdError:"+adError.getErrorCode()+"-"+adError.getErrorMsg());
    }

    private void showStatus(String msg){
        LogUtil.e(msg);
    }

    @Override
    public void viewVideoCallbackSuccess(EmptyModel datas) {

    }

    @Override
    public void viewVideoCallbackFailed(String msg) {

    }
}
