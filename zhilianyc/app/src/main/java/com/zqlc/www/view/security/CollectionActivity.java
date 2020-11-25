package com.zqlc.www.view.security;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.zqlc.www.R;
import com.zqlc.www.base.BaseActivity;
import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.bean.MySelfInfo;
import com.zqlc.www.bean.account.UserAccountBean;
import com.zqlc.www.mvp.account.UserAccountContract;
import com.zqlc.www.mvp.account.UserAccountPresenter;
import com.zqlc.www.util.LoginUtil;

public class CollectionActivity extends BaseActivity implements UserAccountContract.View {

    EditText et_collection;
    TextView btn_submit;

    UserAccountPresenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_collection;
    }

    @Override
    public void initView() {
        showLeftAndTitle("设置收款账号");

        et_collection = $(R.id.et_collection);
        btn_submit = $(R.id.btn_submit);

    }

    @Override
    public void initData() {
        mPresenter = new UserAccountPresenter(context,this);
        mPresenter.getUserAccount(MySelfInfo.getInstance().getUserId());
    }

    @Override
    public void getUserAccountSuccess(UserAccountBean data) {
        btn_submit.setText("完成");
        if(data == null || TextUtils.isEmpty(data.getAlipayAccount())){
            btn_submit.setBackgroundResource(R.drawable.bg_gradients_btn_1c81e9);
            btn_submit.setOnClickListener(v -> {
                if (!LoginUtil.verifyEmpty(et_collection.getText().toString(),"请输入收款账号"))
                    return;

                mPresenter.updateAccountNo(MySelfInfo.getInstance().getUserId(),et_collection.getText().toString());
            });

            et_collection.setFocusable(true);
            et_collection.setFocusableInTouchMode(true); // user touches widget on phone with touch screen
            et_collection.setClickable(true); // user navigates with wheel and selects widget

        }else{
            et_collection.setText(data.getAlipayAccount());

            btn_submit.setBackgroundResource(R.drawable.btn_cc_r40);
            btn_submit.setOnClickListener(null);

            et_collection.setFocusable(false);
            et_collection.setFocusableInTouchMode(false); // user touches widget on phone with touch screen
            et_collection.setClickable(false); // user navigates with wheel and selects widget
        }

    }

    @Override
    public void getUserAccountFailed(String msg) {
        showShortToast(msg);

        btn_submit.setText("刷新");
        btn_submit.setBackgroundResource(R.drawable.bg_gradients_btn_1c81e9);
        btn_submit.setOnClickListener(v -> {
            mPresenter.getUserAccount(MySelfInfo.getInstance().getUserId());
        });
    }

    @Override
    public void updateAccountNoSuccess(EmptyModel data) {
        showShortToast("设置成功");
        finish();
    }

    @Override
    public void updateAccountNoFailed(String msg) {
        showShortToast(msg);
    }
}
