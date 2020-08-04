package com.jmnl2020.ex42viewpaser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> items = new ArrayList<>();
    MyAdapter adapter;

    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager=findViewById(R.id.pager);

        //대량의 데이터 추가하기
        items.add(new Integer(R.drawable.gametitle_01));
        items.add(new Integer(R.drawable.gametitle_02));
        items.add(new Integer(R.drawable.gametitle_03));
        items.add(new Integer(R.drawable.gametitle_04));
        items.add(new Integer(R.drawable.gametitle_05));
        items.add(new Integer(R.drawable.gametitle_06));
        items.add(new Integer(R.drawable.gametitle_07));
        items.add(new Integer(R.drawable.gametitle_08));
        items.add(new Integer(R.drawable.gametitle_09));
        items.add(new Integer(R.drawable.gametitle_10));

        //아답터객체 생성
         adapter = new MyAdapter(items, getLayoutInflater());


         //Viewpager에 아답터 설정
        pager.setAdapter(adapter);


    }


    public void clickPrev(View view) {
        //현재 뷰페이저의 page번호 얻어오기
        int index= pager.getCurrentItem();

        //특정 페이지로 이동
        pager.setCurrentItem(index-1);
    }

    public void clickNext(View view) {
        int index= pager.getCurrentItem();
        pager.setCurrentItem(index+1,false);
        //smooth Scroll 여부 지정 가능
    }
}
