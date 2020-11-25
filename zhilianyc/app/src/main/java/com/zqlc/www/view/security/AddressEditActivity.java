package com.zqlc.www.view.security;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zqlc.www.R;
import com.zqlc.www.base.BaseActivity;
import com.zqlc.www.bean.EmptyModel;
import com.zqlc.www.bean.MySelfInfo;
import com.zqlc.www.bean.address.AddressBean;
import com.zqlc.www.mvp.address.AddressEditContract;
import com.zqlc.www.mvp.address.AddressEditPresenter;
import com.zqlc.www.util.LoginUtil;
import com.zqlc.www.util.pickerView.PickerCityHelp;
import com.zqlc.www.util.rxbus.RxBus2;
import com.zqlc.www.util.rxbus.busEvent.AddressEditEvent;
import com.zqlc.www.util.rxbus.busEvent.RegionSelEvent;
import com.zqlc.www.view.user.ListRegionActivity;

import io.reactivex.disposables.Disposable;

public class AddressEditActivity extends BaseActivity implements View.OnClickListener, AddressEditContract.View {

    EditText et_name, et_phone, et_address_dts;
    TextView tv_address, btn_submit;
    View view_address, view_default;
    ImageView iv_check;

    boolean isChecked;

    PickerCityHelp mPickerCityHelp;

    AddressBean mAddressBean;

    AddressEditPresenter mPresenter;

    int setType = 0;//0新增，1编辑
    String province, city, region, address;

    Disposable disposable;

    @Override
    public int getLayoutId() {
        return R.layout.acitivty_address_edit;

    }

    @Override
    public void initView() {
        mAddressBean = (AddressBean) intent.getSerializableExtra("AddressBean");
        setType = mAddressBean == null ? 0 : 1;

        if (setType == 1) {
            showLeftAndTitle("编辑地址");
        } else {
            showLeftAndTitle("新增地址");
        }

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

        if (mAddressBean != null) {
            setAddressData();
        }

        mPresenter = new AddressEditPresenter(context, this);

        disposable = RxBus2.getInstance().toObservable(RegionSelEvent.class, regionSelEvent -> {
            tv_address.setText(regionSelEvent.getAddressDes());

            tv_address.setText(regionSelEvent.getAddressDes());

            province = regionSelEvent.getProvince();
            city = regionSelEvent.getCity();
            region = regionSelEvent.getRegion();
        });
    }

    private void setAddressData() {
        province = mAddressBean.getProvince();
        city = mAddressBean.getCity();
        region = mAddressBean.getRegion();

        et_name.setText(mAddressBean.getName());
        et_phone.setText(mAddressBean.getMobile());
        tv_address.setText(mAddressBean.getAddressDes());
        et_address_dts.setText(mAddressBean.getAddress());
        isChecked = mAddressBean.getType() == 1;
        iv_check.setImageResource(isChecked ? R.mipmap.ic_check_on : R.mipmap.ic_check_un);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_address:
                startActivity(new Intent(context, ListRegionActivity.class));
                break;
            case R.id.view_default:
                setChecked();
                break;
            case R.id.btn_submit:
                if (!LoginUtil.verifyName(et_name.getText().toString()))
                    return;
                if (!LoginUtil.verifyPhone(et_phone.getText().toString()))
                    return;
                if (!LoginUtil.verifyEmpty(tv_address.getText().toString(), "请选择地区"))
                    return;
                if (!LoginUtil.verifyEmpty(et_address_dts.getText().toString(), "请输入详细地址"))
                    return;


                if (setType == 1) {
                    mPresenter.addressEdit(mAddressBean.getId()
                            , MySelfInfo.getInstance().getUserId()
                            , et_name.getText().toString()
                            , province, city, region
                            , et_address_dts.getText().toString()
                            , et_phone.getText().toString()
                            , isChecked ? "1" : "0");
                } else {
                    mPresenter.addressAdd(MySelfInfo.getInstance().getUserId()
                            , et_name.getText().toString()
                            , province, city, region
                            , et_address_dts.getText().toString()
                            , et_phone.getText().toString()
                            , isChecked ? "1" : "0");
                }
                //回去后刷新地址列表

                break;
        }
    }

    public void setChecked() {
        isChecked = !isChecked;
        iv_check.setImageResource(isChecked ? R.mipmap.ic_check_on : R.mipmap.ic_check_un);

    }

    @Override
    public void addressEditSuccess(EmptyModel data) {
        RxBus2.getInstance().post(new AddressEditEvent());
        showShortToast("提交成功");
        finish();
    }

    @Override
    public void addressEditFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void addressAddSuccess(EmptyModel data) {
        finish();
        RxBus2.getInstance().post(new AddressEditEvent());
        showShortToast("提交成功");
    }

    @Override
    public void addressAddFailed(String msg) {
        showShortToast(msg);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }
}
