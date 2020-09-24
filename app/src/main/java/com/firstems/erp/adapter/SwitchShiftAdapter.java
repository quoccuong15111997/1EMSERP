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
import com.firstems.erp.callback.SwitchShiftItemClickCallback;
import com.firstems.erp.model.switchshift.SwitchShift;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 8/5/2020.
 */
public class SwitchShiftAdapter extends RecyclerView.Adapter<SwitchShiftAdapter.ViewHolder> {
    private List<SwitchShift> list;
    private SwitchShiftItemClickCallback switchShiftItemClickCallback;
    private int role = 0;
    SimpleDateFormat simpleDateFormatDisplay = new SimpleDateFormat("dd/MM/yyyy");

    public SwitchShiftAdapter(List<SwitchShift> list, SwitchShiftItemClickCallback switchShiftItemClickCallback) {
        this.list = list;
        this.switchShiftItemClickCallback = switchShiftItemClickCallback;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.item_switch_shift,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SwitchShift item =list.get(position);
        holder.txtBeginDate.setText(simpleDateFormatDisplay.format(item.getDateBegin()));
        holder.txtendDate.setText(simpleDateFormatDisplay.format(item.getDateEnd()));


        if (!item.isMorning()){
            holder.llSang.setVisibility(View.GONE);
        }
        else
            holder.llSang.setVisibility(View.VISIBLE);
        if (!item.isAfternoon()){
            holder.llChieu.setVisibility(View.GONE);
        }
        else
            holder.llChieu.setVisibility(View.VISIBLE);
        if (!item.isEverning()){
            holder.llToi.setVisibility(View.GONE);
        }
        else
            holder.llToi.setVisibility(View.VISIBLE);

        holder.txtContentSang.setText(item.getContentMornig()!=null && item.getContentMornig().getItemName() !=null ? item.getContentMornig().getItemName() : "");
        holder.txtContentChieu.setText(item.getContentAfternoon()!=null && item.getContentAfternoon().getItemName() !=null ? item.getContentAfternoon().getItemName() : "");
        holder.txtContentToi.setText(item.getContentEverning() !=null && item.getContentEverning().getItemName()!=null ? item.getContentEverning().getItemName() : "");

        long time=  ((item.getDateEnd().getTime() - item.getDateBegin().getTime())/ (1000 * 60 * 60 * 24) % 365)+1;
        holder.txtSum.setText(String.valueOf(time));
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchShiftItemClickCallback.onEditClick(position);
            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchShiftItemClickCallback.onDeleteClick(position);
            }
        });

        holder.imgEdit.setVisibility(role == 1 ? View.VISIBLE : View.INVISIBLE);
        holder.imgDelete.setVisibility(role == 1 ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtBeginDate, txtendDate, txtContentSang, txtContentChieu, txtContentToi, txtSum;
        ImageView imgDelete, imgEdit;
        LinearLayout llSang, llChieu, llToi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtBeginDate=itemView.findViewById(R.id.txtBeginDate);
            txtendDate =itemView.findViewById(R.id.txtEndDate);
            txtContentSang=itemView.findViewById(R.id.txtContentSang);
            txtContentChieu=itemView.findViewById(R.id.txtContentChieu);
            txtContentToi=itemView.findViewById(R.id.txtContentToi);
            txtSum = itemView.findViewById(R.id.txtSum);
            imgDelete=itemView.findViewById(R.id.imgDelete);
            imgEdit=itemView.findViewById(R.id.imgEdit);

            llSang=itemView.findViewById(R.id.llSang);
            llChieu=itemView.findViewById(R.id.llChieu);
            llToi=itemView.findViewById(R.id.llToi);
        }
    }
}
