package com.jmnl2020.ex03imageview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //뷰들을 참조하는 멤버 참조변수
    ImageView iv;
    Button btn01, btn02, btn03, btn04;

    //이미지 변경 번호용 멤버변수
    int num=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //res 폴더 안에 layout 폴더 안에 있는 activity_main.xml에서 만든
        //뷰들을 id를 이용해서 찾아와 참조변수에 대입

        iv= findViewById(R.id.iv);
        btn01= findViewById(R.id.btn01);
        btn02= findViewById(R.id.btn02);
        btn03= findViewById(R.id.btn03);
        btn04= findViewById(R.id.btn04);

        btn01.setOnClickListener(listener);
        btn02.setOnClickListener(listener);
        btn03.setOnClickListener(listener);
        btn04.setOnClickListener(listener);

        //이미지뷰를 클릭할 때마다 이미지를 차례대로 변경
        // 1. xml에서 이미지 뷰의 clickable속성을 true 로 지정 !!!!
        // 2. 이미지뷰 클릭에 반응하는 클릭 리스너 객체 생성 : 익명클래스로
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //3. 이미지를 차례로 변경
                num++;
                if(num>12) num=0; //준비된 이미지가 아닌 이미지를 보여주지 않기 위해 다시 0으로
                iv.setImageResource(R.drawable.flag_australia + num);

            }
        });

    }//OnCreate method

    //4개 버튼 클릭에 반응하는 리스너객체 생성 및 설정
    //리스너 객체 하나로 모든 버튼 제어 하기

    View.OnClickListener listener= new View.OnClickListener() {
        //이 리스너 객체가 바라보고 있는 버튼이 클릭되면 발동
        @Override
        public void onClick(View v) {

            //버튼에따라 이미지 변경
            //onClick 메소드의 파라미터로 전달된 view 객체(v)가 클릭한 뷰!
            int id = v.getId();

            switch ( id ){
                case R.id.btn01:
                    iv.setImageResource(R.drawable.flag_australia);
                    break;

                case R.id.btn02:
                    iv.setImageResource(R.drawable.flag_belgium);
                    break;

                case R.id.btn03:
                    iv.setImageResource(R.drawable.flag_canada);
                    break;

                case R.id.btn04:
                    iv.setImageResource(R.drawable.flag_china);
                    break;

            }


        }
    };




}//MainActivity class..
