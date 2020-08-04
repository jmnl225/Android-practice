package com.jmnl2020.ex32thread2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    MyThread t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //액티비티가 메모리에서 삭제될때 자동으로 실행되는 메소드
    @Override
    protected void onDestroy() {
        //별도 스레드 종료
//        t.stop(); //Thead의 stop은 에러...
        //언제 끝나지? run method가 종료될 때. 그럼 그 때 while문을 멈추자!
//        t.isRun= false; //while문의 조건문을 false로 대입.

        //객체지향? 니건 니가!
        //객체의 멤버변수를 직접 수정하는 것은 권장하지않음

        t.stopThread();
        //run 밖에 새로운 객체를 만들어줌 - > isRun을 false로 변경해주는 칭구

        super.onDestroy();
    }

    public void clickBtn(View view) {
        //버튼을 눌렀을 경우 무한 반복하며 현재시간을 5초마다 보여주기
        //****절대로 이 안에서 반복문 XXXXXXXXXXXXX
        //이 작업을 수행하는 별도의 Thread객체 생성
        t = new MyThread();
        t.start(); // 자동으로 run메소드 envoke

    }

    //현재시간을 무한히 출력 기능을 수행하는 별도 Thread 클래스 설계
    class  MyThread extends Thread{

        boolean isRun= true;

        @Override
        public void run() {
            while(isRun){
                Date now= new Date(); //객체가 생성 되는 순간의 시간을 갖고있음
                final String s = now.toString(); // 현재시간을 문자열로 리턴

                //화면 변경작업은 별도 Thread가 할 수 없음.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //이 안에서는 UI작업이 가능
                        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();

                    }
                });
               //while문이 너무 빨리 진행되므로 잠시 대기 - sleep은 try-catch문
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }//while

        }//run end.

        //스레드를 종료시키는 기능 메소드
        void stopThread(){
            isRun= false;
        }

    }

    ////////////////////////////////////////////////////////////////////

}//main activity end.
