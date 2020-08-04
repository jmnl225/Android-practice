package com.jmnl2020.ex33threadimage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv= findViewById(R.id.iv);

    }

    public void clickBtn(View view) {
        //네트워크상에있는 이미지를 불러와서 ImageView에 보여주기!
        //안드로이드에서는 Main Thread는 네트워크 작업 불가
        //네트워크 작업을 하는 별도의 Thread


        //+---===========================================================+
        //네트워크 작업을 하려면 반드시 인터넷 새옹에 대한 허가를 받아야함! = Permittion


        Thread t= new Thread(){//new 할 때 익명메소드로 오버라이드를 실행시켜!
            @Override
            public void run() {
                //network에 있는 이미지 주소
                String imgUrl= "https://pbs.twimg.com/profile_images/1069218313043996672/ss1k-fm5_400x400.jpg";
                //파일까지 연결되는 무지개로드(Stream)을 만들어주는 해임달(URL)객체 생성
                try {
                    URL url= new URL(imgUrl);

                    //해임달에게 무지개로드 열어줘!
                    InputStream is = url.openStream();

                    //무지개로드를 통해 이미지 데이터를 읽어와서 Image View에 설정
                    //안드로이드에서 이미지를 관리하는 객체인 Bitmap d으로 만들기
                    final Bitmap bm= BitmapFactory.decodeStream(is);

                    //그 Bitmap객체를 이미지뷰에 설정
                    //별도 Thread는 화면 변경
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            iv.setImageBitmap(bm);
                            //마치고 다이얼로그 사라지게
                        }
                    });



                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        };
        t.start();

    }
}
