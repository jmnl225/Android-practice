package com.jmnl2020.ex62servicebindtest;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MusicService extends Service {

    //5. MediaPlayer만들고
    MediaPlayer mp;

    //StartService()메소드로 실행했을떄만 실행되는 메소드
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }



    // BindService() 메소드로 실행했을때 자동으로 호출되는 메소드
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        //3. binder 만들고 리턴함.
        MyBinder binder = new MyBinder();
        return binder; //MainActivity로 파견나갈 객체(Binder)를 리턴
    }

    //2. MusicService 객체의 메모리주소(객체참조값)을 리턴해주는 기능을 가진 Binder클래스를 설계
    class MyBinder extends Binder{
        //이 서비스객체의 주소를 리턴해주는 메소드
        public MusicService getService(){
            return MusicService.this;
        }
    }

    //5. row폴더 생성하고 음악 넣고 난 뒤,
    // 음악재생기능
    void playMusic(){
        if(mp == null ){
            mp = MediaPlayer.create(this, R.raw.kalimba );
            mp.setLooping(true);
            mp.setVolume(0.7f, 0.7f);

        }
        mp.start(); // start = create + resume

    }

    // 음악 일시정지기능
    void pauseMusic(){
        if(mp!=null && mp.isPlaying()) mp.pause();
    }

    //음악 정지 기능
    void stopMusic(){
        if(mp!=null) mp.stop();
        mp.release();
        mp=null;
    }



}
