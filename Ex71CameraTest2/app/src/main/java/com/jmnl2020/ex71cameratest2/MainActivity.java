package com.jmnl2020.ex71cameratest2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ImageView iv;

    //캡쳐한 이미지 경로 Uri 참조변수
    Uri imgUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = findViewById(R.id.iv);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            int permissionResult = checkSelfPermission((Manifest.permission.WRITE_EXTERNAL_STORAGE));

            if(permissionResult == PackageManager.PERMISSION_DENIED){
                String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(permissions, 100);
            }

        }
    }// onCreate method

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch ( requestCode ){
            case 100:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED ) {
                    Toast.makeText(this, "사진 저장이 가능합니다.", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "촬영된 사진이 저장 불가합니다.", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    public void clickFAB(View view) {
        //카메라 앱 실행
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //카메라 앱에게 캡쳐한 이미지를 저장하게 하려면
        //저장할 이미지의 파일경로 URI 를 미리 지정해주어야함!

        //캡쳐된 이미지 Uri를 정하는 메소드 (코드가 복잡해서 메소드를 따로 만들어 사용.)

        setImgUri();

        //key 값으로 경로를 지정하는 키워드가 정해져있음. MediaStore.EXTRA_OUTPUT
        if(imgUri != null) intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
        //imgUri가 null이면 에러!

        startActivityForResult(intent, 30);
        //앱을 실행함

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //카메라 찍고 왔을 때 실행

        switch (requestCode){
            case 30:
                if(resultCode == RESULT_OK){
                    // 사진정보를 가지고 돌아온 intent객체에게 정보를 달라고 요청
                    // 그런데.. 저장하도록 putExtra작업을 하면 Intent가 안 오는 경우도 있음!
                    if(data != null){
                        //돌아온 intent객체에게 Data를 달라고 요청
                        Uri uri = data.getData();

                        if(uri != null){ // intent가 가져온 게 Uri일 때

                            Glide.with(this).load(uri).into(iv);
                            Toast.makeText(this, "Uri로 받음", Toast.LENGTH_SHORT).show();

                        }else {// intent가 가져온 게 Bitmap일 때
                            //Bitmap으로 보여주면 해상도가 낮기 때문에
                            if(imgUri != null )Glide.with(this).load(imgUri).into(iv);
                            Toast.makeText(this, "Bit으로 돌아옴", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        //돌아온 intent객체가 없는 경우.
                        //카메라앱에게 저장하도록 요청했던 사진경고Uri를 이용하여 이미지 보여주기

                        if(imgUri != null ) Glide.with(this).load(imgUri).into(iv);
                        Toast.makeText(this, "인텐트가 안 돌아왔을 때", Toast.LENGTH_SHORT).show();

                    }
                }
                break;
        }

    }

    //캡쳐한 이미지를 저장할 파일경로 Uri를 정하는 메소드
    void setImgUri(){

        //외부저장소(SD Card)에 저장하는 것을 권장
        // 이 때 외부저장소의 2가지 영역이 존재.

        //1. 외부저장소 중에서 본인 앱에 할당된 영역
        // 찾아보려면 : Device File Explorer - storage - self (사실 emulated지만 프라이빗이라 안 보임
// - primary - Android - data - 내 앱 (패키지) 주소
        //특징: 퍼미션이 없어도 oK , 앱을 지우면 저장된 사진도 같이 삭제됨


        //2. 외부저장소 중에서 공용영역 - 동적퍼미션이 필요.
        // 특징: 앱을 지워도 사진은 저장되어있음.

        File path = Environment.getExternalStorageDirectory(); //외부메모리의 최상위(root) 경로 = [storage/emulated/0/]

        path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES); //핸드폰 폴더 중 pictures
        // [storage/emulated/0/pictures/ ]

        // 경로를 정했으므로 파일명 정하기.
        // 같은 이름으로 저장하면 덮어쓰기가 되므로 저장할때마다 다른 이름으로 넣도록..
        // 통상적으로 일시를 이용해서 파일명 생성. ex) Img_20200611_104131.jpg
        // 1) 날짜를 이용
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String fileName = "IMG_"+sdf.format(new Date())+".jpg";

        //파일명과 경로를 합쳐서 File객체 생성
        File file= new File(path, fileName);


        // 2) 자동으로 임시파일명 생성하는 메소드 이용방법
        // 찍은 순서가 아닌 이름 순서대로 저장되기때문에 랜덤으로 만들어진 이름이라 순서가 엉망.

//        try {
//            file= File.createTempFile("IMG_", ".jpg", path);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        //카메라앱에 전달해줘야할 저장파일경로는 file객체가 아니라 Uri 객체여야함.
        // Uri 는 리소스의 식별자. 진짜 경로가 될 수 없음.
        //지금까지 만들어진건 File의 경로이지 Uri는 아님. 그런데 보내줘야 하는 게 Uri 이므로
        // File -> Uri 변환

        //누가(N)버전부터 이 변환이 어려워졌음.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N){
            imgUri = Uri.fromFile(file);
        }else {

            //다른 앱에게 파일의 접근을 허용하도록 하는 Content Provider를 이용해야함.
            // Provider중에서 안드로이드에 이미 만들어져있는 FileProvider를 사용!

            //작업순서 :
            // 1. Manifest.xml에 프로바이더 등록 <provider> 태그를 이용하여  FileProvider클래스 등록
            // 2. FileProvider가 공개할 파일의 경로를 res>>xml 폴더 안에 "paths.xml"  이름으로 만들어서 <path> 태그로 경로 지정
            // 3. 자바에서 <Provider> 태그에 작성한 속성 중 authorities = ""에 작성한 값을 사용

            imgUri = FileProvider.getUriForFile(this, "com.jmnl2020.ex71cameratest2.provider", file);

        }
        //imgUri 작업이 끝!! 잘 되었는지 확인
        new AlertDialog.Builder(this).setMessage(imgUri.toString()).create().show();

    }



}
