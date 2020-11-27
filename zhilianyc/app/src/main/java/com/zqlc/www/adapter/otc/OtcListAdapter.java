package com.zqlc.www.adapter.otc;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zqlc.www.R;
import com.zqlc.www.bean.otc.OtcListBean;
import com.zqlc.www.util.glide.GlideUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class OtcListAdapter extends RecyclerView.Adapter<OtcListAdapter.ViewHolder> {

    Context mContext;
    List<OtcListBean> datas;
    int orderType;//0求购，1转让

    public OtcListAdapter(Context context, List<OtcListBean> datas) {
        mContext = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public OtcListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_otc_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OtcListAdapter.ViewHolder holder, int position) {
        if (holder == null) return;
        final OtcListBean data = datas.get(position);
        if (data == null) return;

        if(orderType == 1){
            holder.btn_send.setText("求购");
            holder.btn_send.setBackgroundResource(R.drawable.bg_gradients_btn_368feb);
        }else{
            holder.btn_send.setText("转让");
            holder.btn_send.setBackgroundResource(R.drawable.bg_gradients_btn_ff4751);
        }

        holder.tv_price.setText(data.getUnitPriceStr());
        holder.tv_num.setText(data.getCountStr());
        holder.tv_name.setText(data.getRealName());

        if(TextUtils.isEmpty(data.getHeadImg())){
            holder.iv_img.setImageResource(R.mipmap.default_head);
        }else{
            GlideUtil.loadCircleImage(mContext,data.getHeadImg(),holder.iv_img);
        }

        if (mOnItemClickListener != null) {
            holder.cl_parent.setOnClickListener(v -> mOnItemClickListener.onClick(position));
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public void setData(List<OtcListBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void addData(List<OtcListBean> datas) {
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    public List<OtcListBean> getData() {
        return this.datas;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView btn_send,tv_price,tv_num,tv_name;
        ImageView iv_img;
        ConstraintLayout cl_parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            btn_send = itemView.findViewById(R.id.btn_send);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_num = itemView.findViewById(R.id.tv_num);
            tv_name = itemView.findViewById(R.id.tv_name);
            iv_img = itemView.findViewById(R.id.iv_img);
            cl_parent = itemView.findViewById(R.id.cl_parent);
        }
    }

    OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
