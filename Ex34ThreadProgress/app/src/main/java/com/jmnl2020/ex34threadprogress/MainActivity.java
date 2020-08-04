package com.jmnl2020.ex34threadprogress;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    ProgressDialog dialog;

    int gauge = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickBtn(View view) {

        //wheel type progress dialog
        ProgressDialog dialog= new ProgressDialog(this);
        dialog.setTitle("Wheel Dialog");
        dialog.setMessage("Downloading......");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        //다운로드가 끝나기 전까지 창 안 끄기/ 끄려면 true
        dialog.setCanceledOnTouchOutside(false);

        dialog.show();

        //3초 뒤에 다이얼로그 종료
        //3000millis 후에 Handler객체의 handleMessage()발동
        handler.sendEmptyMessageDelayed(0,3000);

    }


    //핸들러 객체 생성

    Handler handler= new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            dialog.dismiss();
        }
    };


    public void clickBtn2(View view) {

        //bar type progress dialog
        final ProgressDialog dialog= new ProgressDialog(this);
        dialog.setTitle("Bar Dialog");
        dialog.setMessage("다운로드 중...");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        dialog.setMax(100); // 0~10 까지만, 디폴트 = 100;

        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        dialog.setProgress( gauge );
        //게이지증가: 별도 thread
        new Thread(){
            @Override
            public void run() {
                while (gauge<100){
                    gauge++;
                    dialog.setProgress(gauge);
                    try {
                        Thread.sleep(50); //0.05초
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }// while end.

                dialog.dismiss();

            }
        }.start();

    }


}
