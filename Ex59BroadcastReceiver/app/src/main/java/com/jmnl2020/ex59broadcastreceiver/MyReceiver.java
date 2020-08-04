package com.jmnl2020.ex59broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //이 리시버가 방송을 수신하면 발동하는 콜백메소드

        //만약 방송이 묵시적으로 왔다면 인텐트의 액션값을 확인할 수 있음
        String action = intent.getAction();

        if (action!=null){ //null이 아니라면 묵시적 방송이라는뜻

            switch (action){
                case "aaa":
                    break;

                case Intent.ACTION_BATTERY_LOW:

            }
        }
        Toast.makeText(context, "received", Toast.LENGTH_SHORT).show();


    }
}
