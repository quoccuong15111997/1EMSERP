package com.firstems.erp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firstems.erp.adapter.DialogSingelChoiseAdapter;
import com.firstems.erp.callback.SingelChoiseDialogCallback;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        List<String> stringList = new ArrayList<>();
        stringList.add("Bệnh");
        stringList.add("Đào tạo");
        stringList.add("Công tác");
        stringList.add("Đi làm");
        stringList.add("Hiếu hỉ");
        stringList.add("Học quân sự");
        stringList.add("Không phép");
        stringList.add("Nghỉ tết");
        stringList.add("Nghỉ bù");
        stringList.add("Nghỉ ca");
        stringList.add("Nghỉ việc riêng có lương");
        stringList.add("Phép năm");


       /* *//*DialogSingelChoiseAdapter adapter = new DialogSingelChoiseAdapter(stringList, new SingelChoiseDialogCallback() {
            @Override
            public void itemSelected(String o) {

            }
        });*//*
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);*/
    }
}