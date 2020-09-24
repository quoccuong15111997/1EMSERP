package com.firstems.erp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firstems.erp.R;
import com.firstems.erp.api.model.response.document.DocumentItemApiResponse;
import com.firstems.erp.utils.ItemAnimation;

import java.util.List;

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.ViewHolder> {
    public interface DocumentItemClickListener{
        void onDocumentItemClick(int position, DocumentItemApiResponse item);
    }
    private List<DocumentItemApiResponse> list;
    private DocumentItemClickListener documentItemClickListener;
    private int animation_type = 0;
    
    public void setDocumentItemClickListener(DocumentItemClickListener documentItemClickListener) {
        this.documentItemClickListener = documentItemClickListener;
    }
    
    public void setAnimation_type(int animation_type) {
        this.animation_type = animation_type;
    }
    
    public DocumentAdapter(List<DocumentItemApiResponse> list) {
        this.list = list;
    }
    
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_documnet, parent, false);
        return new ViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DocumentItemApiResponse documentItemApiResponse = list.get(position);
        holder.txtName.setText(documentItemApiResponse.getcNTNBRIF());
        holder.txtStt.setText(String.valueOf(position)+".");
        holder.txtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                documentItemClickListener.onDocumentItemClick(position,documentItemApiResponse);
            }
        });
        //setAnimation(holder.itemView, position);
    }
  /*  @Override
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
    
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtStt, txtName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtStt = itemView.findViewById(R.id.txtStt);
            txtName = itemView.findViewById(R.id.txtDocumentName);
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
}
