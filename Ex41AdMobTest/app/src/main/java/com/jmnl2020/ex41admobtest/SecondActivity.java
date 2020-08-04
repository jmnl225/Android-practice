package com.jmnl2020.ex41admobtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.reward.RewardedVideoAd;

public class SecondActivity extends AppCompatActivity {

    //전면광고
    InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //객체 생성 및 광고단위 ID 설정
        interstitialAd= new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        //광고 요청
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);

        //광고가 모두 load된 후  interstitialAd에 보여지도록!
        interstitialAd.setAdListener(new AdListener(){

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                Toast.makeText(SecondActivity.this, "광고 로딩 실패", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Toast.makeText(SecondActivity.this, "광고 로드 성공", Toast.LENGTH_SHORT).show();
                interstitialAd.show();
            }
        });

    }

    public void clickshow(View view) {

        //다시 부르는 방법
        AdRequest adRequest= new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);

    }


}
