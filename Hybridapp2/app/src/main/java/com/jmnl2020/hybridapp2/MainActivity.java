package com.jmnl2020.hybridapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    EditText et;
    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        et = findViewById(R.id.et);
        wv = findViewById(R.id.wv);

        //웹뷰의 기본설정들

        wv.setWebViewClient(new WebViewClient());
        wv.setWebChromeClient(new WebChromeClient());

        WebSettings setting = wv.getSettings();
        setting.setJavaScriptEnabled(true);

        // 혹시 js 에서 ajax를 사용하고자 한다면..
        // http: 주소에서만 가능함. 만약 file://로 되는 곳에서 사용하려면
        setting.setAllowUniversalAccessFromFileURLs(true);
        //위의 코드 사용 ~!

        ////////////////////////////// 아래에서 통신 담당할 클래스를 만들면!!! 클래스를 객체로 만들어줘야함! //////////////////////////////////////
        // 웹뷰에서 보여주는 javascript 연결할 객체 생성
        wv.addJavascriptInterface( new WebViewConnector(),"Droid" ); //두번째 파라미터 : js에서 이 객체를 호출, 식별할 이름
        //java 내에서는 참조변수를 만들어 객체를 참조할 수 있지만,
        // javascript에서는 객체를 찾을 방법이 없음. 따라서 이름을 만들어주어야함!!!!!

        //웹뷰가 보여줄 웹문서 로딩
        wv.loadUrl("file:///android_asset/index.html");

    }

//    //디바이스의 뒤로가기 (back button)을 클릭했을 때
//    //WebView의 웹페이지를 이전으로 가도록..
//
//    @Override
//    public void onBackPressed() {
//
//        //웹뷰의 history에 이전 웹문서 기록이 있는가?
//        if( wv.canGoBack() ) wv.goBack();
//        else super.onBackPressed(); //부모의 back pressed 기능 작동
//    }

    //Native -> JS
    public void clickSend(View view) {
        //자바에서는 index.html안에 있는 요소들을 직접 제어하는 건 불가능

        //WebView가 보여주는 index.html 안에 있는
        //특정 함수 (프로그래머가 만든 setReceivedMessage() ) 를 호출해서 값 전달

        String msg = et.getText().toString();
        wv.loadUrl("javascript:setReceivedMessage('"+msg+"')");
        //msg를 변수명으로 알아듣게 하기 위해서 문자열을 덧셈+

        et.setText("");
    }

    //inner class
    //Javascript와 통신을 담당할 연결자 클래스 정의
    class WebViewConnector{

        //javascript에서 호출할 메소드는 반드시 특별한 어노테이션을 지정해야함
        // 어노테이션( @JavascriptInterface )이 지정된 클래스만 통신가능
        @JavascriptInterface
        public void getTextView( String msg ){
            tv.setText(msg);
        }


        //갤러리앱 실행하는 작업메소드 - js에서 불러오도록
        @JavascriptInterface
        public void openGallery(){

            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivity(intent);

        }

    }

}//MainActivity class..



