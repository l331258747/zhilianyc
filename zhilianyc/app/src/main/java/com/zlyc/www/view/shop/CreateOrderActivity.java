package com.zlyc.www.view.shop;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.zlyc.www.R;
import com.zlyc.www.adapter.shop.SendOrderAdapter;
import com.zlyc.www.base.ActivityCollect;
import com.zlyc.www.base.BaseActivity;
import com.zlyc.www.bean.EmptyModel;
import com.zlyc.www.bean.MySelfInfo;
import com.zlyc.www.bean.address.AddressBean;
import com.zlyc.www.bean.shop.GoodsDetailsBean;
import com.zlyc.www.mvp.shop.CreateOrderContract;
import com.zlyc.www.mvp.shop.CreateOrderPresenter;
import com.zlyc.www.util.DecimalUtil;
import com.zlyc.www.util.LoginUtil;
import com.zlyc.www.util.StringUtils;
import com.zlyc.www.util.rxbus.RxBus2;
import com.zlyc.www.util.rxbus.busEvent.AddressEditEvent;
import com.zlyc.www.view.security.AddressSetActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.disposables.Disposable;

public class CreateOrderActivity extends BaseActivity implements View.OnClickListener, CreateOrderContract.View {

    TextView btn_address,tv_address_name,tv_address_phone,tv_address_detail,tv_address_edit;
    Group group_content;

    RecyclerView recyclerView;
    SendOrderAdapter mAdapter;

    CreateOrderPresenter mPresenter;

    TextView tv_num,tv_price,btn_submit;


    GoodsDetailsBean data;
    int num;
    String addressId;

    Disposable disposable;

    @Override
    public int getLayoutId() {
        return R.layout.activity_send_order;
    }

    @Override
    public void initView() {
        showLeftAndTitle("确认订单");

        num = intent.getIntExtra("num",0);
        data = (GoodsDetailsBean) intent.getSerializableExtra("goods");

        btn_address = $(R.id.btn_address);
        tv_address_name = $(R.id.tv_address_name);
        tv_address_phone = $(R.id.tv_address_phone);
        tv_address_detail = $(R.id.tv_address_detail);
        tv_address_edit = $(R.id.tv_address_edit);

        tv_num = $(R.id.tv_num);
        tv_price = $(R.id.tv_price);
        btn_submit = $(R.id.btn_submit);

        group_content = $(R.id.group_content);

        btn_address.setOnClickListener(this);
        tv_address_edit.setOnClickListener(this);
        btn_submit.setOnClickListener(this);

        initRecycler();

        float totalSum = DecimalUtil.add(data.getPostage(),DecimalUtil.multiply(data.getPrice(),num));
        tv_price.setText(StringUtils.getStringNum(totalSum));
    }

    //初始化recyclerview
    public void initRecycler() {
        recyclerView = $(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new SendOrderAdapter(context, data, num);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        mPresenter = new CreateOrderPresenter(context,this);

        mPresenter.addressList(MySelfInfo.getInstance().getUserId());

        disposable = RxBus2.getInstance().toObservable(AddressEditEvent.class, addressEditEvent ->
                mPresenter.addressList(MySelfInfo.getInstance().getUserId())
        );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_address:
            case R.id.tv_address_edit:
                startActivity(new Intent(context, AddressSetActivity.class));
                break;
            case R.id.btn_submit:
                if(!LoginUtil.verifyEmpty(addressId,"请选择地址后再提交"))
                    return;
                mPresenter.createOrder(MySelfInfo.getInstance().getUserId(),data.getGoodsId(),addressId,num);
                break;
        }
    }

    @Override
    public void createOrderSuccess(EmptyModel data) {
        showShortToast("提交成功");
        ActivityCollect.getAppCollect().finishAllNotHome();
        startActivity(new Intent(context,OrderListActivity.class));
    }

    @Override
    public void createOrderFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void addressListSuccess(List<AddressBean> datas) {
        if(datas == null) datas = new ArrayList<>();
        setDefaultView(datas);
    }

    AddressBean defaultData;
    public void setDefaultView(List<AddressBean> datas) {

        defaultData = getDefaultItem(datas);

        btn_address = $(R.id.btn_address);
        tv_address_name = $(R.id.tv_address_name);
        tv_address_phone = $(R.id.tv_address_phone);
        tv_address_detail = $(R.id.tv_address_detail);

        if (defaultData != null) {//默认图标
            tv_address_name.setText(defaultData.getName());
            tv_address_phone.setText(defaultData.getMobile());
            tv_address_detail.setText(defaultData.getAddressAll());

            addressId = defaultData.getId();

            btn_address.setVisibility(View.GONE);
            group_content.setVisibility(View.VISIBLE);
        }else{
            btn_address.setVisibility(View.VISIBLE);
            group_content.setVisibility(View.GONE);
        }
    }

    public AddressBean getDefaultItem(List<AddressBean> datas) {
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).getType() == 1) {
                return datas.get(i);
            }
        }
        return null;
    }

    @Override
    public void addressListFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }
}
