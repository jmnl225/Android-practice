package com.jmnl2020.ex80retrofitimageupload;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    ImageView iv;

    //업로드할 이미지의 절대경로 참조변수
    String imgPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv= findViewById(R.id.iv);

        //외부저장소 접근에 대한 동적퍼미션
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 100:
                if(grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(this, "접근이 거부되어 앱을 정상적으로 사용할 수 없습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                }
        }

    }

    public void clickBtn(View view) {
        //사진앱을 실행해서 사진을 선택할 수 있도록..
        Intent intent= new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");

        startActivityForResult(intent, 10);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==10 && resultCode==RESULT_OK){
            //선택된 이미지의 uri를 가지고 돌아온 Intent객체에게 달라고함. -> 퍼미션이 필요함!
            //이미지가 외부저장소에 있을 경우를 생각해서 외부저장소에 접근할 퍼미션 필요
            Uri uri = data.getData();
            if(uri != null){
                Glide.with(this).load(uri).into(iv); //Glide야 this랑 함께 하자, uri를 불러와줘서 iv에 넣어줘.

                //선택된 이미지를 서버로 전송 - retrofit 이용.
                // 단, 서버에 전송하려면 파일의 uri가 아니라 파일의 실제 경로(절대주소)가 필요함.
                // 우선 Uri글씨 출력 -> android 에서는 uri를 알아듣지만 서버에서는 알 수 없음
//                new AlertDialog.Builder(this).setMessage(uri.toString()).show();

                //따라서  uri를 절대경로로 바꿔주어야함.
                // 절대경로 : imgPath
                imgPath = getRealPathFromUri(uri);

                // 잘 되었는지 확인
                new AlertDialog.Builder(this).setMessage(imgPath).show();


            }
        }

    }//onActivityResult method


    //Uri -- > 절대경로로 바꿔서 리턴시켜주는 메소드
    String getRealPathFromUri(Uri uri){
        String[] proj= {MediaStore.Images.Media.DATA};
        CursorLoader loader= new CursorLoader(this, uri, proj, null, null, null);
        Cursor cursor= loader.loadInBackground();
        int column_index= cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result= cursor.getString(column_index);
        cursor.close();
        return  result;
    }

    public void clickUpload(View view){
        Retrofit retrofit = RetrofitHelper.newRetrofit();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        //서버에 보낼 파일의 MultipartBody.Part 객체  생성
        File file = new File(imgPath);
        //경로에 해당하는 File객체
        //이 파일객체를 서버에 보내기위해 포장작업을 해주는 객체
        final RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);

        // ['식별자 key', '파일명', 요청객체] 를 모두 가지고있는 객체
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("img", file.getName(), requestBody);

        Call<String> call = retrofitService.uploadFile(filePart);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    String s = response.body();
                    new AlertDialog.Builder(MainActivity.this).setMessage(s).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }


}// Main
