package com.jmnl2020.ex10scrollview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickBtn(View v){
        //스크롤뷰 참조하기
        ScrollView sv= findViewById(R.id.sv);

        //스크롤뷰의 스크롤위치를 가장 아래쪽으로 이동
        sv.fullScroll(ScrollView.FOCUS_DOWN);
    }
}
