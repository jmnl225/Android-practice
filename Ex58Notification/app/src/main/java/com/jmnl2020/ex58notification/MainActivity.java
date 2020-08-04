package com.jmnl2020.ex58notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickBtn(View view) {

        //notification을 띄워줄 수 있는 관리자객체 소환
        //리턴타입이 오브젝트라서 에러 생김 -> 형변환
        NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //알림(Notification)객체를 만들어두는 건축가 객체 생성
        NotificationCompat.Builder builder= null;

        //오레오버전(api 26) 부터 '알림채널'이 생겼음 따라서 버전에 맞춰 코드 써주기
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //'알림채널'객체 생성성
            NotificationChannel channel= new NotificationChannel("ch01", "channe #1", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);

            builder = new NotificationCompat.Builder(this, "ch01");
        }else {
            builder= new NotificationCompat.Builder(this, null);
        }

        //만들어진 빌더를 이용하여 건축가에게 notification의 모양을 설정
        builder.setSmallIcon(R.drawable.ic_stat_name); // 상태표시줄에 보이는 아이콘 지정

        //확장상태바[상태표시줄을 드래그하여 아래로 내리면 보이는알림창]
        // 확장 상태바에 표시되는 설정
        builder.setContentTitle("쨘 문자에요!");
        builder.setContentText("알림 메세지 입니다.");
        builder.setSubText("sub Text: 아무글씨나 쓰면 됨");

        //res폴더 창고 관리자
        Resources res = getResources();
        Bitmap bm= BitmapFactory.decodeResource(res, R.drawable.a_frog);

        builder.setLargeIcon(bm); //bitmap으로 달라고 요구

        //확장상태바에 큰 이미지 스타일 적용
        NotificationCompat.BigPictureStyle bigPictureStyle= new NotificationCompat.BigPictureStyle(builder);
        //자동으로 빌더에게 연결됨
        //하지만 Bigpicture가 뭔지 정하지 않음
        bigPictureStyle.bigPicture(BitmapFactory.decodeResource(res, R.drawable.moana03));

        //여러스타일 객체들
        //Notification.BigTextStyle
        //Notification.InboxStyle
        //Notification.MediaStyle


        builder.setProgress(100, 50, true);
        //indeterminate : true 라고 적으면 완료가 언제되는 지 몰라 랜덤으로 라인 게이지 애니메이션이 진행

        //진동 : 반드시 android manifest에서 퍼미션을 받아야함
        builder.setVibrate(new long[]{0, 2000, 1000, 3000}); // 0초 대기, 2초 진동, 1초 대기, 3초 진동


        //사운드 알림
//        URI uri= RingtoneManager.getActualDefaultRingtoneUri(this, RingtoneManager.TYPE_NOTIFICATION);
        Uri uri= Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.get_coin);
        builder.setSound(uri);


        //확장상태바의 알림을 클릭했을 때 새로운 액티비티로 이동!
        Intent intent = new Intent(this, SecondActivity.class);
        //intent는 바로 start 시켜줘야 함. 따라서 보류중인 intent 생성
        PendingIntent pendingIntent= PendingIntent.getActivity(this, 10, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pendingIntent);

        //알림 확인했을 때 자동으로 알림이 삭제 **반드시 위에 (setContentIntent()했을 때만 가능)
        builder.setAutoCancel(true);


///////////////////////////////////////////////////////////////////////////////

        //알림 객체가 있어야 notify(보여주기) 할 수 있음.
        //알림 객체 생성
        Notification notification= builder.build();

        //알림 관리자에게 알림을 보이도록 공지
        notificationManager.notify(1, notification);

    }
}
