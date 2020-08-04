package com.jmnl2020.ex61servicetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickplay(View view) {
        //백그라운드에서 뮤직을 실행하는 서비스 시작

        Intent intent = new Intent(this, MusicService.class);
//        startService(intent);
        //포어그라운드 서비스로 실행하도록해야함. -> 버전이 오레오보다 높아야한다.
        //이걸 실행할 때 꼭 !! **퍼미션**을 받아야함!
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)  startForegroundService(intent);
        else startService(intent);


    }

    public void clickstop(View view) {
        //뮤직을 백그라운드에서 실행하는 서비스를 종료!
        Intent intent = new Intent(this, MusicService.class);
        stopService(intent);

    }

}
