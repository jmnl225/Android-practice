package com.jmnl2020.ex26activity2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et=findViewById(R.id.et);
    }

    public void clickBtn(View view) {
        //SecondActivity에 전달할 data를 Edit Text로부터 얻어옴
        String s= et.getText().toString();

        //SecondActivity를 실행할 Intent객체(택배기사) 생성
        Intent intent= new Intent(this, SecondActivity.class);

        //intent에게 전달할 데이터를 넘김.
        intent.putExtra("Name", s); //식별자(Name)와 값(value)을 대입.
        intent.putExtra("Age", 20);

        //택배기사를 통해 새로운  Activity시작.
        startActivity(intent);


    }
}
