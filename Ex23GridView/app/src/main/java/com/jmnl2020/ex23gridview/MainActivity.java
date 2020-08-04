package com.jmnl2020.ex23gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    ArrayAdapter adapter;

    ArrayList<String> datas= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //대량의 데이터들
        datas.add("딸기");
        datas.add("수박");
        datas.add("체리");
        datas.add("감");
        datas.add("메론");
        datas.add("귤");
        datas.add("a");
        datas.add("블루베리");
        datas.add("딸기");
        datas.add("블랙베리");

        gridView= findViewById(R.id.gridview);
        adapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1, datas);
        //필요한 설계도면을 내가 만드는 것이 아니라 안드로이드 기본설정에서 가져와서 사용 가능

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, position+"", Toast.LENGTH_SHORT).show();
            }
        });

    }



}
