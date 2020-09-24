package com.firstems.erp.ui.table;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.evrencoskun.tableview.TableView;
import com.firstems.erp.R;
import com.firstems.erp.adapter.table.adapter.MyTableAdapter;
import com.firstems.erp.adapter.table.listener.MyTableViewListener;
import com.firstems.erp.adapter.table.model.CellModel;
import com.firstems.erp.callback.CreateDatatableCallback;
import com.firstems.erp.databinding.DataTableFragmentBinding;
import com.firstems.erp.helper.table.DataTableHelper;
import com.firstems.erp.helper.table.model.TableHeader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DataTableFragmentFragment extends Fragment {
    private DataTableViewModel mViewModel;
    private DataTableFragmentBinding binding;
    private TableView tableView;
    private ProgressBar mProgressBar;

    public DataTableFragmentFragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.data_table_fragment, container, false);

        addControls();
        addEvents();
        return binding.getRoot();
    }

    private void addEvents() {
    }

    private void addControls() {
        mProgressBar=binding.progressBar;
        tableView=binding.myTableView;
        showProgressBar();
        List<TableHeader> tableHeaders= new ArrayList<>();
        tableHeaders.addAll(getListHeader());
        List<List<CellModel>> listCell = new ArrayList<>();
        listCell.addAll(getListData());
        int size = 10000;
        DataTableHelper.getInstance().createDataTable(getContext(), tableHeaders, listCell, size, new CreateDatatableCallback() {
            @Override
            public void onCreate(MyTableAdapter adapter) {
                tableView.setAdapter(adapter);
                tableView.setTableViewListener(new MyTableViewListener(tableView));
                adapter.setData();
                hideProgressBar();
            }
        });

    }

    private List<List<CellModel>> getListData() {
        List<List<CellModel>> listMain= new ArrayList<>();
        for (int i =1;i<10000;i++){
            List<CellModel> listItem= new ArrayList<>();
            listItem.add(new CellModel("1","2020"+i));
            listItem.add(new CellModel("2","1,364,051,041"+i));
            listItem.add(new CellModel("3","1,364,051,041"+i));
            listItem.add(new CellModel("4","0"+i));
            listItem.add(new CellModel("5","0"+i));
            listItem.add(new CellModel("6","0"+i));
            listItem.add(new CellModel("7","0"+i));
            listItem.add(new CellModel("8","0"+i));
            listItem.add(new CellModel("9","0"+i));
            listItem.add(new CellModel("10","0"+i));
            listItem.add(new CellModel("11","0"+i));
            listItem.add(new CellModel("12","0"+i));
            listItem.add(new CellModel("13","0"+i));
            listItem.add(new CellModel("14","0"+i));
            listMain.add(listItem);
        }
        return listMain;
    }

    private List<TableHeader> getListHeader() {
        List<TableHeader> tableHead= new ArrayList<>();
        tableHead.add(new TableHeader("Chu kỳ",1));
        tableHead.add(new TableHeader("Doanh thu thực tế",2));
        tableHead.add(new TableHeader("Tháng 1",3));
        tableHead.add(new TableHeader("Tháng 2",4));
        tableHead.add(new TableHeader("Tháng 3",5));
        tableHead.add(new TableHeader("Tháng 4",6));
        tableHead.add(new TableHeader("Tháng 5",7));
        tableHead.add(new TableHeader("Tháng 6",8));
        tableHead.add(new TableHeader("Tháng 7",9));
        tableHead.add(new TableHeader("Tháng 8",10));
        tableHead.add(new TableHeader("Tháng 9",11));
        tableHead.add(new TableHeader("Tháng 10",12));
        tableHead.add(new TableHeader("Tháng 11",13));
        tableHead.add(new TableHeader("Tháng 12",14));

        return tableHead;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DataTableViewModel.class);
    }
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
        tableView.setVisibility(View.INVISIBLE);
    }

    public void hideProgressBar() {
        mProgressBar.setVisibility(View.INVISIBLE);
        tableView.setVisibility(View.VISIBLE);
    }
}