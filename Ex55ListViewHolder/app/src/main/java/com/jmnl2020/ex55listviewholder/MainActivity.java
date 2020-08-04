package com.jmnl2020.ex55listviewholder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //대량의 데이터
    ArrayList<String> items = new ArrayList<>();

    //대량의 데이터를 뷰객체로 만들어주는 아답터객체 참조변수
    MyAdapter adapter;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //대량의 데이터 추가
        items.add(new String("오늘은 목요일"));
        items.add(new String("내일은 금요일"));
        items.add(new String("모레는 토요일"));

        //아답터객체 생성
        adapter= new MyAdapter(items, this);

        //listview 연결
        listView = findViewById(R.id.listview);
        listView.setAdapter(adapter);

    }
}
