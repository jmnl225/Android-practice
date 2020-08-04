package com.jmnl2020.ex88kakaologin;

import android.app.Application;
import android.content.Context;

import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.KakaoAdapter;
import com.kakao.auth.KakaoSDK;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 카카오 SDK와 앱의 Application 연결
        // KakaoAdapter : 카카오와 앱을 연결

        //카카오 초기화
        KakaoSDK.init(new KakaoAdapter() {
            @Override
            public IApplicationConfig getApplicationConfig() {
                // Application이 가지고있는 정보를 얻기위한 interface

                // 추상메소드를 리턴하는 값이 추상메소드라 오버라이드를 하면서 리턴.

                return new IApplicationConfig() {
                    @Override
                    public Context getApplicationContext() {
                        return MyApplication.this;
                    }
                };
            }
        });


    }}
