package com.zlyc.www.view.home.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zlyc.www.R;
import com.zlyc.www.adapter.my.MyTabAdapter;
import com.zlyc.www.base.BaseFragment;
import com.zlyc.www.bean.MySelfInfo;
import com.zlyc.www.bean.login.MineBean;
import com.zlyc.www.bean.my.MyTabBean;
import com.zlyc.www.constant.Constant;
import com.zlyc.www.mvp.my.MyInfoContract;
import com.zlyc.www.mvp.my.MyInfoPresenter;
import com.zlyc.www.mvp.my.RealNameStatusContract;
import com.zlyc.www.mvp.my.RealNameStatusPresenter;
import com.zlyc.www.util.ToastUtil;
import com.zlyc.www.util.glide.GlideUtil;
import com.zlyc.www.view.my.AccountActivity;
import com.zlyc.www.view.my.MyTeamActivity;
import com.zlyc.www.view.security.SecurityActivity;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyFragment extends BaseFragment implements View.OnClickListener, MyInfoContract.View, RealNameStatusContract.View {

    RecyclerView recyclerView;
    MyTabAdapter mAdapter;
    List<MyTabBean> datas;

    ImageView iv_head;
    TextView tv_name, tv_UID, tv_data_all_num, tv_data_today_num, tv_data_use_num, tv_data_contribution_num, tv_data_labour_num, tv_region, tv_authentication;
    View view_title;

    MyInfoPresenter mPresenter;
    RealNameStatusPresenter rnsPresenter;

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
        tv_region = $(R.id.tv_region);
        tv_authentication = $(R.id.tv_authentication);

        tv_region.setVisibility(View.GONE);
        tv_authentication.setVisibility(View.GONE);

        view_title = $(R.id.view_title);

        initRecycler();

        view_title.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mPresenter = new MyInfoPresenter(context, this);
        mPresenter.mine(MySelfInfo.getInstance().getUserId());
        rnsPresenter = new RealNameStatusPresenter(context, this);
        rnsPresenter.realNameStatus(MySelfInfo.getInstance().getUserId());
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
        switch (v.getId()) {
            case R.id.view_title:
                startActivity(new Intent(context, AccountActivity.class));
                break;
        }
    }

    @Override
    public void mineSuccess(MineBean data) {
        GlideUtil.LoadCircleImage(context, data.getHeadImg(), iv_head);
        tv_name.setText(data.getNickName());
        tv_UID.setText("UID:" + MySelfInfo.getInstance().getUserId());
        tv_data_all_num.setText(data.getBeans() + "");
        tv_data_today_num.setText(data.getTodayBeans() + "");
        tv_data_use_num.setText(data.getSellableBeans() + "");
        tv_data_contribution_num.setText(data.getContribution() + "");
        tv_data_labour_num.setText(data.getLabor() + "");

        if (!TextUtils.isEmpty(data.getCityPartnerName())) {
            tv_region.setText(data.getCityPartnerName());
        }

    }

    @Override
    public void mineFailed(String msg) {
        showShortToast(msg);
    }

    @Override
    public void realNameStatusSuccess(String data) {
        tv_authentication.setVisibility(View.VISIBLE);
        //0 审核不通过 1 已实名 2 审核中 3 未认证

        if (TextUtils.equals(data, "1")) {
            tv_authentication.setText("已认证");
            tv_authentication.setBackgroundResource(R.drawable.btn_61b53f_r1);
        } else if (TextUtils.equals(data, "2")) {
            tv_authentication.setText("审核中");
            tv_authentication.setBackgroundResource(R.drawable.btn_61b53f_r1);
        } else if (TextUtils.equals(data, "0")) {
            tv_authentication.setText("审核不通过");
            tv_authentication.setBackgroundResource(R.drawable.btn_red_r1);
        } else {
            tv_authentication.setText("未认证");
            tv_authentication.setBackgroundResource(R.drawable.btn_red_r1);
        }
    }

    @Override
    public void realNameStatusFailed(String msg) {
        showShortToast(msg);
    }
}
