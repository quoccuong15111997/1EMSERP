package com.firstems.erp.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firstems.erp.R;
import com.firstems.erp.adapter.diff.approved.ApprovedDetail_1_DiffUtilCallback;
import com.firstems.erp.api.model.response.approved.ApprovedItemDetail_1;
import com.firstems.erp.api.model.response.approved.ApprovedItemDetail_2;
import com.firstems.erp.callback.ApprovedDetail_2_ClickListener;
import com.firstems.erp.callback.AprrovedDetail_1_ClickListener;
import com.firstems.erp.common.Constant;
import com.firstems.erp.utils.ItemAnimation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/25/2020.
 */
public class ApprovedDetail_1_Adapter extends RecyclerView.Adapter<ApprovedDetail_1_Adapter.ViewHolder> {
    private List<ApprovedItemDetail_1> approvedItemDetail_1List;
    private AprrovedDetail_1_ClickListener aprrovedDetail_1_clickListener;

    public void setAprrovedDetail_1_clickListener(AprrovedDetail_1_ClickListener aprrovedDetail_1_clickListener) {
        this.aprrovedDetail_1_clickListener = aprrovedDetail_1_clickListener;
    }

    public ApprovedDetail_1_Adapter(List<ApprovedItemDetail_1> approvedItemDetail_1List) {
        this.approvedItemDetail_1List = approvedItemDetail_1List;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_approved_detail_1,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ApprovedItemDetail_1 approvedItemDetail_1 = approvedItemDetail_1List.get(position);
        holder.txtTitle.setText(approvedItemDetail_1.getsCTNNAME());
        holder.approvedItemDetail_2List.clear();
        holder.approvedItemDetail_2List.addAll(approvedItemDetail_1.getApprovedItemDetail_2List());
        holder.approvedDetail_2_adapter.notifyDataSetChanged();
        holder.approvedDetail_2_adapter.setDetail_2_clickListener(new ApprovedDetail_2_ClickListener() {
            @Override
            public void OnItemClick(ApprovedItemDetail_2 itemDetail_2) {
                aprrovedDetail_1_clickListener.onItemClick(itemDetail_2,approvedItemDetail_1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return approvedItemDetail_1List.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitle;
        ApprovedDetail_2_Adapter approvedDetail_2_adapter;
        List<ApprovedItemDetail_2> approvedItemDetail_2List;
        RecyclerView recyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            recyclerView = itemView.findViewById(R.id.recycleview);
            approvedItemDetail_2List= new ArrayList<>();
            approvedDetail_2_adapter= new ApprovedDetail_2_Adapter(approvedItemDetail_2List);
            approvedDetail_2_adapter.setAnimation_type(ItemAnimation.RIGHT_LEFT);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(itemView.getContext(),linearLayoutManager.getOrientation());
            recyclerView.addItemDecoration(dividerItemDecoration);
            recyclerView.setAdapter(approvedDetail_2_adapter);
            recyclerView.setLayoutManager(linearLayoutManager);
        }
    }
    
    public void setData(List<ApprovedItemDetail_1> newList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ApprovedDetail_1_DiffUtilCallback(newList,approvedItemDetail_1List));
        
        approvedItemDetail_1List.clear();
        approvedItemDetail_1List.addAll(newList);
        
        diffResult.dispatchUpdatesTo(this);
        
    }
    
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
        } else {
            Bundle o = (Bundle) payloads.get(0);
            for (String key : o.keySet()) {
                if (key.equals(Constant.NAME_PUT_DIFF_LIST)){
                    ApprovedItemDetail_1 approvedItemDetail_1 = approvedItemDetail_1List.get(position);
                    holder.txtTitle.setText(approvedItemDetail_1.getsCTNNAME());
                    holder.approvedDetail_2_adapter.setData(approvedItemDetail_1.getApprovedItemDetail_2List());
                }
            }
        }
    }
}
