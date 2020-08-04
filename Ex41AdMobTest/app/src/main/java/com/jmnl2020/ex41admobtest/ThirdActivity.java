package com.jmnl2020.ex41admobtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.ads.rewarded.RewardedAd;

public class ThirdActivity extends AppCompatActivity {

    //보상형 광고
    RewardedVideoAd rewardedAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        //리워드광고 객체 생성
        rewardedAd = MobileAds.getRewardedVideoAdInstance(this);

        //리스너 추가
        rewardedAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {
                rewardedAd.show();
            }


            @Override
            public void onRewardedVideoAdOpened() {

            }

            @Override
            public void onRewardedVideoStarted() {

            }

            @Override
            public void onRewardedVideoAdClosed() {

            }

            //동영상시청 후 보상을 받을 때 자동 실행
            @Override
            public void onRewarded(RewardItem rewardItem) {
                String type = rewardItem.getType();
                int cnt= rewardItem.getAmount();

                Toast.makeText(ThirdActivity.this, type+" : "+cnt, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onRewardedVideoAdLeftApplication() {

            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {

            }

            @Override
            public void onRewardedVideoCompleted() {

            }
        });

        //리워드 비디오 광고는 요청을 하면서 광고단위 Id 지정
        AdRequest adRequest= new AdRequest.Builder().build();
        rewardedAd.loadAd("ca-app-pub-3940256099942544/5224354917", adRequest);





    }

    public void clickshow2(View view) {

        AdRequest adRequest= new AdRequest.Builder().build();
        rewardedAd.loadAd("ca-app-pub-3940256099942544/5224354917", adRequest);

    }
}
