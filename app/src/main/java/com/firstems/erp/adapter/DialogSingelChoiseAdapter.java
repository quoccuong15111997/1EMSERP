package com.firstems.erp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firstems.erp.R;
import com.firstems.erp.api.model.response.timekeeping.TimekeepingTypeCT;
import com.firstems.erp.api.model.response.timekeeping.TimekeepingTypeDC;
import com.firstems.erp.callback.SingelChoiseDialogCallback;

import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/5/2020.
 */
public class DialogSingelChoiseAdapter extends RecyclerView.Adapter<DialogSingelChoiseAdapter.ViewHolder> {
    private List<TimekeepingTypeDC> list;
    private SingelChoiseDialogCallback singelChoiseDialogCallback;

    public DialogSingelChoiseAdapter(List<TimekeepingTypeDC> list, SingelChoiseDialogCallback singelChoiseDialogCallback) {
        this.list = list;
        this.singelChoiseDialogCallback = singelChoiseDialogCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.item_dialog_singel_choise,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TimekeepingTypeDC object = list.get(position);
            holder.txtContent.setText(object.getItemName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singelChoiseDialogCallback.itemSelected(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtContent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtContent=itemView.findViewById(R.id.txtContent);
        }
    }
}
