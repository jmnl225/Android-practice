package com.jmnl2020.ex20listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter adapter; //참조변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView= findViewById( R.id.lv );
        //리스트뷰에서 보여줄 view들을 만들어주는 객체 생성
        adapter= ArrayAdapter.createFromResource(this,  R.array.datas, R.layout.list_item);

        //리스트뷰에게 아탑터 setapdapter
        listView.setAdapter(adapter);

        //리스뷰의 항목을 클릭하는 것을 듣는 리ㅣ스터객체 생성
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //res창고 관리자 객체 가져와야함!
                Resources res= getResources();


                // res/values/arrays.xml 문서안에 있는 datas 배열 가져오기
                String[] arr= res.getStringArray(R.array.datas) ;


                Toast.makeText(MainActivity.this, arr[position], Toast.LENGTH_SHORT).show();

            }
        });

        //아이템 롱~클릭 리스너 추가
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                //보통 여기에서 PopupMenu를 만듦

                Toast.makeText(MainActivity.this, "long click", Toast.LENGTH_SHORT).show();

                return true; //event is consumed
            }
        });

    }
}
