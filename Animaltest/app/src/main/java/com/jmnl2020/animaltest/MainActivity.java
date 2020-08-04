package com.jmnl2020.animaltest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //1. 대량의 데이터 만들기

    ArrayList<Post> posts= new ArrayList<>();

    //내가 만든 어댑터 가리키는 전역변수
    MyAdapter adapter;

    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        posts.add( new Post("이름"));
        posts.add( new Post("가나"));
        posts.add( new Post("다라"));
        posts.add( new Post("마바"));
        posts.add( new Post("사아"));
        posts.add( new Post("자차"));

        //대량의 데이터를 적합한 뷰들로 만들어주는 객체, Adapter

        LayoutInflater inflater= getLayoutInflater();

        adapter= new MyAdapter(posts, inflater);

        //리스트뷰에 어댑터 설정
        listView= findViewById(R.id.listview);
        listView.setAdapter(adapter);


    }

    //=================액션바 만들기=====================
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.actionbar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //액션 메뉴를 선택할때 실행하는 메소드


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //선택한 id를 구별하여 실행되는 기능 다르게 하기
        int id= item.getItemId();
        switch (id){
            case R.id.menu_search:
                Toast.makeText(MainActivity.this, "Search", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_add: //다이얼로그 소환하여 사용자에게 입력받을 정보 보여주기
                break;
       }

        return super.onOptionsItemSelected(item);
    }
}
