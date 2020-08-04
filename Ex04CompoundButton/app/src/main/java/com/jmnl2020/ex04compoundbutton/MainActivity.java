package com.jmnl2020.ex04compoundbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    //xml에서 만들 뷰들을 참조하는 참조변수
    CheckBox cb01, cb02, cb03;
    TextView tv;

    ToggleButton toggleButton;
    Switch sw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //id를 이용해서 뷰들을 찾아와 참조변수에 대입
        cb01= findViewById(R.id.cb01);
        cb02= findViewById(R.id.cb02);
        cb03= findViewById(R.id.cb03);
        tv= findViewById(R.id.tv);

        toggleButton.findViewById(R.id.toggle);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tv.setText( isChecked+"" );
            }
        });

        //Switch도 체크상태 변경 리스너 객체 생성 및 설정
        sw= findViewById(R.id.sw);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tv.setText(isChecked+"");
            }
        });

        //체크ㅏㅂㄱ스의 체크상태가 변경
        CompoundButton.OnCheckedChangeListener changeListener= new CompoundButton.OnCheckedChangeListener() {
            //체크상태가 변경될때마다 실행되는 메소드
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                String s="";

                if(cb01.isChecked()) s+= cb01.getText().toString();
                if(cb02.isChecked()) s+= cb02.getText().toString();
                if(cb03.isChecked()) s+= cb03.getText().toString();

            }
        };

        //위에서 만든 리스너를 각 체크박스에 붙이기.

        cb01.setOnCheckedChangeListener(changeListener);
        cb02.setOnCheckedChangeListener(changeListener);
        cb03.setOnCheckedChangeListener(changeListener);
    }


    //onclick 속성이 부여된 뷰가 클릭되면 발동하는 콜백 메소드
    public void clickBtn(View v){

        String s=null; //기본값으로 쓰레기가 들어가있음

        //선택된 체크박스의 글씨를 읽어와서(얻어와서_) TextView에 보여주기
        if(cb01.isChecked()){
           s= cb01.getText().toString();
        }

        if(cb02.isChecked()){
            s+= cb02.getText().toString();
        }

        if(cb03.isChecked()){
            s+= cb03.getText().toString();
        }

        tv.setText(s);
    }
}
