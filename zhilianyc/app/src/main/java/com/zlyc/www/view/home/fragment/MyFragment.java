package com.zlyc.www.view.home.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zlyc.www.R;
import com.zlyc.www.adapter.my.MyTabAdapter;
import com.zlyc.www.base.BaseFragment;
import com.zlyc.www.bean.MySelfInfo;
import com.zlyc.www.bean.my.MyTabBean;
import com.zlyc.www.constant.Constant;
import com.zlyc.www.util.ToastUtil;
import com.zlyc.www.util.glide.GlideUtil;
import com.zlyc.www.view.my.AccountActivity;
import com.zlyc.www.view.my.MyTeamActivity;
import com.zlyc.www.view.security.SecurityActivity;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyFragment extends BaseFragment implements View.OnClickListener {

    RecyclerView recyclerView;
    MyTabAdapter mAdapter;
    List<MyTabBean> datas;

    ImageView iv_head;
    TextView tv_name,tv_UID,tv_data_all_num,tv_data_today_num,tv_data_use_num,tv_data_contribution_num,tv_data_labour_num;
    View view_title;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView() {
        iv_head = $(R.id.iv_head);
        tv_name = $(R.id.tv_name);
        tv_UID = $(R.id.tv_UID);
        tv_data_all_num = $(R.id.tv_data_all_num);
        tv_data_today_num = $(R.id.tv_data_today_num);
        tv_data_use_num = $(R.id.tv_data_use_num);
        tv_data_contribution_num = $(R.id.tv_data_contribution_num);
        tv_data_labour_num = $(R.id.tv_data_labour_num);

        view_title = $(R.id.view_title);

        initRecycler();

        view_title.setOnClickListener(this);
    }

    @Override
    public void initData() {
        GlideUtil.LoadCircleImage(context, MySelfInfo.getInstance().getUserAvatar(), iv_head);
        tv_name.setText(MySelfInfo.getInstance().getUserNickname());
        tv_UID.setText("UID:" + MySelfInfo.getInstance().getUserId());
        tv_data_all_num.setText("111");
        tv_data_today_num.setText("222");
        tv_data_use_num.setText("333");
        tv_data_contribution_num.setText("444");
        tv_data_labour_num.setText("555");



    }

    //初始化recyclerview
    public void initRecycler() {
        recyclerView = $(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, 4);
        recyclerView.setLayoutManager(gridLayoutManager);
        MyTabBean myTabBean = new MyTabBean();
        datas = myTabBean.getDatas();
        mAdapter = new MyTabAdapter(context, datas);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new MyTabAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                MyTabBean item = datas.get(position);
                switch (item.getId()) {
                    case Constant.MY_TAB_TEAM:
                        startActivity(new Intent(context, MyTeamActivity.class));
                        break;
                    case Constant.MY_TAB_SECURITY:
                        startActivity(new Intent(context, SecurityActivity.class));
                        break;
                    case Constant.MY_TAB_ORDER:
                    case Constant.MY_TAB_TRANSACTION_DTS:
                    case Constant.MY_TAB_INVITAT:
                    case Constant.MY_TAB_STUDIO:
                    case Constant.MY_TAB_NOTIFY:
                    case Constant.MY_TAB_CHANGE:
                    case Constant.MY_TAB_CLOUD:
                    case Constant.MY_TAB_SHOP:
                    case Constant.MY_TAB_RED_PACKAGE:
                    case Constant.MY_TAB_TASK:
                        ToastUtil.showShortToast(context, item.getName());
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.view_title:
                startActivity(new Intent(context, AccountActivity.class));
                break;
        }
    }
}
