package com.zlyc.www.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zlyc.www.R;
import com.zlyc.www.bean.my.MyTabBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MyTabAdapter extends RecyclerView.Adapter<MyTabAdapter.ViewHolder> {

    Context mContext;
    List<MyTabBean> datas;


    public MyTabAdapter(Context context, List<MyTabBean> datas) {
        mContext = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_my_tab, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if (holder == null) return;
        final MyTabBean data = datas.get(position);
        if (data == null) return;

        holder.tv_name.setText(data.getName());
        holder.iv_img.setImageResource(data.getImgId());

        if (mOnItemClickListener != null) {
            holder.cl_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_img;
        TextView tv_name;
        ConstraintLayout cl_parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_img = itemView.findViewById(R.id.iv_img);
            tv_name = itemView.findViewById(R.id.tv_name);
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

    public void setData(List<MyTabBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void addData(List<MyTabBean> datas){
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    public List<MyTabBean> getDatas(){
        return this.datas;
    }
}
