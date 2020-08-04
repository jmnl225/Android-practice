package com.jmnl2020.ex86firebasecloudmessagepush;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.RemoteMessage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickBtn(View view) {
        //앱을 FCM서버에 등록하는 과정
        //앱을 FCM서버에 등록하면 앱을 식별할 수 있는 고유 토큰값(문자열)을 받을 수 있음
        //이 토큰 값(Instance ID)을 통해 앱들(디바이스들)을 구별할 메세지가 전달됨

        FirebaseInstanceId firebaseInstanceId= FirebaseInstanceId.getInstance();
        Task<InstanceIdResult> task = firebaseInstanceId.getInstanceId();
        task.addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                String token = task.getResult().getToken();

                //토큰값 출력
                Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();

                //Logcat창에 토큰값 출력
                Log.i("TAG",token);

                //실무에서는 token값을 본인의 웹서버(dothome서버)에 전송해서 웹 DB에 토큰값 저장하도록 해야함.
                //이 메소드의 파라미터로 전달된 RemoteMessage객체: 받은 원격 메세지


            }
        });



    }
}
