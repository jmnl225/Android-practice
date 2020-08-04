package com.jmnl2020.ex69galleryimageselecttest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    ImageView iv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = findViewById(R.id.iv);

    }

    public void clickFAB(View view) {

        //Gallery App 실행하는 인텐트

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*"); // image / video / 등 자료타입 선택

        //선택된 사진 결과물을 가져와야하기 때문에 intent에게 돌아오도록 명령
        startActivityForResult(intent, 10);



    }

    //startActivityForResult()로 실행한 인텐트가 돌아오면
    //자동으로 실행되는 메소드


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 10:
                //이미지를 선택했다면..?
                if(resultCode != RESULT_CANCELED ){ //intent가 이미지를 가져오지 않은 경우도 있으니 캔슬한 경우를 제외
                    //돌아온 인텐트 객체에게 선택된 이미지의 uri를 달라고 요구
                    Uri uri = data.getData();
                    if(uri == null){
                        Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
                    }else Toast.makeText(this, uri.toString(), Toast.LENGTH_SHORT).show();

                    Glide.with(this).load(uri).into(iv);


                }
                break;

        }

    }
}
