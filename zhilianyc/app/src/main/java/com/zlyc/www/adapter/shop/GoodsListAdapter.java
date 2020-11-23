package com.zlyc.www.adapter.shop;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zlyc.www.R;
import com.zlyc.www.bean.shop.GoodsListBean;
import com.zlyc.www.util.StringUtils;
import com.zlyc.www.util.glide.GlideUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.ViewHolder>{
    Context mContext;
    List<GoodsListBean> datas;

    public GoodsListAdapter(Context context, List<GoodsListBean> datas) {
        mContext = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_goods_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (holder == null) return;
        final GoodsListBean data = datas.get(position);
        if (data == null) return;

        holder.tv_name.setText(data.getName());
        holder.tv_price.setText(StringUtils.getStringNum(data.getPrice()));

        holder.tv_num.setVisibility(View.GONE);

        if(TextUtils.isEmpty(data.getImgUrl())){
            holder.iv_img.setImageResource(R.mipmap.default_head);
        }else{
            GlideUtil.loadImage(mContext, data.getImgUrl(),holder.iv_img);
        }

        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(v -> mOnItemClickListener.onClick(position));
        }


    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setData(List<GoodsListBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }
    public void addData(List<GoodsListBean> datas) {
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_img;
        TextView tv_name,tv_price,tv_num;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_img = itemView.findViewById(R.id.iv_img);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_num = itemView.findViewById(R.id.tv_num);
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
