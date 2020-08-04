package com.jmnl2020.ex27activity3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv= findViewById(R.id.tv);

    }

    public void clickBtn(View view) {
        //SecondActivity 실행! 하되 결과를 받기위해~
        Intent intent = new Intent(this, SecondActivity.class);

        //intent가 결과를 위해 다른 Activity에 갔다가 와야함! 다녀오는것을 명시
        startActivityForResult(intent, 10); //10은 식별번호, 아무번호나 적어도 됨.

        //startActivityForResult();로 실행된 Activity가 종료되어 현재의 화면이 실행될 때
        //자동으로 실행되는 콜백 메소드
        //즉, SecondActivity에 갔던 Itent가 돌앙오면 자동으로 실행되는 메소드

        onActivityResult();

    }

    private void onActivityResult(int requestCode, int resultCode) {
        super.onActivityResult(requestCode, resultCode, intent data);
        //내가 보낸 택배기사가 맞는지 request Cdoeㄹ르 통해 확인
        switch (requestCode){
            case 10:
                //SecondActivity의 OK결과냐?
                if(resultCode==RESULT_OK){
                    //돌아온 Intent객체에게 Extra데이터를 얻어오기
                    //(3번째 파라미터: data)에게 Extra 데이터를 받기
                    String name= data.getStringExtra("Name");
 //                   int age= data.getIntExtra();

                            tv.setText(name+"\n"+age);
                }
                break;


        }
    }
}
