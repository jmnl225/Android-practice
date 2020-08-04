package com.jmnl2020.ex41admobtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {

    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adView= findViewById(R.id.adView);

        //광고요청객체 생성
        AdRequest adRequest= new AdRequest.Builder().build();
        adView.loadAd(adRequest);


    }

    public void clickBtn(View view) {
        Intent intent = new Intent( this, SecondActivity.class);
        startActivity(intent);
    }

    public void clickBtn2(View view) {
        Intent intent = new Intent( this, ThirdActivity.class);
        startActivity(intent);
    }
}
