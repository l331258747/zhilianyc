package com.zlyc.www.view.security;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zlyc.www.R;
import com.zlyc.www.base.BaseActivity;
import com.zlyc.www.util.LoginUtil;

public class ModifyPwdActivity extends BaseActivity {

    EditText et_old_psd,et_new_psd,et_new_psd2;
    TextView btn_submit;

    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_psd;
    }

    @Override
    public void initView() {
        showLeftAndTitle("修改登录密码");

        et_old_psd = $(R.id.et_old_psd);
        et_new_psd = $(R.id.et_new_psd);
        et_new_psd2 = $(R.id.et_new_psd2);
        btn_submit = $(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!LoginUtil.verifyPassword(et_old_psd.getText().toString()))
                    return;
                if (!LoginUtil.verifyPassword(et_new_psd.getText().toString()))
                    return;
                if (!LoginUtil.verifyPassword(et_new_psd2.getText().toString()))
                    return;
                if (!LoginUtil.verifyPasswordDouble(et_new_psd2.getText().toString(),et_new_psd2.getText().toString()))
                    return;

                showShortToast("修改成功");
                finish();
            }
        });
    }

    @Override
    public void initData() {

    }
}
