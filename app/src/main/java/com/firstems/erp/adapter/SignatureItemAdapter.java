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
import com.firstems.erp.adapter.diff.signature.SignatureDiffUtilCallBack;
import com.firstems.erp.api.model.response.signature.SignatureItemApiResponse;
import com.firstems.erp.callback.SignatureItemClickCallback;
import com.firstems.erp.common.Util;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;
import com.firstems.erp.utils.ItemAnimation;

import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/1/2020.
 */
public class SignatureItemAdapter extends RecyclerView.Adapter<SignatureItemAdapter.ViewHolder>{
    private List<SignatureItemApiResponse> list;
    private SignatureItemClickCallback signatureItemClickCallback;
    private int animation_type = 0;
    
    public void setAnimation_type(int animation_type) {
        this.animation_type = animation_type;
    }
    public SignatureItemAdapter(List<SignatureItemApiResponse> list, SignatureItemClickCallback signatureItemClickCallback) {
        this.list = list;
        this.signatureItemClickCallback = signatureItemClickCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.item_signature_item,parent,false);
        return new SignatureItemAdapter.ViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
            if (payloads.isEmpty()) {
                super.onBindViewHolder(holder, position, payloads);
            } else {
                Bundle o = (Bundle) payloads.get(0);
                for (String key : o.keySet()) {
                if (key.equals("keyCode") || key.equals("valueName") || key.equals("valueField") || key.equals("keyCode") || key.equals("mainCode") || key.equals("mainDate")) {
                    SignatureItemApiResponse item = list.get(position);
                    holder.txtMainCode.setText(item.getMainCode()+" - "+ Util.formatDate(item.getMainDate()));
                    holder.txtNote.setText(item.getNote());
                    holder.txtStatus.setText(item.getStatusName());
    
                    holder.txtTiltleNote.setText(SharedPreferencesManager.getSystemLabel(21));
                    holder.txtTileStatus.setText(SharedPreferencesManager.getSystemLabel(22));
    
                    if (item.getValueFld()<0){
                        holder.txtUom.setVisibility(View.GONE);
                        holder.txtlabelValue.setVisibility(View.GONE);
                        holder.txtLabel.setVisibility(View.GONE);
                    }
                    else {
                        holder.txtUom.setVisibility(View.VISIBLE);
                        holder.txtlabelValue.setVisibility(View.VISIBLE);
                        holder.txtLabel.setVisibility(View.VISIBLE);
        
                        holder.txtUom.setText(item.getUomExpr());
                        holder.txtlabelValue.setText(String.valueOf((int) item.getValueFld()));
                        holder.txtLabel.setText(item.getValueName());
                    }
                }
            }
        }
    }
    
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SignatureItemApiResponse item = list.get(position);
        holder.txtMainCode.setText(item.getMainCode()+" - "+ Util.formatDate(item.getMainDate()));
        holder.txtNote.setText(item.getNote());
        holder.txtStatus.setText(item.getStatusName());
        
        holder.txtTiltleNote.setText(SharedPreferencesManager.getSystemLabel(21));
        holder.txtTileStatus.setText(SharedPreferencesManager.getSystemLabel(22));
        
        if (item.getValueFld()<0){
            holder.txtUom.setVisibility(View.GONE);
            holder.txtlabelValue.setVisibility(View.GONE);
            holder.txtLabel.setVisibility(View.GONE);
        }
        else {
            holder.txtUom.setVisibility(View.VISIBLE);
            holder.txtlabelValue.setVisibility(View.VISIBLE);
            holder.txtLabel.setVisibility(View.VISIBLE);

            holder.txtUom.setText(item.getUomExpr());
            holder.txtlabelValue.setText(String.valueOf((int) item.getValueFld()));
            holder.txtLabel.setText(item.getValueName());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signatureItemClickCallback.ItemClick(position,item);
            }
        });
        //setAnimation(holder.itemView, position);
    }
   /* @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                on_attach = false;
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        super.onAttachedToRecyclerView(recyclerView);
    }*/
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMainCode, txtTiltleNote, txtNote, txtTileStatus, txtStatus, txtLabel, txtlabelValue, txtUom;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMainCode=itemView.findViewById(R.id.txtMainCode);
            txtTiltleNote= itemView.findViewById(R.id.txtTitleNote);
            txtTileStatus=itemView.findViewById(R.id.txtTileStatus);
            txtNote=itemView.findViewById(R.id.txtNote);
            txtStatus=itemView.findViewById(R.id.txtStatus);

            txtLabel=itemView.findViewById(R.id.txtLabel);
            txtlabelValue=itemView.findViewById(R.id.txtLabelValue);
            txtUom=itemView.findViewById(R.id.txtUom);
        }
    }
    private int lastPosition = -1;
    private boolean on_attach = true;
    
    private void setAnimation(View view, int position) {
        if (position > lastPosition) {
            ItemAnimation.animate(view, on_attach ? position : -1, animation_type);
            lastPosition = position;
        }
    }
    
    public List<SignatureItemApiResponse> getList() {
        return list;
    }
    
    public void setData(List<SignatureItemApiResponse> listNew) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new SignatureDiffUtilCallBack(listNew,list));
        diffResult.dispatchUpdatesTo(this);
        list.clear();
        this.list.addAll(listNew);
    }
}
