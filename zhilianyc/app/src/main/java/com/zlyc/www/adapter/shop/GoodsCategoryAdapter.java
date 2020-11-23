package com.zlyc.www.adapter.shop;

import android.content.Context;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zlyc.www.R;
import com.zlyc.www.bean.shop.GoodsClassBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class GoodsCategoryAdapter extends RecyclerView.Adapter<GoodsCategoryAdapter.ViewHolder> {

    Context mContext;
    List<GoodsClassBean> datas;

    private String categoryId; //默认一个参数


    public GoodsCategoryAdapter(Context context, List<GoodsClassBean> datas,String categoryId) {
        mContext = context;
        this.datas = datas;
        this.categoryId = categoryId;
    }

    @NonNull
    @Override
    public GoodsCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_goods_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsCategoryAdapter.ViewHolder holder, int position) {
        if (holder == null) return;
        final GoodsClassBean data = datas.get(position);
        if (data == null) return;

        holder.tv_name.setText(data.getName());

        TextPaint tp = holder.tv_name.getPaint();
        String id = datas.get(position).getId();
        if (TextUtils.equals(categoryId, id)) {
            holder.tv_name.setTextColor(ContextCompat.getColor(mContext,R.color.color_368feb));
            tp.setFakeBoldText(true);
            holder.view_line.setVisibility(View.VISIBLE);
            holder.cl_parent.setBackgroundResource(R.color.color_window_background);
        } else {
            holder.tv_name.setTextColor(ContextCompat.getColor(mContext,R.color.color_99));
            tp.setFakeBoldText(false);
            holder.view_line.setVisibility(View.GONE);
            holder.cl_parent.setBackgroundResource(R.color.white);
        }

        if (mOnItemClickListener != null) {
            holder.cl_parent.setOnClickListener(v -> {
                mOnItemClickListener.onClick(position);
                categoryId = id;
                notifyDataSetChanged();//刷新当前点击item
            });
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout cl_parent;
        View view_line;
        TextView tv_name;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cl_parent = itemView.findViewById(R.id.cl_parent);
            view_line = itemView.findViewById(R.id.view_line);
            tv_name = itemView.findViewById(R.id.tv_name);
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
