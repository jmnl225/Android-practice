package com.jmnl2020.ex30activity5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //안드로이드의 시스템 앱들 실행하기
    //ex) 전화걸기 앱, 사진앱, 문자앱, 카메라앱
    //이런 앱들은 모두 지정된 intent action값을 갖고있음
    //따라서 프로그래머는 묵시적인텐트를 이용해서 지정된 액션의 앱들을 실행

    public void clickDial(View view) {
        //전화거는 DialActivity 앱앱
        Intent intent= new Intent();
        intent.setAction(Intent.ACTION_DIAL); //다이얼앱의 약속된 Action문자열 값

        //다이얼화면에 미리 전화번호가 입력되길 원한다면
        Uri uri= Uri.parse("tel:01033335555");
        intent.setData(uri);

        startActivity(intent);
      }

    //=====================================================================

    public void clickSMS(View view) {
        //Short Message Service 짧은 문자 서비스앱
        Intent intent= new Intent();
        intent.setAction(Intent.ACTION_SENDTO);

        Uri uri= Uri.parse("smsto:01011112222,01022223333");
        intent.setData(uri);

        //문자내용을 미리 작성가능
        //식별자 "sms_body"는 약속된 글씨. 마음대로 변경 불가
        intent.putExtra("sms_body","Hello, Good Afternoon! :)");

        startActivity(intent);

    }

    //======================================================================

    public void clickWeb(View view) {
        //웹사이트 연결

        Intent intent= new Intent(Intent.ACTION_VIEW); //액션값 지정
//        intent.setAction(Intent.ACTION_VIEW);
//        Uri uri= Uri.parse("http://www.naver.com");

        intent.setData(Uri.parse("http://www.naver.com"));

        startActivity(intent);

    }

    //=======================================================================

    public void clickGallery(View view) {
        //Gallery 앱 연결

        Intent intent= new Intent(Intent.ACTION_PICK);
        //반드시 추가해야하할 설정
//        intent.setType("image/png"); 이미지파일 : 오디오, 비디오, 사진 중 택1 + 확장자 표시
        intent.setType("image/*");

        startActivity(intent);

    }

    //=======================================================================

    public void clickCamer(View view) {
        //Camera 앱 연결

        Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivity(intent);

    }

}// main end.
