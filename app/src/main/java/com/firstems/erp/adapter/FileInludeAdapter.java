package com.firstems.erp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firstems.erp.R;
import com.firstems.erp.callback.ItemFileClick;
import com.firstems.erp.model.FileIncludeModel;

import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 7/15/2020.
 */
public class FileInludeAdapter extends RecyclerView.Adapter<FileInludeAdapter.ViewHolder> {
    private List<FileIncludeModel> list;
    private ItemFileClick itemFileClick;
    private boolean removeIsVisible = true;
    
    public void setRemoveIsVisible(boolean removeIsVisible) {
        this.removeIsVisible = removeIsVisible;
    }
    
    public FileInludeAdapter(List<FileIncludeModel> list, ItemFileClick itemFileClick) {
        this.list = list;
        this.itemFileClick = itemFileClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.item_file,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FileIncludeModel fileIncludeModel = list.get(position);
        holder.txtName.setText(fileIncludeModel.getFileName());
        holder.imgIcon.setImageResource(fileIncludeModel.getIcon());
        holder.imgRemove.setVisibility(removeIsVisible ? View.VISIBLE : View.GONE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemFileClick.onItemClick(fileIncludeModel);
            }
        });
        holder.imgRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemFileClick.onRemoveClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtName;
        ImageView imgIcon;
        ImageView imgRemove, imgOpen;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName=itemView.findViewById(R.id.txtName);
            imgIcon = itemView.findViewById(R.id.imgIcon);
            imgRemove = itemView.findViewById(R.id.imgRemove);
            imgOpen = itemView.findViewById(R.id.imgOpen);
        }
    }
}
