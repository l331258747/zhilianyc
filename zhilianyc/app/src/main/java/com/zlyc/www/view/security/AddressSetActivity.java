package com.zlyc.www.view.security;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.zlyc.www.R;
import com.zlyc.www.adapter.my.AddressAdapter;
import com.zlyc.www.base.BaseActivity;
import com.zlyc.www.bean.my.AddressBean;
import com.zlyc.www.util.rxbus.RxBus2;
import com.zlyc.www.util.rxbus.busEvent.AddressEditEvent;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class AddressSetActivity extends BaseActivity implements View.OnClickListener {

    TextView tv_head, tv_name, tv_address, btn_edit, btn_submit;
    RecyclerView recyclerView;

    AddressAdapter mAdapter;
    List<AddressBean> otherDatas;
    AddressBean defaultData;

    Disposable disposable;

    @Override
    public int getLayoutId() {
        return R.layout.acitivty_address_set;


    }

    @Override
    public void initView() {
        showLeftAndTitle("收货地址");

        tv_head = $(R.id.tv_head);
        tv_name = $(R.id.tv_name);
        tv_address = $(R.id.tv_address);
        btn_edit = $(R.id.btn_edit);
        btn_submit = $(R.id.btn_submit);

        btn_submit.setOnClickListener(this);
        btn_edit.setOnClickListener(this);


    }

    @Override
    public void initData() {

        disposable = RxBus2.getInstance().toObservable(AddressEditEvent.class, new Consumer<AddressEditEvent>() {
            @Override
            public void accept(AddressEditEvent addressEditEvent) throws Exception {
                AddressBean addressBean = new AddressBean();
                List<AddressBean> datas = addressBean.getDatas2();

                defaultData = getDefaultItem(datas);
                otherDatas = getAddressList(datas);

                if (defaultData != null) {//默认图标
                    tv_head.setText(defaultData.getNameHead());
                    tv_name.setText(defaultData.getName());
                    tv_address.setText(defaultData.getAddressAll());
                }

                mAdapter.setData(otherDatas);
            }
        });

        AddressBean addressBean = new AddressBean();
        List<AddressBean> datas = addressBean.getDatas();

        defaultData = getDefaultItem(datas);
        otherDatas = getAddressList(datas);

        initRecycler();

        if (defaultData != null) {//默认图标
            tv_head.setText(defaultData.getNameHead());
            tv_name.setText(defaultData.getName());
            tv_address.setText(defaultData.getAddressAll());
        }
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
        mAdapter = new AddressAdapter(context, otherDatas);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new AddressAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                intent = new Intent(context, AddressEditActivity.class);
                intent.putExtra("AddressBean", otherDatas.get(position));
                startActivity(intent);
            }
        });
    }

    public AddressBean getDefaultItem(List<AddressBean> datas) {
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).isDefault()) {
                return datas.get(i);
            }
        }
        return null;
    }

    public List<AddressBean> getAddressList(List<AddressBean> datas) {
        List<AddressBean> items = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            if (!datas.get(i).isDefault()) {
                items.add(datas.get(i));
            }
        }
        return items;
    }
}
