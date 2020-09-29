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
import com.firstems.erp.api.model.response.location.LocationResponse;
import com.firstems.erp.database.model.LocationDBModel;

import java.util.List;

public class SelectLocationAdapter extends RecyclerView.Adapter<SelectLocationAdapter.ViewHolder> {
    private List<LocationResponse> locationDBModels;
    private SelectLocationClickListener dialogTopHomeOnClickListener;
    public interface SelectLocationClickListener{
        void onItemClick(LocationResponse model);
    }
    
    public void setDialogTopHomeOnClickListener(SelectLocationClickListener dialogTopHomeOnClickListener) {
        this.dialogTopHomeOnClickListener = dialogTopHomeOnClickListener;
    }
    
    public SelectLocationAdapter(List<LocationResponse> locationDBModels) {
        this.locationDBModels = locationDBModels;
    }
    
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_location_select, parent,false);
        return new ViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LocationResponse locationDBModel = locationDBModels.get(position);
        holder.txtName.setText(locationDBModel.getLocationName());
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
        TextView txtName;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.lAssets);
            txtName=itemView.findViewById(R.id.txtLocatioName);
        }
    }
}
