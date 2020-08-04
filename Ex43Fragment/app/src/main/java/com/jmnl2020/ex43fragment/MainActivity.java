package com.jmnl2020.ex43fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    MyFragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv= findViewById(R.id.tv);
//        myFragment= findViewById(R.id.frag);
        //fragment != view
        //Fragment를 관리하는 매니저 객체 소환

        FragmentManager fragmentManager;
        fragmentManager = getSupportFragmentManager();

        myFragment= (MyFragment) fragmentManager.findFragmentById(R.id.frag);
        //Fragment가 많이 있을 경우 어떤 fragment인지 알 수 없기에 앞에서 형변환.

    }

    public void clickBtn(View view) {
        //MyFragment 안에 있는 TextView의 글씨를 변경
 //       myFragment.tv.setText("NICE!!");
        myFragment.changeText("Nice~!"); // 객체지향적. 전달하기

    }

    //MyFragment 안에 있는 버튼의 onClick속성의 콜백메소드
    public void clickBtn2(View v){
        myFragment.changeText("뿅!");
    }

}
