package com.jmnl2020.ex62servicebindtest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MusicService musicService;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //액티비티가 화면에 보일때 실행 메소드
    @Override
    protected void onResume() {
        super.onResume();

        // 1. mainActivity
        //서비스객체 실행 및 연결(bind)
        if(musicService == null){ //연결되어있는 뮤직서비스가 없을때
            Intent intent = new Intent(this, MusicService.class);
            startService(intent); //일단 Service 객체 생성 [만약 Service객체가 없다면 만들고 onStartCommand()가 발동,
            //있다면 생성은 안 하고 onStartCommand()만 발동 ]

            //확인 후 만들어진 Service 객체와 연결
            //bindService()호출하면 Service클래스안의 onBind()메소드가 발동하고
            //이 onBind()가 Service의 정보를 가진 (객체의 참조값을 가진 객체를) 리턴해줌.

            bindService(intent, conn, 0); //flags값이 0이면 auto create하지 않음
            //* bindService의 마지막 파라미터를 AUTO_CREATE로 만들면 저절로 생성되지만, 이 때
            // MainActivity에서 사용자(유저가) 이전 화면으로 돌아가거나 앱을 내려두었을때 동작이 멈춤.
            //멈추게 하지 않기위해 MusicService와 연결된 다른 참조변수 혹은 뭔가가 필요함

        }
    }

    //4. bindService()를 했을 때 Service객체와 연결된 통로 객체 연결시키고 binder객체 받아오기
    ServiceConnection conn= new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //통로가 잘 연결되었을 때, 두번째 파라미터 IBinder: 서비스객체의 참조주소를 주는 메소드가 있는 객체
            MusicService.MyBinder binder = (MusicService.MyBinder) service; //service를 형변환

            musicService= binder.getService(); //리턴해준 서비스객체주소 참조

            Toast.makeText(musicService, "서비스와 연결되었습니다.", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };



    public void clickPlay(View view) {
        if(musicService!=null) musicService.playMusic();
    }

    public void clickPause(View view) {
        if(musicService!=null) musicService.pauseMusic();
    }

    public void clickStop(View view) {
        if(musicService!=null) {
            musicService.stopMusic();
            unbindService(conn); //터널 끊기. 연결종료.
            musicService = null;
        }

        //서비스객체도 아예 종료
        Intent intent = new Intent(this, MusicService.class);
        stopService(intent);

        finish(); // 액티비티 종료

    }

}
