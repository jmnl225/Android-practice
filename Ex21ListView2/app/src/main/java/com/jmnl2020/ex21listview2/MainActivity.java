package com.jmnl2020.ex21listview2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter adapter;
    //String만을 위한 adapter.

    //대량의 데이터들
    ArrayList<String> datas = new ArrayList<>();

    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et= findViewById(R.id.et);

        //리스트뷰에서 보여줄 대량의 데이터 추가
//        datas.add( new String("복숭아"));
//        datas.add( new String("딸기"));
//        datas.add( "수박" );
//        datas.add( "메론" );

        listView= findViewById(R.id.listview);
        // listView에 보여질 뷰들을 만들어주는 객체
        adapter= new ArrayAdapter(this, R.layout.list_item, datas);

        //리스트뷰에 아답터 설정
        listView.setAdapter(adapter);


        //리스트뷰의 아이템이 비어있을 때 Textview 보여주기
        TextView tv= findViewById(R.id.tv_empty);
        listView.setEmptyView(tv);



        //리스트뷰에 아이템 롱클릭 리스너 추가
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                //데이터 삭제
                datas.remove(position);
                adapter.notifyDataSetChanged();

                return true;
            }
        });

    }


    public void clkickBtn(View view) {

        //EditText 적힌 글씨를 얻어오기
        String s= et.getText().toString();

        //얻어온 글씨를 대량의 데이터에 추가
        datas.add(s);

        //Adapter에게 새로운 데이터가 추가됐으니 리스트 갱신하라고 요청
        adapter.notifyDataSetChanged();
        //리스트뷰의 스크롤 위치를 지정
        listView.setSelection(datas.size()-1); //처음 시작이 0이라서 -1

        //EditText의 글씨 비우기
        et.setText("");

    }}
