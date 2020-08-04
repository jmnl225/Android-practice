package com.jmnl2020.ex72camerateestvideo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView vv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vv = findViewById(R.id.vv);

        //동적퍼미션
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            if( checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
            }
        }

    }// onCreate

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 200:
                if(grantResults[0]== PackageManager.PERMISSION_DENIED)
                    Toast.makeText(this, "앱 사용 불가", Toast.LENGTH_SHORT).show();
                //finish();
        }

    }

    public void clickBtn(View view) {
        //비디오 촬영이 되는 화면 카메라앱 실행

        Intent intent= new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        startActivityForResult(intent, 50);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 비디오는 용량때문에 무조건 파일 저장방식.
        // 즉, 무조건 URI로 캡쳐된 동영상의 경로가 온다.

        switch (requestCode){
            case 50:
                if(resultCode == RESULT_OK){
                    Uri uri = data.getData();
                    vv.setVideoURI(uri);
                    // vv.start();

                    //비디오 뷰를 클릭했을 때, 아래쪽에 컨트롤바가 올라오도록
                    MediaController mediaController = new MediaController(this);
                    mediaController.setAnchorView(vv);

                    vv.setMediaController(mediaController);

                    // 동영상은 로딩에 시간이 소요되므로,
                    // 로딩이 완료되는 것을 듣고 시작하는 것을 권장.

                    // 따라서 퍼미션을 manifest에 받아두어야함.

                    vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            vv.start();
                        }
                    });

                }else {
                    Toast.makeText(this, "비디오 촬영 취소", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
}
