package com.firstems.erp.helper.table;

import android.content.Context;

import com.firstems.erp.adapter.table.adapter.MyTableAdapter;
import com.firstems.erp.adapter.table.model.CellModel;
import com.firstems.erp.adapter.table.model.ColumnHeaderModel;
import com.firstems.erp.adapter.table.viewmodel.MyTableViewModel;
import com.firstems.erp.callback.CreateDatatableCallback;
import com.firstems.erp.helper.datetime.DateTimeHelper;
import com.firstems.erp.helper.table.model.TableHeader;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen Quoc Cuong on 7/27/2020.
 */
public class DataTableHelper {
    private static DataTableHelper instance;

    private DataTableHelper() {
    }
    public static DataTableHelper getInstance(){
        if (instance== null){
            instance= new DataTableHelper();
        }
        return  instance;
    }

    public void createDataTable(Context context,List<TableHeader> tableHeaders, List<List<CellModel>> mCellModelList, int objectSize, CreateDatatableCallback createDatatableCallback){
        MyTableViewModel myTableViewModel= new MyTableViewModel();
        myTableViewModel.setmColumnHeaderModelList(createHeaderList(tableHeaders));
        myTableViewModel.setmCellModelList(mCellModelList);
        myTableViewModel.setmRowHeaderModelList(myTableViewModel.createRowHeaderList(objectSize));

        MyTableAdapter myTableAdapter= new MyTableAdapter(context,myTableViewModel);
        createDatatableCallback.onCreate(myTableAdapter);
    }

    private List<ColumnHeaderModel> createHeaderList(List<TableHeader> tableHeaders) {
        List<ColumnHeaderModel> columnHeaderModels= new ArrayList<>();
        for (TableHeader item :  tableHeaders){
            columnHeaderModels.add(new ColumnHeaderModel(item.getHeaderName()));
        }
        return columnHeaderModels;
    }
}
