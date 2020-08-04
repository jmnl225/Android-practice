package com.jmnl2020.ex29activity4_2;

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
        //Ex28번 예제의 Second Activity를 묵시적 인텐트로 실행
        Intent intent= new Intent();
        intent.setAction("Loki"); // Device 에 설치된 모든 앱의 액티비티 확인
        startActivity(intent);
    }
}
