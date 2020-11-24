package com.zqlc.www.adapter.coupon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zqlc.www.R;
import com.zqlc.www.bean.coupon.MyCouponList;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyCouponAdapter extends RecyclerView.Adapter<MyCouponAdapter.ViewHolder> {

    Context mContext;
    List<MyCouponList> datas;

    public MyCouponAdapter(Context context, List<MyCouponList> datas) {
        mContext = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_my_coupon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (holder == null) return;
        final MyCouponList data = datas.get(position);
        if (data == null) return;

        //COUPON_LV0(0,"试营仓储"),COUPON_LV1(1,"小镇仓储"),
        // COUPON_LV2(2,"区县仓储"),COUPON_LV3(3,"区县市仓储"),COUPON_LV4(4,"市级仓储"),
        // ,COUPON_LV5(5,"直辖市仓储"),COUPON_LV6(6,"省级仓储"),COUPON_LV7(7,"全国仓储");

        holder.iv_img.setImageResource(R.mipmap.ic_coupon_sys);
        int type = data.getType();
        if (type == 1){
            holder.iv_img.setImageResource(R.mipmap.ic_coupon_zhen);
        }else if(type == 2){
            holder.iv_img.setImageResource(R.mipmap.ic_coupon_xian);
        }else if(type == 3){
            holder.iv_img.setImageResource(R.mipmap.ic_coupon_xian);
        }else if(type == 4){
            holder.iv_img.setImageResource(R.mipmap.ic_coupon_shi);
        }else if(type == 5){
            holder.iv_img.setImageResource(R.mipmap.ic_coupon_shi);
        }else if(type == 6){
            holder.iv_img.setImageResource(R.mipmap.ic_coupon_sheng);
        }else if(type == 7){
            holder.iv_img.setImageResource(R.mipmap.ic_coupon_guo);
        }

        holder.tv_name.setText(data.getName());
        holder.tv_totalCount.setText(data.getTotalCountStr());
        holder.tv_dailyRelease.setText(data.getDailyReleaseStr());
        holder.tv_labor.setText(data.getLaborStr());
        holder.tv_remainDay.setText(data.getRemainDayStr());

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setData(List<MyCouponList> datas){
        this.datas = datas;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_img;
        TextView tv_name,tv_totalCount,tv_dailyRelease,tv_labor,tv_remainDay;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_img = itemView.findViewById(R.id.iv_img);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_totalCount = itemView.findViewById(R.id.tv_totalCount);
            tv_dailyRelease = itemView.findViewById(R.id.tv_dailyRelease);
            tv_labor = itemView.findViewById(R.id.tv_labor);
            tv_remainDay = itemView.findViewById(R.id.tv_remainDay);
        }
    }
}
