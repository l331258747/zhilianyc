package com.zlyc.www.view.security;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.zlyc.www.R;
import com.zlyc.www.adapter.my.AddressAdapter;
import com.zlyc.www.base.BaseActivity;
import com.zlyc.www.bean.EmptyModel;
import com.zlyc.www.bean.MySelfInfo;
import com.zlyc.www.bean.address.AddressBean;
import com.zlyc.www.dialog.DialogUtil;
import com.zlyc.www.mvp.address.AddressSetContract;
import com.zlyc.www.mvp.address.AddressSetPresenter;
import com.zlyc.www.util.rxbus.RxBus2;
import com.zlyc.www.util.rxbus.busEvent.AddressEditEvent;
import com.zlyc.www.util.textdrawable.TextdrawableUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.disposables.Disposable;

public class AddressSetActivity extends BaseActivity implements View.OnClickListener, AddressSetContract.View {

    TextView tv_head, tv_name, tv_address, btn_edit, btn_submit,tv_phone;
    RecyclerView recyclerView;
    ConstraintLayout default_address;

    AddressAdapter mAdapter;
    List<AddressBean> otherDatas;
    AddressBean defaultData;

    Disposable disposable;

    TextdrawableUtils mTextdrawableUtils;

    AddressSetPresenter mPresenter;


    @Override
    public int getLayoutId() {
        return R.layout.acitivty_address_set;

    }

    @Override
    public void initView() {
        showLeftAndTitle("收货地址");

        tv_head = $(R.id.tv_head);
        tv_name = $(R.id.tv_name);
        tv_phone = $(R.id.tv_phone);
        tv_address = $(R.id.tv_address);
        btn_edit = $(R.id.btn_edit);
        btn_submit = $(R.id.btn_submit);
        default_address = $(R.id.default_address);

        btn_submit.setOnClickListener(this);
        btn_edit.setOnClickListener(this);

        initRecycler();
    }

    @Override
    public void initData() {
        mTextdrawableUtils = new TextdrawableUtils(context, "默认");

        mPresenter = new AddressSetPresenter(context,this);
        mPresenter.addressList(MySelfInfo.getInstance().getUserId());

        disposable = RxBus2.getInstance().toObservable(AddressEditEvent.class, addressEditEvent ->
                mPresenter.addressList(MySelfInfo.getInstance().getUserId())
        );
    }

    public void setDefaultView(List<AddressBean> datas) {

        defaultData = getDefaultItem(datas);
        otherDatas = getAddressList(datas);

        if (defaultData != null) {//默认图标
            tv_head.setText(defaultData.getNameHead());
            tv_name.setText(defaultData.getName());
            tv_phone.setText(defaultData.getMobile());
            tv_address.setText(defaultData.getAddressAll());
            mTextdrawableUtils.setTextStyle(defaultData.getAddressAll(), tv_address); //添加一个标签
            default_address.setVisibility(View.VISIBLE);
        }else{
            default_address.setVisibility(View.GONE);
        }

        mAdapter.setData(otherDatas);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_edit:
                intent = new Intent(context, AddressEditActivity.class);
                intent.putExtra("AddressBean", defaultData);
                startActivity(intent);
                break;
            case R.id.btn_submit:
                startActivity(new Intent(context, AddressEditActivity.class));
                break;
        }
    }

    //初始化recyclerview
    public void initRecycler() {
        recyclerView = $(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new AddressAdapter(context, new ArrayList<>());
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new AddressAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                intent = new Intent(context, AddressEditActivity.class);
                intent.putExtra("AddressBean", otherDatas.get(position));
                startActivity(intent);
            }

            @Override
            public void onLongClick(final int position) {
                DialogUtil.getInstance().getDefaultDialog(context, "确认删除", new DialogUtil.DialogCallBack() {
                    @Override
                    public void exectEvent(DialogInterface alterDialog) {
                        mPresenter.addressDelete(MySelfInfo.getInstance().getUserId(),otherDatas.get(position).getId());
                    }
                }).show();

            }
        });
    }

    public AddressBean getDefaultItem(List<AddressBean> datas) {
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).getType() == 1) {
                return datas.get(i);
            }
        }
        return null;
    }

    public List<AddressBean> getAddressList(List<AddressBean> datas) {
        List<AddressBean> items = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).getType() != 1) {
                items.add(datas.get(i));
            }
        }
        return items;
    }

    @Override
    public void addressListSuccess(List<AddressBean> datas) {
        if(datas == null) datas = new ArrayList<>();
        setDefaultView(datas);
    }

    @Override
    public void addressListFailed(String msg) {
        showLongToast(msg);
    }

    @Override
    public void addressDeleteSuccess(EmptyModel data) {
        showLongToast("删除成功");
        mPresenter.addressList(MySelfInfo.getInstance().getUserId());
    }

    @Override
    public void addressDeleteFailed(String msg) {
        showLongToast(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }
}
