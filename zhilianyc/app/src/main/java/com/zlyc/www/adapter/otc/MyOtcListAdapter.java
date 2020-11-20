package com.zlyc.www.adapter.otc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zlyc.www.R;
import com.zlyc.www.bean.otc.MyOtcListBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class MyOtcListAdapter extends RecyclerView.Adapter<MyOtcListAdapter.ViewHolder> {

    Context mContext;
    List<MyOtcListBean> datas;

    public MyOtcListAdapter(Context context, List<MyOtcListBean> datas) {
        mContext = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public MyOtcListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_my_otc, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOtcListAdapter.ViewHolder holder, int position) {
        if (holder == null) return;
        final MyOtcListBean data = datas.get(position);
        if (data == null) return;

        holder.tv_name.setText(data.getName());

        holder.tv_status.setText(data.getSendStatusStr());
        holder.tv_value.setText(data.getCount() + "");
        holder.tv_status.setBackgroundResource(R.color.color_E5F2FF);
        holder.tv_status.setTextColor(ContextCompat.getColor(mContext,R.color.color_368feb));
        holder.tv_value.setTextColor(ContextCompat.getColor(mContext,R.color.color_368feb));
        if(data.getSendStatus() == 0){
            holder.tv_status.setBackgroundResource(R.color.color_61B53F_33);
            holder.tv_status.setTextColor(ContextCompat.getColor(mContext,R.color.color_61B53F));
            holder.tv_value.setTextColor(ContextCompat.getColor(mContext,R.color.color_61B53F));
        }else if(data.getSendStatus() == 9 || data.getSendStatus() == 10){
            holder.tv_status.setBackgroundResource(R.color.color_66_33);
            holder.tv_status.setTextColor(ContextCompat.getColor(mContext,R.color.color_66));
            holder.tv_value.setTextColor(ContextCompat.getColor(mContext,R.color.color_66));
        }

        holder.tv_time.setText(data.getCreateTime());

        if (mOnItemClickListener != null) {
            holder.cl_parent.setOnClickListener(v -> mOnItemClickListener.onClick(position));
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setData(List<MyOtcListBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void addData(List<MyOtcListBean> datas) {
        this.datas.addAll(datas);
        setData(this.datas);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name,tv_status,tv_time,tv_value;
        ConstraintLayout cl_parent;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_value = itemView.findViewById(R.id.tv_value);
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
