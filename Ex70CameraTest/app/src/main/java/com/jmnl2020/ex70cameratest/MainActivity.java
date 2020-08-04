package com.jmnl2020.ex70cameratest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
        //카메라 앱을 실행하는 인텐트 생성
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //결과를 받아오도록 intent를 시작시킬떄 돌아오게 하는 명령어
        startActivityForResult(intent, 20);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){

            case 20:
                //카메라 앱에서 캡쳐한 결과를ㄹ 가져왔나?
                if(requestCode == RESULT_OK){

                    //디바이스마다 프로그래밍으로 실행한 카메라앱은 Device마다 사진 캡쳐 후 저장방식이 다름!!
                    // M버전 이후부터 대부분의 Device에서 intent로 실행한 카메라앱으로 찍은 사진을 Device에 저장하지 않음.
                    // 저장하지 않고 사진의 Bitmap객체를 만들어 전달함.
                    // 즉 , 사진의 경로에 해당하는 uri가 없을수도 있음!!!!!

                    //인텐트객체에게 Uri를 가져왔는지 알아봐야함!
                    Uri uri = data.getData();

                    if(uri != null){ // 사진이 파일로 저장되는 방식의 Device

                        Toast.makeText(this, "Uri로 저장", Toast.LENGTH_SHORT).show();
                        Glide.with(this).load(uri).into(iv);

                    }else { // 사진이 Bitmap으로 전달되는 방식의 Device
                        Toast.makeText(this, "저장없이 비트맵으로", Toast.LENGTH_SHORT).show();

                        //Bitmap 객체를 intent의 extra 데이터로 전달해줌
                        Bundle bundle = data.getExtras();

                        Bitmap bm = (Bitmap) bundle.get("data");
                        // *** data는 정해진 키워드 *** 비트맵 이미지를 내놓으라고 명령.
                        // get 내놔! 그걸 Bitmap으로 받았는데 bundle이 Bitmap인지 몰라서 에러.
                        // 따라서 형변환.

                        Glide.with(this).load(bm).into(iv);

                        //Bitmap으로 왔다는 뜻은 사진이 디바이스에 저장 X
                        // 만약 저장하고싶다면 꽤 복잡한 코드작업이 추가 -> Ex71
                        // Bitmap으로 전달된 데이터는 해상도가 낮은 섬네일 이미지. 파일 저장을 해야 full-size 이미지가 전달됨.

                    }

                }
            break;
        }

    }
}
