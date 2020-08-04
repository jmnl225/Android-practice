package com.jmnl2020.hellobyjava;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //가급적 Activity가 보여준s View들의 참조변수는 멤버변수로 만들 것
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);

        //Java언어만으로 이 액티비티가 보여줄 view들을 설정

        //액티비티에 놓여질 수 있는 것은 view 클래스를 상속한 클래스들만 가능

        //글씨를 보여주는 textview 객체 생성 및 설정 **지역변수가 아닌 멤버변수로 참조변수 만들기
        tv= new TextView(this);
        tv.setText("Hello World!");

        //만들어진 TextView를 액티비티에서 보여주기!
//        this.setContentView(tv);

        //버튼객체 생성 및 설정
        Button btn= new Button(this);
        btn.setText("Hello");

        //만들어진 Button을 액티비티에서 보여주기!
        //this.setContentView(btn);
//        setContentView(btn);

        //기본적으로 activity는 한 번에 하나의 view만 보여질 수 있음!

        //그래서 여러개의 view객체를 담을 수 있는 viewGruop 객체 생성
        //ViewGroup 중에서 가장 기본적이고 많이 사용되는 LinearLayout 클래스를 객체로 생성하자!

        LinearLayout layout= new LinearLayout(this);

        // 이 레이아웃객체에 위에서 만든 Button, TextView를 추가

        layout.addView(tv);
        layout.addView(btn);


        //이 레이아웃객체를 activity가 보여주도록
        this.setContentView(layout);


        //버튼 클릭시 TextView의 글씨 변경
        View.OnClickListener listener= new View.OnClickListener(){

            //이 리스너가 바라보고 있는 버튼이 클릭되면 자동으로 실행되는 콜백 메소드
            //콜백메소드: 내가 호출하지 않아도 자동 실행됨

            @Override
            public void onClick(View v) {
                //TextView가 보여주는 글씨 변경
                tv.setText("Android!"); //**지역변수가 아닌 참조변수로 만들기
            }
        };
        btn.setOnClickListener(listener);








    }
}
