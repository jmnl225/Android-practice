package com.jmnl2020.ex25activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickBtn(View view) {
        //Second Activity : 실행시켜주는 택배기사객체(intent) 생성하여 요청
        Intent intent= new Intent(this, SecondActivity.class);
        startActivity(intent);

        //만약 현재 mainActivity를 종료시키고 싶다면 (backstack에 넣지 않고 싶다면)
//        finish();


        //제목줄 관리 객체 얻어오기
        ActionBar actionBar=getSupportActionBar(); //최근것 = SupportActionBar
        actionBar.setTitle("Second Activity"); // 제목변경

    }

    public void clickBtn2(View view) {

        Intent intent= new Intent(this, ThirdActivity.class);
        startActivity(intent);

    }
}
