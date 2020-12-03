package com.zqlc.www.view.security;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zqlc.www.R;
import com.zqlc.www.base.ActivityCollect;
import com.zqlc.www.base.BaseActivity;
import com.zqlc.www.bean.MySelfInfo;
import com.zqlc.www.bean.user.AuthRealInfoBean;
import com.zqlc.www.bean.user.AuthRealNameBean;
import com.zqlc.www.bean.user.AuthRealPayBean;
import com.zqlc.www.dialog.TextDialog;
import com.zqlc.www.mvp.my.AuthRealNameContract;
import com.zqlc.www.mvp.my.AuthRealNamePresenter;
import com.zqlc.www.mvp.my.RealNameStatusContract;
import com.zqlc.www.mvp.my.RealNameStatusPresenter;
import com.zqlc.www.util.LoginUtil;
import com.zqlc.www.util.pickerView.PickerCityHelp;
import com.zqlc.www.util.rxbus.RxBus2;
import com.zqlc.www.util.rxbus.busEvent.RegionSelEvent;
import com.zqlc.www.view.user.ListRegionActivity;

import java.net.URLEncoder;

import io.reactivex.disposables.Disposable;

public class AuthenticationActivity extends BaseActivity implements AuthRealNameContract.View, RealNameStatusContract.View {

    EditText et_real_name, et_code;
    View view_address;
    TextView tv_address, btn_submit;

    PickerCityHelp mPickerCityHelp;

    AuthRealNamePresenter mPresenter;
    RealNameStatusPresenter mPresenterStatus;
    Disposable disposable;

    @Override
    public int getLayoutId() {
        return R.layout.activity_authentication;
    }

    @Override
    public void initView() {
        showLeftAndTitle("实名认证");

        et_real_name = $(R.id.et_real_name);
        et_code = $(R.id.et_code);
        view_address = $(R.id.view_address);
        tv_address = $(R.id.tv_address);
        btn_submit = $(R.id.btn_submit);

        btn_submit.setOnClickListener(v -> {
            if (!LoginUtil.verifyName(et_real_name.getText().toString()))
                return;
            if (!LoginUtil.verifyID(et_code.getText().toString()))
                return;
            if (!LoginUtil.verifyEmpty(tv_address.getText().toString(), "请选择地区"))
                return;

            mPresenter.authRealPay(MySelfInfo.getInstance().getUserId(), et_real_name.getText().toString(), et_code.getText().toString(), locationCode);
        });

        view_address.setOnClickListener(v -> {
            startActivity(new Intent(context, ListRegionActivity.class));
        });
    }

    @Override
    public void initData() {
        inType = 0;
        mPresenter = new AuthRealNamePresenter(context, this);
        mPresenterStatus = new RealNameStatusPresenter(context, this);
        mPickerCityHelp = new PickerCityHelp(context);
        mPickerCityHelp.initData();

        disposable = RxBus2.getInstance().toObservable(RegionSelEvent.class, regionSelEvent -> {
            tv_address.setText(regionSelEvent.getAddressDes());
            locationCode = regionSelEvent.getpCode();
        });

        mPresenter.realNameInfo(MySelfInfo.getInstance().getUserId());
    }


    @Override
    public void authRealNameSuccess(AuthRealNameBean data) {
        //https://ida.webank.com/api/web/login?
        // webankAppId=${params.webankAppId}
        // &version=${params.version}
        // &nonce=${params.nonce}
        // &orderNo=${params.orderNo}
        // &h5faceId=${params.h5faceId}
        // &url=${params.url}
        // &userId=${params.userId}
        // &sign=${params.sign}
        // &from=${params.from}
        String url = "https://ida.webank.com/api/web/login?" +
                "webankAppId=" + data.getWebankAppId() +
                "&version=" + data.getVersion() +
                "&nonce=" + data.getNonce() +
                "&orderNo=" + data.getOrderNo() +
                "&h5faceId=" + data.getH5faceId() +
                "&url=" + data.getUrl() +
                "&userId=" + data.getUserId() +
                "&sign=" + data.getSign() +
                "&from=" + URLEncoder.encode(data.getUrl());

        inType = 2;

//        Intent intentRed = new Intent(context, WebViewActivity.class);
//        intentRed.putExtra(Constant.EXTRA_URL,url);
//        startActivity(intentRed);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        startActivity(Intent.createChooser(intent, "请选择浏览器"));

//        showShortToast("提交成功");
//        finish();
//        RxBus2.getInstance().post(new InformationEvent());
    }

