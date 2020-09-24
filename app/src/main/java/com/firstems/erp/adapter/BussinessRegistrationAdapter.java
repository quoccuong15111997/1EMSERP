package com.firstems.erp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firstems.erp.R;
import com.firstems.erp.model.business.Business;

import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/17/2020.
 */
public class BussinessRegistrationAdapter extends RecyclerView.Adapter<BussinessRegistrationAdapter.ViewHolder> {
    public interface OnItemClick{
        void onEdit(int position);
        void onDelete(int position);
    }
    private List<Business> list;
    private OnItemClick onItemClick;
    private boolean isEdit = true;

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

    public BussinessRegistrationAdapter(List<Business> list, OnItemClick onItemClick) {
        this.list = list;
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_item_bussiness_registration,parent,false);
        return new BussinessRegistrationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Business business = list.get(position);
        holder.txtBeginDate.setText(business.getDateBeginDisplay());
        holder.txtendDate.setText(business.getDateEndDisplay());

        if (business.getTimekeepingTypeCTMorn()!=null){
            holder.llSang.setVisibility(View.VISIBLE);
            holder.txtContentSang.setText(business.getTimekeepingTypeCTMorn().getItemName());
        }
        else {
            holder.llSang.setVisibility(View.GONE);
        }
        if (business.getTimekeepingTypeCTAfft()!=null){
            holder.llChieu.setVisibility(View.VISIBLE);
            holder.txtContentChieu.setText(business.getTimekeepingTypeCTAfft().getItemName());
        }
        else {
            holder.llChieu.setVisibility(View.GONE);
        }
        if (business.getTimekeepingTypeCTEvrn()!=null){
            holder.llToi.setVisibility(View.VISIBLE);
            holder.txtContentToi.setText(business.getTimekeepingTypeCTEvrn().getItemName());
        }
        else {
            holder.llToi.setVisibility(View.GONE);
        }
        if (isEdit){
            holder.llAdd.setVisibility(View.VISIBLE);
        }
        else {
            holder.llAdd.setVisibility(View.INVISIBLE);
        }
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onEdit(position);
            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onDelete(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtBeginDate, txtendDate, txtContentSang, txtContentChieu, txtContentToi, txtSum;
        ImageView imgDelete, imgEdit;
        LinearLayout llSang, llChieu, llToi, llAdd;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtBeginDate=itemView.findViewById(R.id.txtBeginDate);
            txtendDate =itemView.findViewById(R.id.txtEndDate);
            txtContentSang=itemView.findViewById(R.id.txtContentSang);
            txtContentChieu=itemView.findViewById(R.id.txtContentChieu);
            txtContentToi=itemView.findViewById(R.id.txtContentToi);
            txtSum = itemView.findViewById(R.id.txtSum);
            imgDelete=itemView.findViewById(R.id.imgDelete);
            imgEdit=itemView.findViewById(R.id.imgEdit);

            llSang=itemView.findViewById(R.id.llSang);
            llChieu=itemView.findViewById(R.id.llChieu);
            llToi=itemView.findViewById(R.id.llToi);
            llAdd =itemView.findViewById(R.id.imgAdd);
        }
    }
}
