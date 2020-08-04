package com.jmnl2020.ex82retrofitboard;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public class EditActivity extends AppCompatActivity {

    EditText etName;
    EditText etTitle;
    EditText etMsg;
    EditText etPrice;
    ImageView iv;

    String imgPath; // 선택된 이미지의 절대경로

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etName = findViewById(R.id.et_name);
        etTitle = findViewById(R.id.et_title);
        etMsg = findViewById(R.id.et_msg);
        etPrice = findViewById(R.id.et_price);
        iv = findViewById(R.id.iv);

        //외부저장소의 접근 동적퍼미션
        String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkSelfPermission(permissions[0]) == PackageManager.PERMISSION_DENIED){
                requestPermissions(permissions, 100);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_DENIED){
            Toast.makeText(this, "사진파일 전송이 불가능합니다.", Toast.LENGTH_SHORT).show();
        }

    }

    public void clickSelectImage(View view) {
        //업로드할 사진 선택
        Intent intent= new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 10);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 10 && resultCode == RESULT_OK){
            Uri uri = data.getData();
            if(uri != null){
                Glide.with(this).load(uri).into(iv);

                //이미지 uri를 서버에 전송하려면 실제 경로 주소가 필요.
                // uri를 절대경로로 변경
                // 변경해주는 코드를 복붙 ㅎ

                imgPath = getRealPathFromUri(uri);

                //잘 되는지 확인
                new AlertDialog.Builder(this).setMessage(imgPath).show();

            }
        }

    }

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

    public void clickComplete(View view) {
        //서버에 전송할 데이터들 [name , title , msg, price, imgpath]
        String name= etName.getText().toString();
        String title = etTitle.getText().toString();
        String msg = etMsg.getText().toString();
        String price = etPrice.getText().toString();

        //레트로핏 라이브러리로 데이터를 전송
        Retrofit retrofit= RetrofitHelper.getInstance2(); //String echo 응답받기


        //추상메소드를 만들어주고 옴
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        //데이터들
        Map<String, String> dataPart= new HashMap<>();
        dataPart.put("name", name);
        dataPart.put("title", title);
        dataPart.put("msg", msg);
        dataPart.put("price", price);

        Log.i("test", "여기까지 확인");

        //업로드할 파일
        MultipartBody.Part filePart = null;
        if(imgPath!=null){
            File file = new File(imgPath);
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);

            //['식별자' , '파일명' , 요청객체]를 가진 객체
            filePart = MultipartBody.Part.createFormData("img", file.getName(), requestBody);
        }

        Call<String> call= retrofitService.postDataToBoard(dataPart, filePart);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    String s = response.body();
                    AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);
                    builder.setMessage(s+"").show();


                    //액티비티 종료
                    finish();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Snackbar.make(getWindow().getDecorView().getRootView(), t.getMessage(), Snackbar.LENGTH_LONG).show();
            }
        });

    }


}
