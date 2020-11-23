package com.zlyc.www.view.otc;

import android.text.Editable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.zlyc.www.R;
import com.zlyc.www.base.BaseActivity;
import com.zlyc.www.bean.EmptyModel;
import com.zlyc.www.mvp.otc.OtcFeedbackContract;
import com.zlyc.www.mvp.otc.OtcFeedbackPresenter;
import com.zlyc.www.util.LoginUtil;
import com.zlyc.www.util.MyTextWatcher.MyTexxtWatcher;

import androidx.recyclerview.widget.RecyclerView;

public class OtcFeedbackActivity extends BaseActivity implements OtcFeedbackContract.View {


    OtcFeedbackPresenter mPresenter;

    TextView tv_words,btn_submit;
    EditText et_content,et_phone;
    RecyclerView mPhotoRecyclerView;

    String file;

    @Override
    public int getLayoutId() {
        return R.layout.activity_otc_feedback;
    }

    @Override
    public void initView() {
        showLeftAndTitle("订单申诉");

        tv_words = $(R.id.tv_words);
        et_content = $(R.id.et_content);
        btn_submit = $(R.id.btn_submit);
        et_phone = $(R.id.et_phone);
        mPhotoRecyclerView = $(R.id.recycler_view);

        et_content.addTextChangedListener(new MyTexxtWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(s))
                    tv_words.setText("0/500");
                else
                    tv_words.setText("("+s.length() + "/500)");
            }
        });

        btn_submit.setOnClickListener(v -> {
            if(!LoginUtil.verifyEmpty(et_content.getText().toString(),"请输入申诉内容"))
                return;
            if(!LoginUtil.verifyPhone(et_phone.getText().toString()))
                return;
            if(!LoginUtil.verifyEmpty(file,"请选择申诉图片"))
                return;



        });

    }

    @Override
    public void initData() {
        mPresenter = new OtcFeedbackPresenter(context,this);

    }

    @Override
    public void otcFeedbackSuccess(EmptyModel data) {
        showShortToast("提交成功");
        finish();
    }

    @Override
    public void otcFeedbackFailed(String msg) {
        showShortToast(msg);
    }




}
