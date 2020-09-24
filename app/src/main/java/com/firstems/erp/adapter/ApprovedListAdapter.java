package com.firstems.erp.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firstems.erp.R;
import com.firstems.erp.adapter.diff.approved.ApprovedDetailDiffUtilCallback;
import com.firstems.erp.api.model.response.approved.ApprovedItemApiResponse;
import com.firstems.erp.api.model.response.approved.ApprovedItemDetail_1;
import com.firstems.erp.callback.AprrovedDetail_1_ClickListener;
import com.firstems.erp.common.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/25/2020.
 */
public class ApprovedListAdapter extends RecyclerView.Adapter<ApprovedListAdapter.ViewHolder> {
    private List<ApprovedItemApiResponse> list;
    private AprrovedDetail_1_ClickListener approvedItemDetail_1;

    public ApprovedListAdapter(List<ApprovedItemApiResponse> list, AprrovedDetail_1_ClickListener approvedItemDetail_1) {
        this.list = list;
        this.approvedItemDetail_1 = approvedItemDetail_1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.approved_item_list,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ApprovedItemApiResponse itemApiResponse = list.get(position);
        holder.recyclerView.setVisibility(View.GONE);
        holder.txtName.setText(itemApiResponse.getdCMNNAME());
        holder.approvedItemDetail_1List.clear();
        holder.approvedItemDetail_1List.addAll(itemApiResponse.getApprovedItemDetail_1List());
        holder.approvedDetail_1_adapter.notifyDataSetChanged();
        try {
            holder.txtCount.setText(String.valueOf(itemApiResponse.getApprovedItemDetail_1List().get(0).getApprovedItemDetail_2List().size()));
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        holder.layoutHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.recyclerView.getVisibility()==View.GONE){
                    holder.recyclerView.setVisibility(View.VISIBLE);
                    holder.imgExpaned.setRotation(180);
                }
                else if (holder.recyclerView.getVisibility()==View.VISIBLE){
                    holder.recyclerView.setVisibility(View.GONE);
                    holder.imgExpaned.setRotation(0);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtName;
        TextView txtCount;
        RecyclerView recyclerView;
        ImageView imgExpaned;
        ApprovedDetail_1_Adapter approvedDetail_1_adapter;
        List<ApprovedItemDetail_1> approvedItemDetail_1List;
        ConstraintLayout layoutHeader;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtCount = itemView.findViewById(R.id.txtNumber);
            recyclerView = itemView.findViewById(R.id.recycle);
            imgExpaned = itemView.findViewById(R.id.imgExpanMore);
            approvedItemDetail_1List = new ArrayList<>();
            approvedDetail_1_adapter = new ApprovedDetail_1_Adapter(approvedItemDetail_1List);
            LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(itemView.getContext());
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(approvedDetail_1_adapter);
            approvedDetail_1_adapter.setAprrovedDetail_1_clickListener(approvedItemDetail_1);
            layoutHeader = itemView.findViewById(R.id.constraintLayout15);
        }
    }
    
    public void setDate(List<ApprovedItemApiResponse> listNew) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ApprovedDetailDiffUtilCallback(listNew,list));
        list.clear();
        list.addAll(listNew);
        
        diffResult.dispatchUpdatesTo(this);
    }
    
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads);
        } else {
            Bundle o = (Bundle) payloads.get(0);
            for (String key : o.keySet()) {
                if (key.equals(Constant.NAME_PUT_DIFF_FLAG)){
                    ApprovedItemApiResponse itemApiResponse = list.get(position);
                    holder.txtName.setText(itemApiResponse.getdCMNNAME());
                    holder.approvedDetail_1_adapter.setData(itemApiResponse.getApprovedItemDetail_1List());
                }
            }
        }
    }
}
