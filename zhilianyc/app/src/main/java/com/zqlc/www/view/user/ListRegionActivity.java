package com.zqlc.www.view.user;

import android.view.View;
import android.widget.TextView;

import com.zqlc.www.R;
import com.zqlc.www.adapter.user.RegionAdapter;
import com.zqlc.www.base.BaseActivity;
import com.zqlc.www.bean.user.ListReginBean;
import com.zqlc.www.mvp.user.ListRegionContract;
import com.zqlc.www.mvp.user.ListRegionPresenter;
import com.zqlc.www.util.rxbus.RxBus2;
import com.zqlc.www.util.rxbus.busEvent.RegionSelEvent;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListRegionActivity extends BaseActivity implements ListRegionContract.View {

    RecyclerView recyclerView;
    RegionAdapter mAdapter;
    List<ListReginBean> datas;

    TextView tv_name;
    String pCode = "000000";

    ListRegionPresenter mPresenter;

    String province;
    String city;
    String region;

    String address;

    @Override
    public int getLayoutId() {
        return R.layout.acitivty_region;
    }

    @Override
    public void initView() {
        showLeftAndTitle("城市选择");

        tv_name = $(R.id.tv_name);
        tv_name.setVisibility(View.GONE);

        initRecycler();
    }

    private void initRecycler() {
        recyclerView = $(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        mAdapter = new RegionAdapter(activity, new ArrayList<>());
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(position -> {
            ListReginBean item = datas.get(position);
            pCode = item.getCode();

            if(item.getLevel() == 1){
                province = item.getName();
                address = province;
            }else if(item.getLevel() == 2){
                city = item.getName();
                address = address + " > "+city;
            }else if(item.getLevel() == 3){
                region = item.getName();
                address = address + " > "+region;
            }
            tv_name.setVisibility(View.VISIBLE);
            tv_name.setText(address);

            if(item.getLevel() >= 3){
                finish();
                RxBus2.getInstance().post(new RegionSelEvent(province,city,region,pCode));
            }else{
                mPresenter.listRegion(pCode);
            }
        });
    }

    @Override
    public void initData() {
        mPresenter = new ListRegionPresenter(context, this);
        mPresenter.listRegion(pCode);
    }

    @Override
    public void listRegionSuccess(List<ListReginBean> data) {
        if(data == null) data = new ArrayList<>();
        this.datas = data;
        mAdapter.setData(datas);
    }

    @Override
    public void listRegionFailed(String msg) {
        showShortToast(msg);
    }
}
