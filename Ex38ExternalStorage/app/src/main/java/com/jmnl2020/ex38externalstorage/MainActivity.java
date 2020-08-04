package com.jmnl2020.ex38externalstorage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {

    EditText et;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et= findViewById(R.id.et);
        tv= findViewById(R.id.tv);

    }

    public void clickSave(View view) {
        //외장메모리 (SD카드)가 있는지 확인
        String state= Environment.getExternalStorageState();

        //외장메모리(state)가 연결(mounted) 되어있는지 확인
        if(!state.equals(Environment.MEDIA_MOUNTED)){
            Toast.makeText(this, "SDcard is not mounted", Toast.LENGTH_SHORT).show();
            return;
        }

        String data= et.getText().toString();
        et.setText("");

        //데이터를 저장할 파일의 경로 얻어오기
        File path;

        //액티비티에게 외부메모리의 앱한테 할당한 고유한 폴더경로 얻어오기
        File[] dirs= getExternalFilesDirs("MyDir"); //MyDir은 내가 만들어 ㅈ정하는 폴더
        path= dirs[0]; //첫번째 경로를 선택 안되면 1로 바꿔봅시당

        tv.setText(path.getPath()); // 위에서 선택한 경로를 출력해보기

        //위의 경로(Path)와 파일명을 결합한 File객체 생성
        File file= new File(path, "Data.txt");


        try {
            FileWriter fw= new FileWriter(file, true); // append 여부를 안 쓰면 덮어쓰기
            PrintWriter writer= new PrintWriter(fw); //보조 writer
            writer.println(data);
            writer.flush();
            writer.close();

            Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void clickLoad(View view) {
        String state= Environment.getExternalStorageState();

        if (state.equals(Environment.MEDIA_MOUNTED)||
                state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
            //읽을 수 있는 상태

            //스트림을 열고 다시 가져와야하는데 파일은 경로가 필요함
            File[] dirs= getExternalFilesDirs("MyDir");
            File path= dirs[0];

            File file= new File(path, "Data.txt");
            //경로를 받아왔으면 스트림을 읽을 Reader가 필요함

            try {
                FileReader fr= new FileReader(file);
                BufferedReader reader= new BufferedReader(fr);// FileReader가 읽어온걸 한줄씩

                StringBuffer buffer= new StringBuffer();
                while(true){
                    String line= reader.readLine();
                    if(line==null) break;
                    buffer.append(line+"\n");
                }

                tv.setText(buffer.toString());

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


    //requestPermission()를 실행해서 보여진 다이얼로그의 허가/거부를 선택했을 때
    //자동으로 실행되는 콜백메소드 존재
    //마치 startActivityForResult() 했을떄와 비ㅡㅅ.. onActivityResult


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 10:

                if (grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"저장가능",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "외부 저장 사용 불가", Toast.LENGTH_SHORT).show();
                }

                break;
        }


    }

    public void clickBtn(View view) {
        //외부 메모리에서 이 앱에 할당된 고유한 경로가 아닌 곳 경로 설정 -> 퍼미션 필요
        //퍼미션이 필요한 외부경로 저장

        String state= Environment.getExternalStorageState();
        if(!state.equals(Environment.MEDIA_MOUNTED)){
            Toast.makeText(this, "외부저장소 없음", Toast.LENGTH_SHORT).show();
            return;
        }

        //동적퍼미션 작업(앱을 실행하면 다이얼로그가 뜨면서 퍼미션 체크)
        //이 앱에서 저장소를 사용하는 것에 허가되어있ㄴ는지 체크체크
        //api 23버전(M버전 마쉬멜로우) 이상에서만 체크

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){ //핸드폰 버전이 M보다 큰가?
            int check =checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);

            //외부저장소 사용허가가 거부되었는지 확인
            if(check== PackageManager.PERMISSION_DENIED){ //허가가 거부되면 해달라고 요청!
                //퍼미션 요청 다이얼로그 메소드
                String[] permissions= new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, 10);

                return;
            };
        }

        //퍼미션이 허가된 이후에 실행되는 영역

        File path= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(path, "aaa.txt");

//        FileWriter fr= null;
        try {
            FileWriter fr = new FileWriter(file, true);
            PrintWriter writer= new PrintWriter(fr);
            writer.println( et.getText().toString());
            writer.flush();
            writer.close();

            tv.setText("저장");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
