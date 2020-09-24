package com.firstems.erp.adapter.table.viewmodel;

import android.view.Gravity;


import com.firstems.erp.adapter.table.model.CellModel;
import com.firstems.erp.adapter.table.model.ColumnHeaderModel;
import com.firstems.erp.adapter.table.model.RowHeaderModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 7/27/2020.
 */
public class MyTableViewModel {
    private List<ColumnHeaderModel> mColumnHeaderModelList;
    private List<RowHeaderModel> mRowHeaderModelList;
    private List<List<CellModel>> mCellModelList;

    public void setmColumnHeaderModelList(List<ColumnHeaderModel> mColumnHeaderModelList) {
        this.mColumnHeaderModelList = mColumnHeaderModelList;
    }

    public void setmRowHeaderModelList(List<RowHeaderModel> mRowHeaderModelList) {
        this.mRowHeaderModelList = mRowHeaderModelList;
    }

    public void setmCellModelList(List<List<CellModel>> mCellModelList) {
        this.mCellModelList = mCellModelList;
    }

    public int getColumnTextAlign(int column) {
        switch (column) {
            // Id
            case 0:
                return Gravity.CENTER;
            // Name
            case 1:
                return Gravity.RIGHT;
            // Nickname
            case 2:
                return Gravity.RIGHT;
            // Email
            case 3:
                return Gravity.RIGHT;
            // BirthDay
            case 4:
                return Gravity.RIGHT;
            // Gender (Sex)
            case 5:
                return Gravity.RIGHT;
            // Age
            case 6:
                return Gravity.RIGHT;
            // Job
            case 7:
                return Gravity.RIGHT;
            // Salary
            case 8:
                return Gravity.RIGHT;
            // CreatedAt
            case 9:
                return Gravity.RIGHT;
            // UpdatedAt
            case 10:
                return Gravity.RIGHT;
            // Address
            case 11:
                return Gravity.RIGHT;
            // Zip Code
            case 12:
                return Gravity.RIGHT;
            // Phone
            case 13:
                return Gravity.RIGHT;
            // Fax
            case 14:
                return Gravity.RIGHT;
            default:
                return Gravity.CENTER;
        }

    }
    public List<RowHeaderModel> createRowHeaderList(int size) {
        List<RowHeaderModel> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(new RowHeaderModel(String.valueOf(i + 1)));
        }
        return list;
    }


    public List<ColumnHeaderModel> getColumHeaderModeList() {
        return mColumnHeaderModelList;
    }

    public List<RowHeaderModel> getRowHeaderModelList() {
        return mRowHeaderModelList;
    }

    public List<List<CellModel>> getCellModelList() {
        return mCellModelList;
    }


    /*public void generateListForTableView(List<User> users) {
        mColumnHeaderModelList = createColumnHeaderModelList();
        mCellModelList = createCellModelList(users);
        mRowHeaderModelList = createRowHeaderList(users.size());
    }*/
}
