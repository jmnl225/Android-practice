package com.jmnl2020.ex86firebasecloudmessagepush;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFCMReceiveService extends FirebaseMessagingService {

    //push 서버에서 보낸 메세지가 수신되었을때 자동으로 발동하는 메소드
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        //이 안에서는 알림(Notification)만 만들 수 있음 [심지어 Toast도 불가]
        // 우선, 리시브 확인용으로 Logcat에 출력
        Log.i("TAG","=====onMessageReceived!!=====");

        //메세지를 보낸 기기명 [firebase 서버에서 자동으로 지정된 이름]
        String fromWho= remoteMessage.getFrom();

        //알림에 대한 데이터들
        String notiTitle = "title"; //제목이 안 왔을때의 기본값
        String notiText = "body text"; //글씨가 안 왔을때의 기본값

        if(remoteMessage.getNotification()!=null){
            notiTitle = remoteMessage.getNotification().getTitle();
            notiText = remoteMessage.getNotification().getBody();
            // Uri notiImage = remoteMessage.getNotification().getImageUrl(); // 이건 유료 ㅠ

        }

        // firebase 푸시 메세지에 추가로 데이터가 있을 경우([ key : value ]) 형태로 송신된 데이터들
        Map<String, String> data = remoteMessage.getData();

        String name =null;
        String msg= null;
        if(data != null){
            name= data.get("name");
            msg = data.get("msg");
        }

        //잘 받았는지 확인
        Log.i("TAG", fromWho+" : "+notiTitle+", "+ notiText +"data: "+name+", "+msg);


        //받은 값들을 알림객체(Notification)를 만들어 공지하기
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = null;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("ch01","채널 01", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
//            notificationManager.createNotificationChannel(new NotificationChannel("ch01","채널 01", NotificationManager.IMPORTANCE_HIGH));

            builder = new NotificationCompat.Builder(this,"ch01");

        }else {
            builder= new NotificationCompat.Builder(this, null);
        }


        builder.setSmallIcon(R.drawable.ic_stat_flower);
        builder.setContentTitle(notiTitle);
        builder.setContentText(notiText);

        //알림을 선택했을때 실행될 액티비티를 실행하는 intent
        Intent intent = new Intent(this,MessageActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("msg", msg);
        //앱이 꺼져있을때 알람을 누르고 들어오게 된다면...
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // 이미 있던 액티비티를 모두 없애고 새로운 것으로 실행하라

        //보류중인 intent로 변환
        PendingIntent pendingIntent = PendingIntent.getActivity(this,2222, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //가장마지막: pending이 또 있으면 어떡할것이냐? 가장 최신것으로 업데이트 해달라

        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true); //알람을 선택했을때 화면에 있던 알람 없애기

        Notification notification = builder.build();
        notificationManager.notify(1111,notification);

    }
}
