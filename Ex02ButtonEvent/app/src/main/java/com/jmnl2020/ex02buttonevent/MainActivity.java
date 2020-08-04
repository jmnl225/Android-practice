package com.jmnl2020.ex02buttonevent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //뷰 객체들의 참조변수는 가급적(반드시) 멤버변수로 만들것!!!!!!!!!!!!!!!!!!!!!!!!!!
    TextView tv;
    Button btn;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // xml문서를 읽어서 view객체를 생성함

        //xml에서 만든 TextView 객체를 tv 참조변수로 참조함. - > 제어하기 위해서!
        tv= this.findViewById(R.id.tv);  //R장부 안에, id 칸 안에 있는 tv라는 id 이름.
//        btn= this.findViewById(R.id.btn); //this. 은 생략 가능
        btn= findViewById(R.id.btn);
        et= findViewById(R.id.et);

        //버튼을 클릭 했을 때 TextView의 글씨 변경
        //버튼이 클릭 되는 것을 듣는 리스너객체 생성
        View.OnClickListener listener= new View.OnClickListener(){//익명클래스
            //이 리스너 객체가 바라보고 있는 버튼이 클릭되면 자동으로 실행되는 콜백 메소드
            @Override
            public void onClick(View v){
                //이 메소드 안에 코드를 작성하면, 클릭되었을 때 이 코드들이 실행됨
//                tv.setText("Good Afternoon :)");


                //EditText에 적힌 글씨를 얻어와서
                Editable editable = et.getText();
                String s= editable.toString();

                // 얻어온 글씨를 TextView에 설정하기
                tv.setText(s);

                }
             };

        //위에서 만든 리스너객체를 버튼에게 설정
        btn.setOnClickListener(listener);

    }
}
