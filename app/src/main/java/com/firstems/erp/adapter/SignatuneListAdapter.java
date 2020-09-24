package com.firstems.erp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firstems.erp.R;
import com.firstems.erp.api.model.response.signature.SignatureItemApiResponse;
import com.firstems.erp.callback.OnAddNewSignatureCallback;
import com.firstems.erp.callback.SignatureItemClickCallback;
import com.firstems.erp.utils.ItemAnimation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SignatuneListAdapter extends RecyclerView.Adapter<SignatuneListAdapter.ViewHolder>{
    private HashMap<String, List<SignatureItemApiResponse>> hashMap;
    private List<String> key;
    private OnAddNewSignatureCallback onAddNewSignatureCallback;
    private SignatureItemClickCallback signatureItemClickCallback;

    public SignatuneListAdapter(HashMap<String, List<SignatureItemApiResponse>> hashMap, OnAddNewSignatureCallback onAddNewSignatureCallback,SignatureItemClickCallback signatureItemClickCallback) {
        this.hashMap = hashMap;
        this.onAddNewSignatureCallback = onAddNewSignatureCallback;
        this.signatureItemClickCallback= signatureItemClickCallback;
        key= new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.item_signature_card_custom,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        key.clear();
        key.addAll(hashMap.keySet());
        List<SignatureItemApiResponse> model = hashMap.get(key.get(position));
        holder.txtContent.setText(model.get(0).getDcmnName());
        holder.txtNumber.setText(String.valueOf(model.size()));
        holder.signatureItemAdapter.setData(model);
        holder.recyclerViewItem.setVisibility(View.GONE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  holder.progressBar.setVisibility(View.VISIBLE);
                if (holder.recyclerViewItem.getVisibility()==View.GONE)
                {
                    holder.recyclerViewItem.setVisibility(View.VISIBLE);
                    holder.imgExpan.setImageResource(R.drawable.ic_baseline_expand_less_24);
                }
                else
                {
                    holder.recyclerViewItem.setVisibility(View.GONE);
                    holder.imgExpan.setImageResource(R.drawable.ic_baseline_expand_more_24);
                }
            }
        });
        if (model.get(0).getAddVchr()==0){
            holder.imgAdd.setVisibility(View.INVISIBLE);
        }
        else
            holder.imgAdd.setVisibility(View.VISIBLE);
        holder.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddNewSignatureCallback.onAddClick(model.get(0));
            }
        });

    }
    @Override
    public int getItemCount() {
        return hashMap.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtContent, txtNumber;
        ConstraintLayout constraintLayout;
        ImageView imgAdd;
        RecyclerView recyclerViewItem;
        SignatureItemAdapter signatureItemAdapter;
        List<SignatureItemApiResponse> list;
        ImageView imgExpan;
        ProgressBar progressBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtContent=itemView.findViewById(R.id.txtContent);
            txtNumber=itemView.findViewById(R.id.txtNumber);
            imgAdd=itemView.findViewById(R.id.imgAdd);
            constraintLayout=itemView.findViewById(R.id.constrainMain);
            recyclerViewItem=itemView.findViewById(R.id.recycleItem);
            list= new ArrayList<>();
            signatureItemAdapter= new SignatureItemAdapter(list,signatureItemClickCallback);
            signatureItemAdapter.setAnimation_type(ItemAnimation.RIGHT_LEFT);
            LinearLayoutManager linearLayoutManager= new LinearLayoutManager(itemView.getContext());
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(itemView.getContext(), linearLayoutManager.getOrientation());
            recyclerViewItem.addItemDecoration(dividerItemDecoration);
            recyclerViewItem.setAdapter(signatureItemAdapter);
            recyclerViewItem.setLayoutManager(linearLayoutManager);
            recyclerViewItem.setVisibility(View.GONE);
            imgExpan=itemView.findViewById(R.id.imgExpan);
            progressBar=itemView.findViewById(R.id.progressBar);
            progressBar.setVisibility(View.GONE);
            //CustomUIHelper.getInstance().customBackgroundConstrainLayoutTitle(itemView.getContext(),constraintLayout);
        }
    }
    
    public HashMap<String, List<SignatureItemApiResponse>> getHashMap() {
        return hashMap;
    }
    
    public void setData(HashMap<String, List<SignatureItemApiResponse>> hashMapNew) {
        this.hashMap = hashMapNew;
    }
}
