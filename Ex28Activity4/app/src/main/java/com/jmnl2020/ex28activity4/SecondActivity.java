package com.jmnl2020.ex28activity4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //액션바 이름 찍기
        getSupportActionBar().setTitle("Second Activity");
    }
}
