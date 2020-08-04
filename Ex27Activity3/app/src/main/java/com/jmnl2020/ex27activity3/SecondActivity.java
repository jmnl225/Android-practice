package com.jmnl2020.ex27activity3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import javax.xml.transform.Result;

public class SecondActivity extends AppCompatActivity {

    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        et= findViewById(R.id.et);



    }

    public void clickBtn(View view) {
        //MainActivity에 돌려줄  Edit 텍스트에서 얻어오기
        String s= et.getText().toString();

        //메인 Activit 를 이용해 데이터 되돌려주기
        getIntent().putExtra("Name",5);

        Intent intent= getIntent();

        //이 인텐트 객체의 결과가
        this.setResult(RESULT_OK, intent);

        //이 액티비티 종료
        finish();
    }
}
