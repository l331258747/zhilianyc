package com.zlyc.www.view.login;

import android.content.Intent;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zlyc.www.R;
import com.zlyc.www.base.BaseActivity;
import com.zlyc.www.bean.EmptyModel;
import com.zlyc.www.bean.MySelfInfo;
import com.zlyc.www.bean.login.LoginBean;
import com.zlyc.www.mvp.login.LoginContract;
import com.zlyc.www.mvp.login.LoginPresenter;
import com.zlyc.www.util.AppUtils;
import com.zlyc.www.util.LoginUtil;
import com.zlyc.www.util.StatusBarUtil;
import com.zlyc.www.util.StringUtils;
import com.zlyc.www.util.rxbus.RxBus2;
import com.zlyc.www.util.rxbus.busEvent.SetLoginMobileEvent;
import com.zlyc.www.view.home.HomeActivity;

import java.util.concurrent.TimeUnit;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginContract.View {

    TextView tv_title, tv_register, tv_login, btn_login, btn_forget;
    LinearLayout tab_login, tab_register;
    View line_register, line_login;
    EditText et_phone, et_password;

    ConstraintLayout cl_register, cl_login;

    EditText et_phone_rgt, et_verify_rgt, et_password_rgt, et_password_rgt2, et_invite;
    TextView tv_verify_code_rgt, btn_register;
    LinearLayout ll_check;
    ImageView iv_check;

    boolean isCheck;

    LoginPresenter mPresenter;

    Disposable setMobileDisposable;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

        StatusBarUtil.setStatusBar(this, ContextCompat.getColor(context, R.color.color_1C81E9));

        hideTitleLayout();

        tv_title = $(R.id.tv_title);
        cl_register = $(R.id.cl_register);
        cl_login = $(R.id.cl_login);

        //注册
        et_phone_rgt = $(R.id.et_phone_rgt);
        et_verify_rgt = $(R.id.et_verify_rgt);
        et_password_rgt = $(R.id.et_password_rgt);
        et_password_rgt2 = $(R.id.et_password_rgt2);
        et_invite = $(R.id.et_invite);
        tv_verify_code_rgt = $(R.id.tv_verify_code_rgt);
        btn_register = $(R.id.btn_register);
        ll_check = $(R.id.ll_check);
        iv_check = $(R.id.iv_check);

        tv_verify_code_rgt.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        ll_check.setOnClickListener(this);

        //登录
        line_login = $(R.id.line_login);
        line_register = $(R.id.line_register);
        tab_login = $(R.id.tab_login);
        tab_register = $(R.id.tab_register);
        tv_register = $(R.id.tv_register);
        tv_login = $(R.id.tv_login);

        et_password = $(R.id.et_password);
        et_phone = $(R.id.et_phone);

        btn_login = $(R.id.btn_login);
        btn_forget = $(R.id.btn_forget);

        tab_login.setOnClickListener(this);
        tab_register.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        btn_forget.setOnClickListener(this);

        testLogin();

    }

    public void testLogin() {
        et_phone.setText("15616397868");
        et_password.setText("123456");
    }

    @Override
    public void initData() {
        tv_title.setText(String.format("智链云仓(%1$s)", AppUtils.getVersionName()));

        mPresenter = new LoginPresenter(context, this);

        if (MySelfInfo.getInstance().isLogin()) {
            startActivity(new Intent(context, HomeActivity.class));
            finish();
        }

        setMobileDisposable = RxBus2.getInstance().toObservable(SetLoginMobileEvent.class, new Consumer<SetLoginMobileEvent>() {
            @Override
            public void accept(SetLoginMobileEvent setLoginMobileEvent) throws Exception {
                et_phone.setText(setLoginMobileEvent.getMobile());
                et_password.setText("");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab_login:
                setTab(true);
                break;
            case R.id.tab_register:
                setTab(false);
                break;
            case R.id.btn_login:
                if (!LoginUtil.verifyPhone(et_phone.getText().toString()))
                    return;
                if (!LoginUtil.verifyPassword(et_password.getText().toString()))
                    return;

                mPresenter.login(et_phone.getText().toString(), et_password.getText().toString());

                break;
            case R.id.btn_forget:
                intent = new Intent(context,ForgetActivity.class);
                intent.putExtra("mobile",et_phone.getText().toString());
                startActivity(intent);
                break;
            case R.id.tv_verify_code_rgt:
                verifyEvent();
                break;
            case R.id.btn_register:
                if (!LoginUtil.verifyPhone(et_phone_rgt.getText().toString()))
                    return;
                if (!LoginUtil.verifyVerify(et_verify_rgt.getText().toString()))
                    return;
                if (!LoginUtil.verifyPassword(et_password_rgt.getText().toString()))
                    return;
                if (!LoginUtil.verifyPassword(et_password_rgt2.getText().toString()))
                    return;
                if (!LoginUtil.verifyPasswordDouble(et_password_rgt.getText().toString(), et_password_rgt2.getText().toString()))
                    return;
                if (!LoginUtil.verifyEmpty(et_invite.getText().toString(), "请输入邀请码"))
                    return;
                if(!isCheck){
                    showShortToast("请勾选隐私协议");
                    return;
                }

                mPresenter.register(et_phone_rgt.getText().toString()
                        , et_verify_rgt.getText().toString()
                        , et_password_rgt.getText().toString()
                        , et_invite.getText().toString());
                break;
            case R.id.ll_check:
                isCheck = !isCheck;
                iv_check.setImageResource(isCheck ? R.mipmap.ic_check_on : R.mipmap.ic_check_un);
                break;

        }
    }

    public void setTab(boolean isLogin) {
        tv_login.setTextColor(ContextCompat.getColor(context, R.color.color_99));
        tv_login.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        line_login.setVisibility(View.INVISIBLE);
        tv_register.setTextColor(ContextCompat.getColor(context, R.color.color_99));
        tv_register.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        line_register.setVisibility(View.INVISIBLE);
        cl_login.setVisibility(View.GONE);
        cl_register.setVisibility(View.GONE);

        if (isLogin) {
            tv_login.setTextColor(ContextCompat.getColor(context, R.color.color_text));
            tv_login.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            line_login.setVisibility(View.VISIBLE);
            cl_login.setVisibility(View.VISIBLE);

        } else {
            tv_register.setTextColor(ContextCompat.getColor(context, R.color.color_text));
            tv_register.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            line_register.setVisibility(View.VISIBLE);
            cl_register.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void loginSuccess(LoginBean data) {
        MySelfInfo.getInstance().setLoginData(data, et_phone.getText().toString());
        startActivity(new Intent(context, HomeActivity.class));
        finish();
    }

    @Override
    public void loginFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void registerSuccess(EmptyModel data) {
        setTab(true);
        et_phone.setText(et_phone_rgt.getText().toString());

        isCheck = false;
        iv_check.setImageResource(isCheck ? R.mipmap.ic_check_on : R.mipmap.ic_check_un);
        et_phone_rgt.setText("");
        et_verify_rgt.setText("");
        et_password_rgt.setText("");
        et_password_rgt2.setText("");
        et_invite.setText("");
    }

    @Override
    public void registerFailed(String msg) {
        showShortToast(msg);
    }


    Disposable disposable;

    private void verifyEvent() {
        final int count = 60;//倒计时10秒
        Observable.interval(0, 1, TimeUnit.SECONDS)//设置0延迟，每隔一秒发送一条数据
                .take(count + 1)//设置循环次数
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return count - aLong;
                    }
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        tv_verify_code_rgt.setEnabled(false);//在发送数据的时候设置为不能点击
                        tv_verify_code_rgt.setTextColor(ContextCompat.getColor(context, R.color.color_66));
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//ui线程中进行控件更新
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(Long num) {
                        StringUtils.setHtml(tv_verify_code_rgt, String.format(getResources().getString(R.string.verify), num));
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        //回复原来初始状态
                        tv_verify_code_rgt.setEnabled(true);
                        tv_verify_code_rgt.setText("重新发送");
                        tv_verify_code_rgt.setTextColor(ContextCompat.getColor(context, R.color.white));
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
        if (setMobileDisposable != null && !setMobileDisposable.isDisposed())
            setMobileDisposable.dispose();
    }

}
