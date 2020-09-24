package com.firstems.erp.adapter.table.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractSorterViewHolder;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import com.firstems.erp.R;
import com.firstems.erp.adapter.table.model.CellModel;
import com.firstems.erp.adapter.table.model.ColumnHeaderModel;
import com.firstems.erp.adapter.table.model.RowHeaderModel;
import com.firstems.erp.adapter.table.viewholder.CellViewHolder;
import com.firstems.erp.adapter.table.viewholder.ColumnHeaderViewHolder;
import com.firstems.erp.adapter.table.viewholder.GenderCellViewHolder;
import com.firstems.erp.adapter.table.viewholder.MoneyCellViewHolder;
import com.firstems.erp.adapter.table.viewholder.RowHeaderViewHolder;
import com.firstems.erp.adapter.table.viewmodel.MyTableViewModel;

/**
 * Created by Nguyen Quoc Cuong on 7/27/2020.
 */
public class MyTableAdapter extends AbstractTableAdapter<ColumnHeaderModel, RowHeaderModel,
        CellModel> {

    private MyTableViewModel myTableViewModel;

    public MyTableAdapter(Context p_jContext, MyTableViewModel myTableViewModel) {
        super(p_jContext);
        this.myTableViewModel=myTableViewModel;
    }


    @Override
    public AbstractViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {
        View layout;

        layout = LayoutInflater.from(mContext).inflate(R.layout.tableview_cell_layout,
                parent, false);

        return new CellViewHolder(layout);
    }

    @Override
    public void onBindCellViewHolder(AbstractViewHolder holder, Object p_jValue, int
            p_nXPosition, int p_nYPosition) {
        CellModel cell = (CellModel) p_jValue;

        if (holder instanceof CellViewHolder) {
            // Get the holder to update cell item text
            ((CellViewHolder) holder).setCellModel(cell, p_nXPosition);

        } else if (holder instanceof GenderCellViewHolder) {
            ((GenderCellViewHolder) holder).setCellModel(cell);
        } else if (holder instanceof MoneyCellViewHolder) {
            ((MoneyCellViewHolder) holder).setCellModel(cell);
        }

    }

    @Override
    public AbstractSorterViewHolder onCreateColumnHeaderViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(mContext).inflate(R.layout
                .tableview_column_header_layout, parent, false);

        return new ColumnHeaderViewHolder(layout, getTableView());
    }

    @Override
    public void onBindColumnHeaderViewHolder(AbstractViewHolder holder, Object p_jValue, int
            p_nXPosition) {
        ColumnHeaderModel columnHeader = (ColumnHeaderModel) p_jValue;

        // Get the holder to update cell item text
        ColumnHeaderViewHolder columnHeaderViewHolder = (ColumnHeaderViewHolder) holder;

        columnHeaderViewHolder.setColumnHeaderModel(columnHeader, p_nXPosition);
    }

    @Override
    public AbstractViewHolder onCreateRowHeaderViewHolder(ViewGroup parent, int viewType) {

        // Get Row Header xml Layout
        View layout = LayoutInflater.from(mContext).inflate(R.layout.tableview_row_header_layout,
                parent, false);

        // Create a Row Header ViewHolder
        return new RowHeaderViewHolder(layout);
    }

    @Override
    public void onBindRowHeaderViewHolder(AbstractViewHolder holder, Object p_jValue, int
            p_nYPosition) {

        RowHeaderModel rowHeaderModel = (RowHeaderModel) p_jValue;

        RowHeaderViewHolder rowHeaderViewHolder = (RowHeaderViewHolder) holder;
        rowHeaderViewHolder.row_header_textview.setText(rowHeaderModel.getData());

    }

    @Override
    public View onCreateCornerView() {
        return LayoutInflater.from(mContext).inflate(R.layout.tableview_corner_layout, null, false);
    }

    @Override
    public int getColumnHeaderItemViewType(int position) {
        return 0;
    }

    @Override
    public int getRowHeaderItemViewType(int position) {
        return 0;
    }

    @Override
    public int getCellItemViewType(int position) {
        return 0;
    }


    /**
     * This method is not a generic Adapter method. It helps to generate lists from single user
     * list for this adapter.
     */
    public void setData() {
        // Now we got what we need to show on TableView.
        setAllItems(myTableViewModel.getColumHeaderModeList(), myTableViewModel
                .getRowHeaderModelList(), myTableViewModel.getCellModelList());
    }
}