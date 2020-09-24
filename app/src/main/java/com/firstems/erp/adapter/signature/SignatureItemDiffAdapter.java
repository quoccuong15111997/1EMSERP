package com.firstems.erp.adapter.signature;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.firstems.erp.R;
import com.firstems.erp.adapter.diff.signature.SignatureDiffUtilCallBack;
import com.firstems.erp.api.model.response.signature.SignatureItemApiResponse;
import com.firstems.erp.callback.OnAddNewSignatureCallback;
import com.firstems.erp.callback.SignatureItemClickCallback;
import com.firstems.erp.common.Util;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;

import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/1/2020.
 */
public class SignatureItemDiffAdapter extends RecyclerView.Adapter<SignatureItemDiffAdapter.ViewHolder>{
    private List<SignatureItemApiResponse> list;
    private SignatureItemClickCallback signatureItemClickCallback;
    private OnAddNewSignatureCallback onAddNewSignatureCallback;
    
    public SignatureItemDiffAdapter(List<SignatureItemApiResponse> list, SignatureItemClickCallback signatureItemClickCallback) {
        this.list = list;
        this.signatureItemClickCallback = signatureItemClickCallback;
    }
    
    public SignatureItemDiffAdapter(List<SignatureItemApiResponse> list) {
        this.list = list;
    }
    
    public void setSignatureItemClickCallback(SignatureItemClickCallback signatureItemClickCallback) {
        this.signatureItemClickCallback = signatureItemClickCallback;
    }
    
    public void setOnAddNewSignatureCallback(OnAddNewSignatureCallback onAddNewSignatureCallback) {
        this.onAddNewSignatureCallback = onAddNewSignatureCallback;
    }
    
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.item_signature_diff,parent,false);
        return new SignatureItemDiffAdapter.ViewHolder(view);
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
        
        holder.txtContent.setText(item.getDcmnName());
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
        holder.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddNewSignatureCallback.onAddClick(item);
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMainCode, txtTiltleNote, txtNote, txtTileStatus, txtStatus, txtLabel, txtlabelValue, txtUom, txtContent,txtNumber;
        ImageView imgExpan;
        ImageView imgAdd;
        
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
            txtContent = itemView.findViewById(R.id.txtContent);
            
            imgExpan=itemView.findViewById(R.id.imgExpan);
            txtNumber=itemView.findViewById(R.id.txtNumber);
            imgAdd=itemView.findViewById(R.id.imgAdd);
            
        }
    }
    
    public List<SignatureItemApiResponse> getList() {
        return list;
    }
    
    public void setData(List<SignatureItemApiResponse> listNew) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new SignatureDiffUtilCallBack(listNew,list));
      
        list.clear();
        this.list.addAll(listNew);
        
        diffResult.dispatchUpdatesTo(this);
    }
}
