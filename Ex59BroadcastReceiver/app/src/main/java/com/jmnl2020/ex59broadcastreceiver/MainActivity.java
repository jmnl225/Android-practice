package com.jmnl2020.ex59broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MyReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickBtn(View view) {
        //명시적 intent로 리시버 실행하기: 같은 앱 안에있는 리시버만 실행할 수 있음
        //**manifests 등록이 되어있어야함!!!
        Intent intent= new Intent(this, MyReceiver.class);
        sendBroadcast(intent);

    }

    //Oreo 버전부터 브로드캐스트나 서비스 컴포넌트사용에 제한 (백그라운드에서 너무 리소스를 많이 사용 - 배터리소모 -kill method)
    // - 운영체제가 방송하는 시스템 브로드캐스트는 정상적 동작
    // - 하지만 개발자가 임의로 보내는 방송은 ***동적 리시버 등록***을 해야함
    // 즉, Manifest.xml에 리시버를 등록하지 않고 JAVA코드로 리시버를 등록!! = [동적 리시버 등록]
    // 즉, 앱이 켜져있을때만 묵시적 방송을 듣도록 제약.


    public void clickBtn2(View view) {
        //묵시적 방송: 디바이스에 설치된 모든 앱에게 방송
        Intent intent= new Intent();
        intent.setAction("aaa");//방송의 액션값(식별값) 지정
        sendBroadcast(intent);

    }

    //액티비티가 화면에 보여질때 자동으로 발동하는 콜백메소드
    //onCreate() 실행 후 onStart()실행 후 실행되는 메소드[라이프사이클 메소드]


    @Override
    protected void onResume() {
        super.onResume();

        //동적으로 리시버 등록 [aaa액션을 듣는 리시버]
        myReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter("aaa");
        registerReceiver(myReceiver, filter);

        //켰을때 발동

    }

    @Override
    protected void onPause() {
        super.onPause();

        //꺼지기 직전, 멈췄을 때
        unregisterReceiver(myReceiver);
        //내가 만든 방송은 앱이 꺼졌을 때 받지 않도록.

    }
}
