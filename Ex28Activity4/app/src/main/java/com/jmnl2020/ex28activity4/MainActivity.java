package com.jmnl2020.ex28activity4;

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

        //묵시적 인텐트 실행 [Activity class명 없이 호출]
        Intent intent= new Intent();
        //그 Activity의 고유한 식별자 설정 (ex. 인사담당자)
//        intent.setAction("Loki"); // setAction -> manifest에서 intent filter로 action을 추가해서 그 식별자로 구별
        intent.setAction("aaa"); // 여러개가 가능
        startActivity(intent);

        //***********************
        // 묵시적 인텐트는 핸드폰 안의 모든 앱에 질문함

    }
}
