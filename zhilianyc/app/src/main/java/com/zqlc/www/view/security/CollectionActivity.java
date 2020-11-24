package com.zqlc.www.view.security;

import android.widget.EditText;
import android.widget.TextView;

import com.zqlc.www.R;
import com.zqlc.www.base.BaseActivity;
import com.zqlc.www.util.LoginUtil;

public class CollectionActivity extends BaseActivity {

    EditText et_collection;
    TextView btn_submit;


    @Override
    public int getLayoutId() {
        return R.layout.activity_collection;
    }

    @Override
    public void initView() {
        showLeftAndTitle("设置收款账号");

        et_collection = $(R.id.et_collection);
        btn_submit = $(R.id.btn_submit);

        btn_submit.setOnClickListener(v -> {
            if (!LoginUtil.verifyEmpty(et_collection.getText().toString(),"请输入收款账号"))
                return;

            showShortToast("设置成功");
            finish();
        });
    }

    @Override
    public void initData() {

    }
}
