package com.jmnl2020.ex61servicetest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.jmnl2020.ex61servicetest.MainActivity;
import com.jmnl2020.ex61servicetest.R;

public class MusicService extends Service {

    MediaPlayer mp;

    //StartService()를 통해 서비스가 시작되면 자동으로 실행 콜백메섣

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //Oreo버전부터 운영체제에서 백그라운드작업 및 방송수신작업에 제약을 두고있음.
        //(배터리문제)로 만약 백그라운드에서 서비스가 계속 동작하고 싶다면
        // foreGroundService 로 돌려야함.
        // 즉, 사용자가 서비스가 가동중임을 인지할 수 있는 장치 마련.

        ///////////////////////////////////////
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            NotificationChannel channel= new NotificationChannel("ch1", "뮤직서비스", NotificationManager.IMPORTANCE_LOW);
            notificationManager.createNotificationChannel(channel);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "ch1");

            //알림 설정들
            builder.setSmallIcon(R.drawable.ic_stat_name);
            builder.setContentTitle("Music Service");
            builder.setContentText("뮤직서비스가 실행중입니다.");

            //알림창을 클릭했을 때 뮤직제어화면으로 전환되도록
            Intent i = new Intent(this, MainActivity.class);
            //이거 추가하기!
            i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); // 기존의 액티비티를 가장 앞으로 옮겨라!
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


            PendingIntent pendingIntent= PendingIntent.getActivity(this, 11, i, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);

            builder.setAutoCancel(true); // 원래 클릭하면 알림이 자동취소되어야하지만 Service 포어그라운드에서는 적용되지 않음.

            Notification notification = builder.build();

            //이 서비스를 포어그라운드에서 돌아가도록 명령
            // -> 포어그라운드서비스를 하려면 Notification을 만들어야함.
            startForeground(1, notification); //id를 0번으로 사용하면 안 됨!!!
        }


        //미디어플레이어 객체 생성 및 시작!
        if(mp==null){
            mp= MediaPlayer.create(this, R.raw.kalimba);
            mp.setLooping(true);
            mp.setVolume(1.0f, 1.0f);
        }

        mp.start();

        //메모리문제로 인해 서비스를 강제로 kill시켰을 때, 메모리문제가 해결되면 자동으로 서비스를 다시 실행해달라!
        // returen START_STICKY
        return START_STICKY;

    }

    //stopService()를 통해 서비스가 종료되면 자동으로 실행되는 메소드
    @Override
    public void onDestroy() {

        //mp가 객체가 없을 때 mp. 으로 명령을 내리면 널포인트에러. 따라서  mp가 가리키는 객체 유무 확인 먼저.
        if(mp!=null && mp.isPlaying() ){
         mp.stop();
         mp.release(); // mediaplayer객체를 메모리에서 삭제
         mp=null;


         //Garbage Collector 가 null point인 경우의 객체는 삭제하지만 Thread는 예외로 없애지 않는다.
            //따라서 꼭!!! 직접 지워주어야만 함!!!!
        }

        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }
}
