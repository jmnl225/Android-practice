package com.jmnl2020.ex26activity2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tv= findViewById(R.id.tv);

        //이 액티비티를 실행한 택배기사intent객체 호출(참조)
        Intent intent= getIntent(); //나를 실행한 Intent 참조.

        //Intent 객체에게 추가데이터 받기
         String name= intent.getStringExtra("Name");

         int age= intent.getIntExtra("Age",0); //두번째 파라미터 : 혹시 값이 없을 때 넣을 기본값

        //전달받은 값을 TextView에 보여주자!
        tv.setText(name+"\n"+age);

    }
}
