package com.firstems.erp.adapter.table.listener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.evrencoskun.tableview.ITableView;
import com.evrencoskun.tableview.listener.ITableViewListener;
/**
 * Created by Nguyen Quoc Cuong on 7/27/2020.
 */
public class MyTableViewListener implements ITableViewListener

    {
        private static final String LOG_TAG = MyTableViewListener.class.getSimpleName();

        private ITableView mTableView;

    public MyTableViewListener(ITableView pTableView) {
            this.mTableView = pTableView;
        }


        @Override
        public void onCellClicked(@NonNull RecyclerView.ViewHolder viewHolder, int i, int i1) {

        }

        @Override
        public void onCellLongPressed(@NonNull RecyclerView.ViewHolder viewHolder, int i, int i1) {

        }

        @Override
        public void onColumnHeaderClicked(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        }

        @Override
        public void onColumnHeaderLongPressed(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        }

        @Override
        public void onRowHeaderClicked(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        }

        @Override
        public void onRowHeaderLongPressed(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        }
    }