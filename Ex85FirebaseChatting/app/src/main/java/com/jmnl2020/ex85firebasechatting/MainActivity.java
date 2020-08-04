package com.jmnl2020.ex85firebasechatting;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    CircleImageView ivProfile;
    EditText etName;

    //프로필이미지 uri 멤버변수
    Uri imgUri;

    //이미지 변경 표식용
    Boolean isChanged = false; //이미지 변경이 된 적 있는지

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivProfile = findViewById(R.id.iv_profile);
        etName= findViewById(R.id.et);

        //이미 저장되어있는 정보들 읽어오기
        loadData();

        if(G.nickName != null){
            etName.setText(G.nickName);
            Glide.with(this).load(G.profileUri).into(ivProfile);

            //프로필 이미지를 변경했다고 표식
            //isChanged = true;

        }

    }

    public void clickImg(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && resultCode== RESULT_OK){
            imgUri = data.getData();

            if(imgUri != null){
                Glide.with(this).load(imgUri).into(ivProfile);

                //프로필 이미지를 변경했다고 표식
                isChanged = true;
            }
        }

    }

    //데이터를 저장하는 메소드
    void saveData(){
        //프로필 이미지와 채팅명을 Firebase에 저장
        // 그 저장할 변수를 직접 만들지 않고 별도의 class 를이용해서 마치 R장부처럼 사용
        // 따라서 모든 액티비티에서 사용 가능!

        G.nickName= etName.getText().toString();
        //G.profileUri= imgUri.toString(); //Uri의 정보(정체경로)를 저장

        //1. 이미지 파일부터 Firebase Storage에 업로드
        // 1-1. 업로드할 파일명이 같으면 안되므로 날짜를 이용해서 파일명 지정
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        final String fileName = sdf.format(new Date())+".png";

        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();

        final StorageReference imgRef= firebaseStorage.getReference("profileImages/"+fileName);

        // 1-2. 파일 참조변수에 이미지 업로드
        UploadTask task = imgRef.putFile(imgUri);
        task.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() { //업로드가 완료되는 것을 들음
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //Firebase 실시간 DB에 저장할 경로로에 업로드된 실제 인터넷경로 URL 알아내야함
                imgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {//따라서 경로 다운로드의 완료를 듣는 리스너
                    @Override
                    public void onSuccess(Uri uri) {
                        // 다운로드 URL을 G.profileURi에 저장
                        G.profileUri= uri.toString();


                        //firebase에 저장된 이미지 파일의 경로를 fireDB에 저장하고 내 디바이스에도 저장

                        // 2. firebaseDB 저장 [ G.nickName & ImageUri의 다운로드 URL ]
                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        //"profiles" 라는 이름에 자식 노드를 참조하는 참조객체 가져오기
                        DatabaseReference profileRef= firebaseDatabase.getReference("profiles");

 //                       //nickName을 key값으로 지정한 노드에 이미지경로(imgurl)를 값으로 지정
//                        profileRef.child(G.nickName).setValue();

                        //nickName을 Key값을 지정한 노드에 이미지 URL을 값으로 지정
                        profileRef.child(G.nickName).setValue(G.profileUri);


                        // 3. sharedPreferences 이용하여 저장 [ G.nickName & G.profileUri ]
                        SharedPreferences pref = getSharedPreferences("account", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();

                        editor.putString("nickName", G.nickName);
                        editor.putString("profileUrl", G.profileUri);

                        editor.commit();

                        Toast.makeText(MainActivity.this, "저장 완료.", Toast.LENGTH_SHORT).show();

                        //모든 저장이 완료되었으므로 채팅화면으로 이동.

                        //누르면 채팅 액티비티로 이동
                        Intent intent = new Intent(MainActivity.this, ChattingActivity.class);
                        startActivity(intent);

                        finish();

                    }
                });
            }
        });


    }

    public void clickBtn(View view) {
        // 프로필 이미지와 채팅명을 Firebase에 저장하고 그걸 가져와서 서로에게 보여주어야함!

        //이미지를 바꿨다면 그 때 저장을 하는걸로
        if(isChanged){
            saveData();
        }else {
            Intent intent = new Intent(this, ChattingActivity.class);
            startActivity(intent);

            finish();
        }


    }

    //Device에 저장된 정보 가져오기
    void loadData(){

        SharedPreferences pref = getSharedPreferences("account", MODE_PRIVATE);
        G.nickName = pref.getString("nickName", null); // nickName 이라는 키값에 저장된 데이터 불러오기. / 만약 없으면 빈칸
        G.profileUri= pref.getString("profileUrl", null);

    }

}
