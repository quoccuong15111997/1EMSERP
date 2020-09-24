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
import com.firstems.erp.database.model.LocationDBModel;

import java.util.List;

public class DialogTopHomeAdapter extends RecyclerView.Adapter<DialogTopHomeAdapter.ViewHolder> {
    private List<LocationDBModel> locationDBModels;
    private DialogTopHomeOnClickListener dialogTopHomeOnClickListener;
    public interface DialogTopHomeOnClickListener{
        void onItemClick(LocationDBModel model);
    }
    
    public void setDialogTopHomeOnClickListener(DialogTopHomeOnClickListener dialogTopHomeOnClickListener) {
        this.dialogTopHomeOnClickListener = dialogTopHomeOnClickListener;
    }
    
    public DialogTopHomeAdapter(List<LocationDBModel> locationDBModels) {
        this.locationDBModels = locationDBModels;
    }
    
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_top_home, parent,false);
        return new ViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LocationDBModel locationDBModel = locationDBModels.get(position);
        holder.txtName.setText(locationDBModel.getLocationName());
        holder.imgCheck.setVisibility(locationDBModel.isCheck() ? View.VISIBLE : View.INVISIBLE);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogTopHomeOnClickListener.onItemClick(locationDBModel);
            }
        });
        
    }
    
    @Override
    public int getItemCount() {
        return locationDBModels.size();
    }
    
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgCheck;
        TextView txtName;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.lAssets);
            imgCheck = itemView.findViewById(R.id.ivCheckAssets);
            txtName=itemView.findViewById(R.id.tvAssets);
        }
    }
}
