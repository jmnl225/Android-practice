package com.jmnl2020.ex75webservice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    ImageView iv;

    String textUrl= "http://jmnl2020.dothome.co.kr/test.text";
    String imgUrl = "http://jmnl2020.dothome.co.kr/newyork.jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv= findViewById(R.id.tv);
        iv= findViewById(R.id.iv);

    }

    public void clickBtn(View view) {
 //        서버에서 제공하는 텍스트파일을 읽어오기


        new Thread(){
            @Override
            public void run() {
                //stream을 만들어주는 해임달객체(URL) 생성
                try {
                    URL url= new URL(textUrl);

                    InputStream is = url.openStream();
                    InputStreamReader isr= new InputStreamReader(is); //byte를 문자 스트림으로 변환
                    BufferedReader reader = new BufferedReader(isr); // 문자스트림을 string으로 하나씩 모아서 만들어줌.
                    final StringBuffer buffer = new StringBuffer(); //string 을 모아서 n줄로 만들어줌

                    while(true){
                        String line = reader.readLine();
                        if(line == null) break;

                        buffer.append(line+"\n");
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv.setText(buffer.toString());
                        }
                    });

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void clickBtn2(View view) {

        //이미지를 네트워크에서 손쉽게 로딩하여 보여주는 라이브러리 사용
        Glide.with(this).load(imgUrl).into(iv);

//
//        new Thread(){
//            @Override
//            public void run() {
//                try {
//                    URL url = new URL(imgUrl);
//                    InputStream is = url.openStream();
//
//                    final Bitmap bm = BitmapFactory.decodeStream(is);
//
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            iv.setImageBitmap(bm);
//                        }
//                    });
//
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();

    }
}













