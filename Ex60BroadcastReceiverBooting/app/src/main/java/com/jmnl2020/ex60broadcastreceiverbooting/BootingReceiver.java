package com.jmnl2020.ex60broadcastreceiverbooting;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.net.URI;

public class BootingReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "알림: 부팅 감지", Toast.LENGTH_SHORT).show();

        String action = intent.getAction();

        if(action.equals(Intent.ACTION_BOOT_COMPLETED)){


            //android 10버전이나 그 이상에서는 직접 액티비티를 실행할 수 없음!
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                //부팅을 듣고 알림을 만들어서 그 알림(Notification)을 클릭하여 액티비티 실행

                //object인 줄 알고 에러가 남. 앞에 형변환 (NotificationManager) 직접 지정
                NotificationManager notificationManager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

                NotificationCompat.Builder builder = null;
                //일단 null을 대입하고 버전에 맞춰서 실행

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    NotificationChannel channel = new NotificationChannel("ch01", "channer #1", NotificationManager.IMPORTANCE_HIGH);
                    notificationManager.createNotificationChannel(channel);

                    //26버전 이상에서는 사운드나 진동을 채널객체를 사용해야함
                    Uri soundUri = Uri.parse("android.resource://"+context.getPackageName()+"/"+R.raw.s_sijak);
                    channel.setSound(soundUri, new AudioAttributes.Builder().build());


                    builder= new NotificationCompat.Builder(context, "ch01");

                }else {
                    builder= new NotificationCompat.Builder(context, null);
                    //사운드 작업
                    Uri soundUri = Uri.parse("android.resource://"+context.getPackageName()+"/"+R.raw.s_sijak);
                    builder.setSound(soundUri);
                }



                /////////////////////////////////설정 //////////////////////////////////////
                //상태바에 보이는 아이콘 삽입
                builder.setSmallIcon(R.drawable.ic_stat_name);


                //확장상태바의 설정들
                builder.setContentTitle("부팅이 완료되었습니다!");
                builder.setContentText("Ex60 MainActivity Loading...");

                //확장상태바의 알림을 클릭하여 MainActivity를 실행하도록..
                Intent i = new Intent(context, MainActivity.class); // 하지만 바로실행되지 않기 때문에 intent를 보류시킴

                PendingIntent pendingIntent = PendingIntent.getActivity(context, 55, i, PendingIntent.FLAG_UPDATE_CURRENT);
                //context , 식별자 번호, 인텐트 참조변수 i, 같은 알람이 또 왔을때 최신으로 업데이트

                //알림에 intent 적용
                builder.setContentIntent(pendingIntent);

                //클릭하면 자동으로 알림 없어지게
                builder.setAutoCancel(true);

                ////////////////////////////////////////////////////////////////////////////////

                //Notification notification= null;
                Notification notification = builder.build();
                notificationManager.notify(0, notification); //cancel 할 때 필요한 식별자 번호.

            }else{
                Intent i = new Intent(context, MainActivity.class);

                //액티비티를 실행하는 TASK가 없었기 때문에 그냥 startActivity()하면
                //새로운 액티비티가 실행되지 않음

                //인텐트에 설정 추가.
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);


            }


        }



    }
}
