package com.jmnl2020.chattest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EditActivity extends AppCompatActivity {

    EditText etName;
    EditText etTitle;
    EditText etMsg;
    ImageView et_iv;

    //
    String uid;
    ArrayList<String> writeKey = null;
    ArrayList<String> writeValue = null;

    ArrayList<String> adapterString= null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etMsg = findViewById(R.id.et_msg);
        etTitle= findViewById(R.id.et_title);
        etName= findViewById(R.id.et_name);
        et_iv = findViewById(R.id.et_iv);

    }

    public void clickSelectImage(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");

        startActivityForResult(intent, 100);
    }

    Uri imgUri;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && resultCode == RESULT_OK){
            imgUri = data.getData();
            if(imgUri != null){
                Glide.with(EditActivity.this).load(imgUri).into(et_iv);
            }
        }

    }

    public void clickComplete(View view) {
        // 데이터들을 서버로 전송

        //나의 클래스 [Value Object] 객체를 만들어서 한번에 멤버값 저장시키기.
        // img 는 Storage에, String 은 database에 저장.


        requestPermission();

        uploadImg();
        uploadfiles();

        finish();

    }

    private void requestPermission() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            int permissionResult = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if(permissionResult == PackageManager.PERMISSION_DENIED){
                String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, 100);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 100 && grantResults[0]== PackageManager.PERMISSION_DENIED){
            Toast.makeText(this, "사진업로드 불가능", Toast.LENGTH_SHORT).show();
        }
    }

    private void uploadImg() {

        //이미지 파일을 업로드하기

        // 1. Firebase Storage 관리자 소환
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();

        // 2. 같은 파일명이 있으면 덮어쓰기 되므로 날짜를 이용한 파일명 저장
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String filename = sdf.format(new Date())+".png";

        // 3. 업로드할 파일의 참조객체 만들기
        StorageReference imgRef= firebaseStorage.getReference("uploadMainImg/"+filename);

        // ++ 업로드의 성공 결과를 알고싶다면 [Tsak 이용]
        UploadTask task = imgRef.putFile(imgUri);
        task.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(EditActivity.this, "이미지 업로드 완료", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void uploadfiles() {//나의 클래스 [Value Object] 객체를 만들어서 한번에 멤버값 저장시키기..?????


        // 1. 저장할 값들을 얻어오기.
        String cvTitle = etTitle.getText().toString();
        String cvName = etName.getText().toString();
        String cvMsg = etMsg.getText().toString();

        String cvImgfile = imgUri.toString();

        // 2. database 관리 객체
        FirebaseDatabase db= FirebaseDatabase.getInstance();

        // 3. 저장시킬 노드 참조객체
        DatabaseReference rootRef = db.getReference(); // 최상위 노드 호출


        // 4. 저장할 값들을 하나의 객체로 생성 String cvTitle, String cvName, String cvMsg, String cvImgfile
        CardviewItem uploaditem = new CardviewItem(cvTitle, cvName, cvMsg, cvImgfile);

        Log.i("TAG", "Test1");

        // 5. CardviewItem 이름의 노드를 참조하는 객체 생성, 참조
        DatabaseReference itemRef = rootRef.child("ChatRooms"); //채팅방 노드는 하나고 그 아래로 쭉 만들어지게 할 수 없나? 방 이름만 새로 만들도록.
        itemRef.child(cvTitle).setValue(uploaditem);

        // 6. 채팅방 노드의 밸류가 변경되는 것을 듣는 리스너 추가
        itemRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // 여기엔 뭘 써야하지...?
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }





}


