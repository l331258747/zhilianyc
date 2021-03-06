package com.zqlc.www.view.login;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zqlc.www.R;
import com.zqlc.www.base.BaseActivity;
import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.dialog.VerifyDialog;
import com.zqlc.www.mvp.login.ForgetPwdContract;
import com.zqlc.www.mvp.login.ForgetPwdPresenter;
import com.zqlc.www.mvp.my.SendCodeContract;
import com.zqlc.www.mvp.my.SendCodePresenter;
import com.zqlc.www.util.LoginUtil;
import com.zqlc.www.util.StringUtils;
import com.zqlc.www.util.rxbus.RxBus2;
import com.zqlc.www.util.rxbus.busEvent.SetLoginMobileEvent;

import java.util.concurrent.TimeUnit;

import androidx.core.content.ContextCompat;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class ForgetActivity extends BaseActivity implements View.OnClickListener, ForgetPwdContract.View, SendCodeContract.View {

    EditText et_phone,et_verify,et_pwd;
    TextView tv_verify_code,btn_submit;

    ForgetPwdPresenter mPresenter;
    SendCodePresenter mPresenterCode;

    @Override
    public int getLayoutId() {
        return R.layout.activity_forget;
    }

    @Override
    public void initView() {
        showLeftAndTitle("忘记密码");

        et_phone = $(R.id.et_phone);
        et_verify = $(R.id.et_verify);
        et_pwd = $(R.id.et_pwd);
        tv_verify_code = $(R.id.tv_verify_code);
        btn_submit = $(R.id.btn_submit);

        btn_submit.setOnClickListener(this);
        tv_verify_code.setOnClickListener(this);

        String mobile = intent.getStringExtra("mobile");

        if(!TextUtils.isEmpty(mobile))
            et_phone.setText(mobile);
    }

    @Override
    public void initData() {
        mPresenter = new ForgetPwdPresenter(context,this);
        mPresenterCode = new SendCodePresenter(context,this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_verify_code:
                if (!LoginUtil.verifyPhone(et_phone.getText().toString()))
                    return;
                new VerifyDialog(context).setSubmitListener(() -> {
                    verifyEvent();
                    mPresenterCode.sendCode(et_phone.getText().toString());
                }).show();
                break;
            case R.id.btn_submit:
                if (!LoginUtil.verifyPhone(et_phone.getText().toString()))
                    return;
                if (!LoginUtil.verifyVerify(et_verify.getText().toString()))
                    return;
                if (!LoginUtil.verifyPassword(et_pwd.getText().toString()))
                    return;

                mPresenter.forgetPwd(et_phone.getText().toString(),et_verify.getText().toString(),et_pwd.getText().toString());
                break;

        }
    }

    Disposable disposable;

    private void verifyEvent() {
        final int count = 60;//倒计时10秒
        Observable.interval(0, 1, TimeUnit.SECONDS)//设置0延迟，每隔一秒发送一条数据
                .take(count + 1)//设置循环次数
                .map(aLong -> count - aLong)
                .doOnSubscribe(disposable -> {
                    tv_verify_code.setEnabled(false);//在发送数据的时候设置为不能点击
                    tv_verify_code.setTextColor(ContextCompat.getColor(context, R.color.color_66));
                })
                .observeOn(AndroidSchedulers.mainThread())//ui线程中进行控件更新
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(Long num) {
                        StringUtils.setHtml(tv_verify_code, String.format(getResources().getString(R.string.verify), num));
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        //回复原来初始状态
                        tv_verify_code.setEnabled(true);
                        tv_verify_code.setText("重新发送");
                        tv_verify_code.setTextColor(ContextCompat.getColor(context, R.color.white));
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }

    @Override
    public void forgetPwdSuccess(EmptyModel data) {
        showShortToast("修改成功");
        finish();

        RxBus2.getInstance().post(new SetLoginMobileEvent(et_phone.getText().toString()));
    }

    @Override
    public void forgetPwdFailed(String msg) {
        showLongToast(msg);
    }

    @Override
    public void sendCodeSuccess(EmptyModel data) {
        showShortToast("手机验证码发送成功");
    }

    @Override
    public void sendCodeFailed(String msg) {
        showShortToast(msg);
    }
}
