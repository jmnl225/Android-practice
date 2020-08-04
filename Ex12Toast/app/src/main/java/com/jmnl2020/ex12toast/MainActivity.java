package com.jmnl2020.ex12toast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    //onClick 속성이 부여된 View가 click되면 자동으로 실행되는 콜백메소드
    public void clickBtn(View v){
        //1. 토스트 객체 만들기 [ new 키워드 대신에 Toast클래스의 makeText() 메소드를 통해 토스트 객체 생성 ]
        //Context: 안드로이드 운영체제의 기능을 사용할 수 있도록 만든 클래스 [운영체제 대리인]
        //Activity가 Context를 상속받았기에 Context가 필요할 때 이 Activity를 전달해도 됨.

        Toast t = Toast.makeText(this,"clicked", Toast.LENGTH_SHORT) ;
        //만들어진 토스트객체 화면에 보여라!
        //t.show();

        //2.res/values/strings.xml 안에 문자열 작성하고 토스트에서 문자열 읽어와 보여주기
        t= Toast.makeText(this, R.string.msg, Toast.LENGTH_SHORT);

        //토스트가 보여질 위치 지정
        t.setGravity(Gravity.CENTER, 0,300);
        //t.show();


        //3. Toast에 문자열이 아닌 원하는 모양의 View 보여주기기
        // 우선 빈 문자열로 된 Toast객체 생성
        t= Toast.makeText(this, " ", Toast.LENGTH_LONG);

        //ImageView 객체 생성
        ImageView iv= new ImageView(this);

        //토스트가 보여질 view 설정 [기본은 Text view]
        iv.setImageResource(android.R.drawable.ic_lock_silent_mode);

        // TextView도 같이 보여주기!
        TextView tv= new TextView(this);
        tv.setText("음소거");


//        t.setView( iv );
//        t.setView(tv);
//          토스트에는 하나의 뷰만 설정 가능.
        // ViewGroup을 만들어서 그 안에 view들을 넣고 그룹 하나만 설정

        LinearLayout layout= new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        //알 수 없는 int를 넣으라고 할 때 클래스명을 찍고 찾으면 나옴!!

        layout.addView(iv);
        layout.addView(tv);

        //토스트에 set.view
        t.setView(layout);

        t.setGravity(Gravity.CENTER,0,-800);

//        t.show();


        //4. xml로 객체의 모양을 설계하고 이를 객체로만들어 사용
        //layout 폴더 안에 xml 문서를 만들고 뷰들을 배치

        //toast에 view 설정하기

        t= Toast.makeText(this, " ", Toast.LENGTH_LONG);


        //xml에 설계된 뷰들을 JAVA의 view객체로 만들자!
        //이걸 해주는 객체가 있음. 걔한테 요청 ㄱ
        //객체가 xml을 view객체로 만들어줫 inflater

        LayoutInflater inflater=this.getLayoutInflater();
        View view= inflater.inflate(R.layout.toast, null);

        t.setView(view);

        t.setGravity(Gravity.CENTER, 0,0);
        t.show();

    }

}
