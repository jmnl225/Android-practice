package com.jmnl2020.ex84firebasestorage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv= findViewById(R.id.iv);

        String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if( checkSelfPermission(permissions[0]) == PackageManager.PERMISSION_DENIED){
                requestPermissions(permissions, 100);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if( requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_DENIED){
            Toast.makeText(this, "업로드가 안 됩니다ㅠ", Toast.LENGTH_SHORT).show();
        }

    }

    public void clickLoad(View view) {

        //fire storage에 있는 이미지 보여주기

        // 저장된 이미지의 URL을 얻어와서 이미지뷰에 보여주기!

        //Firebase Storage 관리객체 얻어오기
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();

        //최상위 참조객체 얻어오기
        StorageReference rootRef= firebaseStorage.getReference();

        //읽어오길 원하는 파일의 참조객체 얻어오기
//        StorageReference imgRef = rootRef.child("icebg1.jpg");
        StorageReference imgRef = rootRef.child("photos/nm1.jpg");


        //파일 참조객체로부터 이미지의 URL 얻어오기
        // [https://firebasestorage.googleapis.com/v0/b/ex84firebasestoragejmnl.appspot.com/o/icebg1.jpg?alt=media&token=7199237e-29aa-41b5-8724-200eabe8d9f2]

        if( imgRef != null ){
            //참조객체로부터 URL 을 얻어오는 작업이 성공했을때의 리스너 실행
            imgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {

                        Glide.with(MainActivity.this).load(uri).into(iv);

                }
            });
        }


    }

    public void clickSel(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");

        startActivityForResult(intent, 10);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==10 && resultCode == RESULT_OK){
            Uri uri = data.getData();
            Glide.with(this).load(uri).into(iv);

            imgUri = uri;

        }

    }

    //멤버변수
    Uri imgUri;

    public void clickUpload(View view) {
        //Firebase Storage 관리자 소환
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance(); //싱글톤 방식

        //업로드해서 저장될 파일명이 같으면 덮어쓰기 되므로
        // 날짜를 이용해서 파일명 만들어내기.
        SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMddhhmmss");
        String fileName = sdf.format(new Date())+".png"; //20200623142901

        //업로드할 파일의 참조객체 만들기 [ 파일 데이터는 없고 파일명만 만들어진 상태 ]
        StorageReference imgRef= firebaseStorage.getReference("uploads/"+fileName);

        //위 위치의 참조객체에 이미지파일 데이터 덩어리 보내기
 //       imgRef.putFile(imgUri);

        //업로드의 성공 결과를 알고 싶다면 [Task : 별도 thread 객체와 비슷]
        UploadTask task = imgRef.putFile(imgUri);
        task.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(MainActivity.this, "upload Success", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
