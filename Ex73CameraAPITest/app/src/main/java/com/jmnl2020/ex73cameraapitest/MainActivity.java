package com.jmnl2020.ex73cameraapitest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    MyCameraView cv;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cv = findViewById(R.id.cv);
        iv = findViewById(R.id.iv);


        //동적퍼미션 2개 적용
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            int permissionResult = checkSelfPermission(Manifest.permission.CAMERA);
            if(permissionResult == PackageManager.PERMISSION_DENIED){
                String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, 10);
            }
        }

    }//onCreate end

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 10:
                if(grantResults[0]== PackageManager.PERMISSION_DENIED || grantResults[1]==PackageManager.PERMISSION_DENIED){
                    Toast.makeText(this,"카메라 사용불가", Toast.LENGTH_SHORT).show();
                }
                break;

        }

    }

    public void clickBtn(View view) {

        cv.camera.takePicture(null, null, new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {

                //이미지 정보를 byte배열로 전달함.
                Bitmap bm= BitmapFactory.decodeByteArray(data, 0, databaseList().length);
                iv.setImageBitmap(bm);
                iv.setRotation(90);

                //카메라 하드웨어를 직접 사용하면 캡쳐된 이미지가 저장되어있지 않음
                //직접 저장 해주기
                //외부 메모리에 저장이 일반적.
                File path = Environment.getExternalStorageDirectory();// 퍼미션이 필요!

                //파일명을 날짜를 이용하여 만들기
                SimpleDateFormat sdf= new SimpleDateFormat("yyyy,MM,dd,mm");
                String filename= "IMG_"+sdf.format(new Date())+"jpg";

                //파일명과 경로 결합
                File file = new File(path, filename);

                //파일경로에 데이터 저장하기!! (외부메모리 저장 예제 참고)
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(data);
                    fos.flush();
                    fos.close();

                    Toast.makeText(MainActivity.this, "saved", Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });



    }
}
