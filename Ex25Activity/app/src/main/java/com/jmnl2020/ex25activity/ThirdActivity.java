package com.jmnl2020.ex25activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Third Activity");

        //제목줄 왼쪽에 '뒤로가기 화살표 아이콘' 만들기
        //이 아이콘을 눌렀을 때 돌아갈 액티비티 지정 가능
        // manifest에서 지정~!
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