    @Override
    public void authRealNameFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void authRealPaySuccess(AuthRealPayBean data) {
        //判断是否支付的状态码,1、未支付，此时authRealNameVO.payUrl是去支付的链接2、已支付但未实名，得到此状态直接发起实名
        if (data.getCode() == 1) {
            inType = 1;

//            Intent intentRed = new Intent(context, WebViewActivity.class);
//            intentRed.putExtra(Constant.EXTRA_URL,data.getAuthRealNameVO().getPayUrl());
//            startActivity(intentRed);

            //从其他浏览器打开
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            Uri content_url = Uri.parse(data.getAuthRealNameVO().getPayUrl());
            intent.setData(content_url);
            startActivity(Intent.createChooser(intent, "请选择浏览器"));
        } else if (data.getCode() == 2) {
            if (!LoginUtil.verifyName(et_real_name.getText().toString()))
                return;
            if (!LoginUtil.verifyID(et_code.getText().toString()))
                return;
            if (!LoginUtil.verifyEmpty(tv_address.getText().toString(), "请选择地区"))
                return;

            mPresenter.authRealName(MySelfInfo.getInstance().getUserId(), et_real_name.getText().toString(), et_code.getText().toString(), locationCode);
        }
    }

    @Override
    public void authRealPayFailed(String msg) {
        showShortToast(msg);
    }

    String locationCode;

    @Override
    public void realNameInfoSuccess(AuthRealInfoBean data) {
        if(data == null) return;
        if(!TextUtils.isEmpty(data.getName())){
            et_real_name.setText(data.getName());
            et_real_name.setFocusable(false);
            et_real_name.setFocusableInTouchMode(false); // user touches widget on phone with touch screen
            et_real_name.setClickable(false); // user navigates with wheel and selects widget
        }
        if(!TextUtils.isEmpty(data.getIdCard())){
            et_code.setText(data.getIdCard());
            et_code.setFocusable(false);
            et_code.setFocusableInTouchMode(false); // user touches widget on phone with touch screen
            et_code.setClickable(false); // user navigates with wheel and selects widget
        }
        if(!TextUtils.isEmpty(data.getAddress())){
            tv_address.setText(data.getAddress());
            tv_address.setFocusable(false);
            tv_address.setFocusableInTouchMode(false); // user touches widget on phone with touch screen
            tv_address.setClickable(false); // user navigates with wheel and selects widget

            locationCode = data.getLocation();
        }
    }

    @Override
    public void realNameInfoFailed(String msg) {
        showShortToast(msg);
    }

    int inType;
    @Override
    protected void onResume() {
        super.onResume();
        if(inType == 1){
            new TextDialog(context).setContent("如果您已支付，确认后将启动 H5 人脸核身").setSubmitListener(v -> {
                if (!LoginUtil.verifyName(et_real_name.getText().toString()))
                    return;
                if (!LoginUtil.verifyID(et_code.getText().toString()))
                    return;
                if (!LoginUtil.verifyEmpty(tv_address.getText().toString(), "请选择地区"))
                    return;

                mPresenter.authRealPay(MySelfInfo.getInstance().getUserId(), et_real_name.getText().toString(), et_code.getText().toString(), locationCode);
            }).show();
        }else if(inType == 2){
            mPresenterStatus.realNameStatus(MySelfInfo.getInstance().getUserId());
        }

    }

    @Override
    public void realNameStatusSuccess(String data) {
        //0 审核不通过 1 已实名 2 审核中 3 未认证

        if (TextUtils.equals(data, "1")) {
            new TextDialog(context).setContent("认证成功").setSubmitListener(v -> {
                ActivityCollect.getAppCollect().finishAllNotHome();
            }).show();
        }
    }

    @Override
    public void realNameStatusFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }
}
