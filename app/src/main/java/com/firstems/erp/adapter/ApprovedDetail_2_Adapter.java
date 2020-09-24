package com.firstems.erp.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.firstems.erp.R;
import com.firstems.erp.adapter.diff.approved.ApprovedDetail_2_DiffUtilCallback;
import com.firstems.erp.api.model.response.approved.ApprovedItemDetail_2;
import com.firstems.erp.callback.ApprovedDetail_2_ClickListener;
import com.firstems.erp.common.Constant;

import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/25/2020.
 */
public class ApprovedDetail_2_Adapter extends RecyclerView.Adapter<ApprovedDetail_2_Adapter.ViewHolder> {
    private List<ApprovedItemDetail_2> approvedItemDetail_2List;
    private ApprovedDetail_2_ClickListener detail_2_clickListener;
    private int animation_type = 0;
    
    public void setAnimation_type(int animation_type) {
        this.animation_type = animation_type;
    }
    
    public ApprovedDetail_2_Adapter(List<ApprovedItemDetail_2> approvedItemDetail_2List) {
        this.approvedItemDetail_2List = approvedItemDetail_2List;
    }
    
    public void setDetail_2_clickListener(ApprovedDetail_2_ClickListener detail_2_clickListener) {
        this.detail_2_clickListener = detail_2_clickListener;
    }
    
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate((R.layout.item_approved_detail_2),parent,false);
        return new ViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ApprovedItemDetail_2 item = approvedItemDetail_2List.get(position);
        holder.txtMainCode.setText(item.getmAINCODE()+" - "+item.geteMPLNAME());
        holder.txtMainCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detail_2_clickListener.OnItemClick(item);
            }
        });
    }
    
    @Override
    public int getItemCount() {
        return approvedItemDetail_2List.size();
    }
    
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMainCode;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMainCode = itemView.findViewById(R.id.txtMainCode);
        }
    }
    
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
        } else {
            Bundle o = (Bundle) payloads.get(0);
            for (String key : o.keySet()) {
                if (key.equals(Constant.NAME_PUT_DIFF_FLAG)){
                    ApprovedItemDetail_2 item = approvedItemDetail_2List.get(position);
                    holder.txtMainCode.setText(item.getmAINCODE()+" - "+item.geteMPLNAME());
                }
            }
        }
    }
    
    public void setData(List<ApprovedItemDetail_2> newList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ApprovedDetail_2_DiffUtilCallback(newList, approvedItemDetail_2List));
        approvedItemDetail_2List.clear();
        this.approvedItemDetail_2List.addAll(newList);
        
        diffResult.dispatchUpdatesTo(this);
    }
}
