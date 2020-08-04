package com.jmnl2020.ex05radiobutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    RadioGroup rg;
    RadioButton rb_kor, rb_jap, rb_chi;

    RatingBar rb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rg= findViewById(R.id.rg);
        rb_kor=findViewById(R.id.rb_kor);
        rb_chi=findViewById(R.id.rb_ch);
        rb_jap=findViewById(R.id.rb_jap);
        tv= findViewById(R.id.tv);

//        rb_chi.setChecked(true); //java에서도 체크 가능


        //라디오그룹안의 라디오버튼의 체크상태가 변경되는 것을 듣는 리스너객체 생성 및 설정
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //두번째 파라미터인 체크된 RB의 id를 이용하여
                //체크된 라디오버튼객체를 참조
                RadioButton rb= findViewById(checkedId);

                tv.setText(rb.getText().toString());
            }
        });

        //레이팅바 변경 리스너 생성 및 추가

        rb= findViewById(R.id.rating);
        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                tv.setText(rating+" 점");
            }
        });

    }

    public void clickBtn(View v){
        //RadioGroup을 통해 체크되어있는 RadioButton 찾아오기
        int id = rg.getCheckedRadioButtonId();
        RadioButton rb = findViewById( id );

        //체크된 라디오버튼의 글씨를 얻어와서 텍스트뷰에 보여주기

        tv.setText(rb.getText().toString());

    }

}
