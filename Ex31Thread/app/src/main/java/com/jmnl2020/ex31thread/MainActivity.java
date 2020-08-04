package com.jmnl2020.ex31thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    int num=0; //tv에 보여질 글씨

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv= findViewById(R.id.tv);

    }

    public void clickBtn(View view) {
        //오래걸리는 작업
        //별도의 Thread를 만들지 않았기에 Mainthread가 이 작업을 수행

        for (int i=0; i<20; i++){
            num++;
            tv.setText(num+"");

            //mainThread가 이 반복문 안에서만 작업하기 때문에 Textview에 넘버값을 보여주는 역할 수행 X
            //그래서 num값이 증가되는 모습을 볼 수 없음
            //따라서 반복문이 끝난 후에 마지막 num값인 20만 출력

            //그러므로 오래걸리는 작업은 Main Thread가 하지 않도록 해야함. 즉, 별도의 Thread 생성, 작업수행 명령

           //1초간 잠시 대기
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }//for end.

    }//clickBtn end.

    public void clickBtn2(View view) {
        //오래걸리는 작업 수행 (ex. Network, db 작업)
        //직원객체 생성 및 실행 = Thread
        MyThread t= new MyThread();
        t.start(); // 직원에게 작업수행을 지시 [= 이 클래스의 run()실행]

    }

    //오래걸리는 작업을 수행하는 스레드의 동작을 설계
    class MyThread extends Thread{
        //이 객체를 start()하면 자동으로 실행되는 메소드

// run method 오버라이드
        @Override
        public void run() {
            //이 안에서 thread가 해야할 작업 적기
            for (int i=0; i<20; i++){
                num++;

                //화면에 보여지는 작업 수행.
// ==============================================================================
//              tv.setText(num+""); //하위버전은 여기서 에러
                //화면을 변경하는 UI작업은 반드시 MainThread만 할 수 있도록 강제화 되어있음
                //즉, 별도의 Thread는 화면변경작업을 수행할 수 없음
                //MainThread에게 화면변경작업수행을 요청

                // 방법 1. Handler 이용
//                handler.sendEmptyMessage(0); //what은 식별번호. 어떤 번호든 상관없음


                // 방법 2. runOnUiThread() 메소드(Activity클래스 멤버) 이용
                //UI변경작업을 수행할 수 있도록 Main으로부터 위임장을 받을 Runnable 인터페이스를 구현한 객체 생성

//                Runnable runnable= new Runnable() {
//                    @Override
//                    public void run() {
//                        tv.setText(num+"");
//                    }
//                };

//                runOnUiThread(runnable); //위임장을 주는 기능 메소드.
                //파라미터로 전달한 runnable 객체에게 UI변경이 가능하도록 위임장 부여

                //줄여서 사용가능..
                runOnUiThread(new Runnable() {
                                  @Override
                                  public void run() {
                                      tv.setText(num+"");
                                  };


                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


//            super.run(); 있어도 없어도 상관없음 걍 지우자
        }// run method end.

    }// MyThread end.

    //별도의 Thread가 Main Thread에게 UI변경작업을 요청할 때 활용될 객체
    Handler handler= new Handler(){
        //익명클래스
        //handler.sendEmptyMessage() 를 실행하여 MainThread가 이 메세지를 처리하면 자동으로 실행되는 메소드
        //send message를 하면 이게 실행됨

        @Override
        public void handleMessage(@NonNull Message msg) { //Empty가 아니면 msg에서 값을 받아옴
            //이 곳에서 Ui변경 가능
            tv.setText(num+"");

//            super.handleMessage(msg); 있어도 없어도 상관없음
        }
    };



}//MainActivity Class end.
