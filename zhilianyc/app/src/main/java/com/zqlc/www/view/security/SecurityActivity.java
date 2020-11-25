package com.zqlc.www.view.security;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.zqlc.www.R;
import com.zqlc.www.base.BaseActivity;
import com.zqlc.www.bean.MySelfInfo;
import com.zqlc.www.mvp.my.RealNameStatusContract;
import com.zqlc.www.mvp.my.RealNameStatusPresenter;
import com.zqlc.www.util.rxbus.RxBus2;
import com.zqlc.www.util.rxbus.busEvent.InformationEvent;

import androidx.core.content.ContextCompat;
import io.reactivex.disposables.Disposable;

public class SecurityActivity extends BaseActivity implements View.OnClickListener, RealNameStatusContract.View {

    View view_modify,view_capital,view_authentication,view_address,view_collection;

    TextView tv_status;

    RealNameStatusPresenter mPresenter;
    Disposable disposable;

    @Override
    public int getLayoutId() {
        return R.layout.activity_security;
    }

    @Override
    public void initView() {
        showLeftAndTitle("安全中心");

        view_modify = $(R.id.view_modify);
        view_capital = $(R.id.view_capital);
        view_authentication = $(R.id.view_authentication);
        view_address = $(R.id.view_address);
        view_collection = $(R.id.view_collection);
        tv_status = $(R.id.tv_status);

        view_modify.setOnClickListener(this);
        view_capital.setOnClickListener(this);
        view_authentication.setOnClickListener(this);
        view_address.setOnClickListener(this);
        view_collection.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mPresenter = new RealNameStatusPresenter(context,this);
        mPresenter.realNameStatus(MySelfInfo.getInstance().getUserId());

        disposable = RxBus2.getInstance().toObservable(InformationEvent.class, realNameStatusEvent -> {
            mPresenter.realNameStatus(MySelfInfo.getInstance().getUserId());
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.view_modify:
                startActivity(new Intent(context, ModifyPwdActivity.class));
                break;
            case R.id.view_capital:
                startActivity(new Intent(context, CapitalSetActivity.class));
                break;
            case R.id.view_authentication:
                if (TextUtils.equals(data, "1"))
                    return;
                startActivity(new Intent(context,AuthenticationActivity.class));
                break;
            case R.id.view_address:
                startActivity(new Intent(context,AddressSetActivity.class));
                break;
            case R.id.view_collection:
                startActivity(new Intent(context,CollectionActivity.class));
                break;
        }
    }

    String data;
    @Override
    public void realNameStatusSuccess(String data) {
        this.data = data;
        if (TextUtils.equals(data, "1")) {
            tv_status.setText("已认证");
            tv_status.setTextColor(ContextCompat.getColor(context, R.color.color_61B53F));
        } else if (TextUtils.equals(data, "2")) {
            tv_status.setText("审核中");
            tv_status.setTextColor(ContextCompat.getColor(context, R.color.color_61B53F));
        } else if (TextUtils.equals(data, "0")) {
            tv_status.setText("审核不通过");
            tv_status.setTextColor(ContextCompat.getColor(context, R.color.color_red));
        } else {
            tv_status.setText("未认证");
            tv_status.setTextColor(ContextCompat.getColor(context, R.color.color_red));
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
