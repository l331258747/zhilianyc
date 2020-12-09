package com.zqlc.www.view.user;

import android.text.TextUtils;
import android.widget.TextView;

import com.mediamain.android.nativead.Ad;
import com.mediamain.android.view.FoxCustomerTm;
import com.mediamain.android.view.interfaces.FoxNsTmListener;
import com.zqlc.www.R;
import com.zqlc.www.base.BaseActivity;
import com.zqlc.www.bean.ConfigInfo;
import com.zqlc.www.bean.MySelfInfo;
import com.zqlc.www.util.GsonUtil;
import com.zqlc.www.util.log.LogUtil;

public class ExcitationActivity extends BaseActivity {

    private FoxCustomerTm mOxCustomerTm;


    private TextView mContainer;

    Ad ad;

    boolean isSignin;

    ExcitationBean mDataBean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_excitation;
    }

    @Override
    public void initView() {
        isSignin = intent.getBooleanExtra("isSignin", false);

        hideTitleLayout();

        String taAlotId = ConfigInfo.getInstance().getTaAwardAlotId();
        if (isSignin) {
            taAlotId = ConfigInfo.getInstance().getTaSiginAlotId();
        }

        mContainer = $(R.id.container);

        //创建广告对象
        mOxCustomerTm = new FoxCustomerTm(this);

        mOxCustomerTm.setAdListener(new FoxNsTmListener() {
            @Override
            public void onReceiveAd(String result) {
                //获取广告返回结果
                LogUtil.e("onReceiveAd:" + result);
                mDataBean = GsonUtil.convertString2Object(result,ExcitationBean.class);

                if (mOxCustomerTm!=null && mDataBean!=null && !TextUtils.isEmpty(mDataBean.getActivityUrl())){
                    //素材点击时候调用素材点击曝光方法
                    mOxCustomerTm.adClicked();
                    mOxCustomerTm.openFoxActivity(mDataBean.getActivityUrl());

                    mOxCustomerTm.adExposed();
                }
            }

            @Override
            public void onFailedToReceiveAd(int i, String s) {
                LogUtil.e("onFailedToReceiveAd");
                mContainer.setText("onFailedToReceiveAd" + "\n" + "i:" + i + "\n" + "s:" + s);
            }

            //增加奖励回调接口
            @Override
            public void onAdActivityClose(String s) {
                LogUtil.e("onAdActivityClose" + s);

                finish();
            }
        });
        //加载广告,传入对应的(必传)广告位id和用户id(可选,禁止以固定值填充相关引导,如果有发奖类活动userId是唯一标志)
        if (mOxCustomerTm != null) {
            try {
                mOxCustomerTm.loadAd(Integer.parseInt(taAlotId), MySelfInfo.getInstance().getUserId());
            } catch (Exception e) {
                LogUtil.e("Exception:" + e.toString());
            }
        }

    }

    @Override
    public void initData() {

    }


    @Override
    protected void onDestroy() {
        //销毁控件
        if (mOxCustomerTm != null) {
            mOxCustomerTm.destroy();
        }
        super.onDestroy();
    }
}
