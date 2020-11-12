package com.zlyc.www.view.security;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zlyc.www.R;
import com.zlyc.www.base.BaseActivity;
import com.zlyc.www.bean.my.AddressBean;
import com.zlyc.www.util.LoginUtil;
import com.zlyc.www.util.pickerView.PickerCityHelp;
import com.zlyc.www.util.rxbus.RxBus2;
import com.zlyc.www.util.rxbus.busEvent.AddressEditEvent;

public class AddressEditActivity extends BaseActivity implements View.OnClickListener {

    EditText et_name, et_phone, et_address_dts;
    TextView tv_address, btn_submit;
    View view_address, view_default;
    ImageView iv_check;

    boolean isChecked;

    PickerCityHelp mPickerCityHelp;


    AddressBean mAddressBean;

    @Override
    public int getLayoutId() {
        return R.layout.acitivty_address_edit;

    }

    @Override
    public void initView() {
        mAddressBean = (AddressBean) intent.getSerializableExtra("AddressBean");

        showLeftAndTitle("编辑地址");
        et_name = $(R.id.et_name);
        et_phone = $(R.id.et_phone);
        et_address_dts = $(R.id.et_address_dts);
        tv_address = $(R.id.tv_address);
        btn_submit = $(R.id.btn_submit);
        view_address = $(R.id.view_address);
        view_default = $(R.id.view_default);
        iv_check = $(R.id.iv_check);

        view_address.setOnClickListener(this);
        view_default.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mPickerCityHelp = new PickerCityHelp(context);
        mPickerCityHelp.initData();

        if(mAddressBean != null){
            setAddressData();
        }
    }

    private void setAddressData() {
        et_name.setText(mAddressBean.getName());
        et_phone.setText(mAddressBean.getPhone());
        tv_address.setText(mAddressBean.getAddress());
        et_address_dts.setText(mAddressBean.getAddressDts());
        isChecked = mAddressBean.isDefault();
        iv_check.setImageResource(isChecked ? R.mipmap.ic_check_on : R.mipmap.ic_check_un);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_address:
                mPickerCityHelp.showPickerView(tv_address);
                break;
            case R.id.view_default:
                setChecked();
                break;
            case R.id.btn_submit:
                if (!LoginUtil.verifyName(et_name.getText().toString()))
                    return;
                if (!LoginUtil.verifyPhone(et_phone.getText().toString()))
                    return;
                if (!LoginUtil.verifyEmpty(tv_address.getText().toString(),"请选择地区"))
                    return;
                if (!LoginUtil.verifyEmpty(et_address_dts.getText().toString(),"请输入详细地址"))
                    return;


                RxBus2.getInstance().post(new AddressEditEvent());
                showShortToast("编辑成功");
                finish();


                //回去后刷新地址列表

                break;
        }
    }

    public void setChecked() {
        isChecked = !isChecked;
        iv_check.setImageResource(isChecked ? R.mipmap.ic_check_on : R.mipmap.ic_check_un);

    }
}
