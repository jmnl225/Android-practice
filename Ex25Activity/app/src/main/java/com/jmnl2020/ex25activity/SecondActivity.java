package com.jmnl2020.ex25activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //이 액티비티가 보여줄 뷰들을 설정하는 메소드 호출
        setContentView(R.layout.activity_second);

    }
}
