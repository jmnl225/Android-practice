package com.jmnl2020.hybridapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wv = findViewById(R.id.wv);

        //예전 웹뷰는 기본적으로 웹문서를 열 때 본인 안에 열리는 게 아니라
        // 새로운 웹 브라우저앱을 실행하여 그 곳에서 보여줌
        // 이를 방지하기 위해서
        wv.setWebViewClient( new WebViewClient()); //이 코딩을 써줘야함.

        //하지만!!!! [버전업 된 웹뷰에서는 위 코드가 필요없음!]

        //========================================================

        //웹뷰는 기복적으로 alert(), confirm() 같은 별도의 모달 다이얼로그를 제한한다.
        // 사용하게 하려면
        wv.setWebChromeClient( new WebChromeClient() );

        //웹뷰는 기본적으로 js 사용을 제한함.
        WebSettings settings = wv.getSettings();
        settings.setJavaScriptEnabled(true);

        //========================================================


        //웹뷰가 보여줄 웹문서 (.html) 로드하기
        // 하이브리드 앱은 오프라인에서도 동작해야하므로
        // .html 을 서버에 위치하지 않고 앱 프로젝트 폴더 안에 위치시킴

        //wv.loadUrl("http://");

        //html문서가 위치한 asset 폴더의 파일을 웹뷰에서 로드하도록
        wv.loadUrl("file:///android_asset/index.html");
        // 슬래시 / 하나는 폴더

    }
}
