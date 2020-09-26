package com.firstems.erp.ui.shared.employee;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firstems.erp.R;
import com.firstems.erp.adapter.EmployeeAdapter;
import com.firstems.erp.api.model.response.employee.Employee;
import com.firstems.erp.callback.ItemCheckCallback;
import com.firstems.erp.callback.ServerCheckCallback;
import com.firstems.erp.common.CommonFragment;
import com.firstems.erp.common.Constant;
import com.firstems.erp.databinding.EmployeeFragmentBinding;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeFragment extends CommonFragment implements ItemCheckCallback {
    private EmployeeViewModel mViewModel;
    private EmployeeFragmentBinding binding;
    private RecyclerView recyclerViewEmployee;
    private EmployeeAdapter employeeAdapter;
    private List<Employee> list;
    private List<Employee> listCurrent;
    private int currentPosition=-1;
    private List<Employee> listEmployeeSelect;
    private boolean type = true;
    private TextView txtNumberSelect;
    private String departmentListCode = "";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.employee_fragment, container, false);

        initText();
        addControls();
        addEvents();
        return binding.getRoot();
    }
    
    private void initText() {
        binding.editTextTextPersonName2.setHint(SharedPreferencesManager.getSystemLabel(137));
        binding.radAll.setText(SharedPreferencesManager.getSystemLabel(138));
        binding.radSelected.setText(SharedPreferencesManager.getSystemLabel(139));
        binding.radUnSelected.setText(SharedPreferencesManager.getSystemLabel(140));
    }
    
    private void addEvents() {
        binding.editTextTextPersonName2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                doSearch(s.toString());
            }
        });
        binding.radAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                    if (type){
                        list.clear();
                        list.addAll(listCurrent);
                        clearCheck(listCurrent);
                        setCheck();
                    }
                    else {
                        list.clear();
                        list.addAll(listCurrent);
                    }
                    recyclerViewEmployee.post(new Runnable() {
                        @Override
                        public void run() {
                            employeeAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });
        binding.radSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    list.clear();
                    if (type){
                        list.addAll(listEmployeeSelect);
                    }
                    else {
                        List<Employee> listTemp= new ArrayList<>();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            listTemp.addAll(listCurrent
                                    .stream()
                                    .filter(x -> x.isCheck())
                                    .collect(Collectors.toList()));
                        }
                        list.addAll(listTemp);
                    }
                    recyclerViewEmployee.post(new Runnable() {
                        @Override
                        public void run() {
                            binding.txtNumberSelect.setText("Đã chọn " + list.size());
                            employeeAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });
        binding.radUnSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    list.clear();
                    if (type){
                        list.addAll(listEmployeeSelect);

                    }
                    else {
                        List<Employee> listTemp= new ArrayList<>();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            listTemp.addAll(listCurrent
                                    .stream()
                                    .filter(x -> !x.isCheck())
                                    .collect(Collectors.toList()));
                        }
                        list.addAll(listTemp);
                    }
                    recyclerViewEmployee.post(new Runnable() {
                        @Override
                        public void run() {
                            employeeAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });
        binding.include22.findViewById(R.id.imgDone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();

                if (type){
                    intent.putExtra(Constant.NAME_PUT_EMPLOYEE,listEmployeeSelect.get(0));
                }
                else {
                    List<Employee> listTemp= new ArrayList<>();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        listTemp.addAll(listCurrent
                                .stream()
                                .filter(x -> x.isCheck())
                                .collect(Collectors.toList()));
                    }
                    intent.putExtra(Constant.NAME_PUT_LIST_EMPLOYEE, (Serializable) listTemp);
                }

                getActivity().setResult(Activity.RESULT_OK,intent);
                getActivity().finish();
            }
        });
        binding.include22.findViewById(R.id.imgClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    private void doSearch(String key) {
        List<Employee> listTemp= new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            listTemp.addAll(listCurrent
                    .stream()
                    .filter(x -> x.getItemCode().toLowerCase().contains(key) || x.getItemName().toLowerCase().contains(key))
                    .collect(Collectors.toList()));
        }
        list.clear();
        list.addAll(listTemp);

        if (key.equals("")){
            list.addAll(listCurrent);
        }

        recyclerViewEmployee.post(new Runnable() {
            @Override
            public void run() {
                employeeAdapter.notifyDataSetChanged();
            }
        });
    }

    private void setCheck() {
        if (listEmployeeSelect.size()!=1){
            return;
        }
        else {
            int position = listCurrent.indexOf(listEmployeeSelect.get(0));
            listCurrent.get(position).setCheck(true);
        }
    }

    private void clearCheck(List<Employee> listCurrent) {
        for (Employee employee : listCurrent){
            employee.setCheck(false);
        }
    }

    private void addControls() {
        Intent intent =getActivity().getIntent();
        boolean selectType = intent.getBooleanExtra(Constant.NAME_PUT_ACTION_SELECT_EMPLOYEE,true);
        type = selectType;

        departmentListCode = intent.getStringExtra(Constant.NANE_PUT_PARA_EMPLOYEE);

        recyclerViewEmployee=binding.recycleEmployee;
        listEmployeeSelect= new ArrayList<>();
        list= new ArrayList<>();
        listCurrent= new ArrayList<>();
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerViewEmployee.getContext(),
                linearLayoutManager.getOrientation());
        recyclerViewEmployee.addItemDecoration(dividerItemDecoration);
        employeeAdapter = new EmployeeAdapter(list,this::onCheckChange,type);
        recyclerViewEmployee.setLayoutManager(linearLayoutManager);
        recyclerViewEmployee.setAdapter(employeeAdapter);
        binding.radAll.setChecked(true);

        initTitle();
    }

    private Employee getItemSelected() {
        Intent intent= getActivity().getIntent();
        Employee employeeSelected = (Employee) intent.getSerializableExtra(Constant.NAME_PUT_EMPLOYEE);
        if (employeeSelected!=null){
            if (!employeeSelected.getItemCode().equals("")){
                return employeeSelected;
            }
        }
        return null;
    }
    private List<Employee> getListItemSelected(){
        Intent intent = getActivity().getIntent();
        List<Employee> employees  = (List<Employee>) intent.getSerializableExtra(Constant.NAME_PUT_LIST_EMPLOYEE);
        return employees;
    }

    private void initTitle() {
        Intent intent= getActivity().getIntent();
        String title = intent.getStringExtra(Constant.NAME_PUT_TITLE_EMPLOYEE);
        TextView txtTitle = binding.include22.findViewById(R.id.txtTitle);
        txtTitle.setText(title);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);
        mViewModel.setServerCheckCallback(new ServerCheckCallback() {
            @Override
            public void onServerLoadFail() {
                showOutTOKEN();
            }
        });
        mViewModel.loadDataEmployee(departmentListCode);
        mViewModel.getListMutableLiveEmployee().observe(getViewLifecycleOwner(), new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                list.clear();
                listCurrent.addAll(employees);

                if (type){
                    Employee employee = getItemSelected();
                    if (employee!=null){
                        listEmployeeSelect.clear();
                        listEmployeeSelect.add(employee);
                        for (Employee emp : listCurrent){
                            if (emp.getItemCode().equals(employee.getItemCode())){
                                emp.setCheck(true);
                                break;
                            }
                        }
                    }
                }
                else {
                    List<Employee> employeesSelectedList = getListItemSelected();
                    if (employeesSelectedList!=null){
                        for (Employee em : employeesSelectedList){
                            for (Employee employee : listCurrent){
                                if (em.getItemCode().equals(employee.getItemCode())){
                                    employee.setCheck(true);
                                }
                            }
                        }
                    }
                }

                list.addAll(listCurrent);
                recyclerViewEmployee.post(new Runnable() {
                    @Override
                    public void run() {
                        employeeAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

    }

    @Override
    public void onCheckChange(String itemCode, boolean isChecked) {
        int flag=0;
        for (int i =0;i<listCurrent.size();i++){
            if (listCurrent.get(i).getItemCode().equals(itemCode)){
                flag=i;
            }
        }
        if (type){
            if (isChecked){
                listEmployeeSelect.clear();
                listEmployeeSelect.add(listCurrent.get(flag));
            }
            else {
                listEmployeeSelect.clear();
            }
        }
        else {
            listCurrent.get(flag).setCheck(isChecked);
        }
        System.out.println(listCurrent.size()+"");
        System.out.println("=========="+listCurrent.get(flag).getItemCode()+"-"+listCurrent.get(flag).isCheck());
    }
}