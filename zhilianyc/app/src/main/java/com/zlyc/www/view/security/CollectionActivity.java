package com.zlyc.www.view.security;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zlyc.www.R;
import com.zlyc.www.base.BaseActivity;
import com.zlyc.www.util.LoginUtil;

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

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!LoginUtil.verifyEmpty(et_collection.getText().toString(),"请输入收款账号"))
                    return;

                showShortToast("设置成功");
                finish();
            }
        });
    }

    @Override
    public void initData() {

    }
}
