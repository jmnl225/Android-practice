package com.jmnl2020.ex24listviewcustom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //대량의 데이터
    ArrayList<Member> members= new ArrayList<>();

    MyAdapter adapter;

    //MainActivity
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //대량의 Data를 추가작업
        members.add( new Member("아이스크림콘", "간식", R.drawable.icon01) );
        members.add( new Member("샌드위치", "식사", R.drawable.icon02) );
        members.add( new Member("하드", "디저트", R.drawable.icon03) );
        members.add( new Member("피자", "음식", R.drawable.icon04) );
        members.add( new Member("도넛", "냠냠", R.drawable.icon05) );
        members.add( new Member("콘", "아이스크림", R.drawable.icon06) );
        members.add( new Member("아이스크림콘", "간식", R.drawable.icon01) );
        members.add( new Member("샌드위치", "식사", R.drawable.icon02) );
        members.add( new Member("하드", "디저트", R.drawable.icon03) );
        members.add( new Member("피자", "음식", R.drawable.icon04) );
        members.add( new Member("도넛", "냠냠", R.drawable.icon05) );
        members.add( new Member("콘", "아이스크림", R.drawable.icon06) );

        //대량의 데이터를 적합한 뷰들로 만들어주는 객체

        LayoutInflater inflater= getLayoutInflater();

        adapter= new MyAdapter(members, inflater); // 대량의 데이터들, layoutInflater 를 전달하자

        //-----------------------------------------------------------------------------------
        listView= findViewById(R.id.listView);
        //리스트뷰에 아답터 설정
        listView.setAdapter(adapter);


        //리스트 뷰에 아이템 클릭에 반응하는 리스너~
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, members.get(position).name, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
