package com.jmnl2020.ex37internalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
        //파일에 저장할 데이터를 EditText에서 얻어오기
        String data= et.getText().toString();
        et.setText(""); //et의 글씨 제거

        //액티비티 내부메모리(Internal)에 file을 저장할 ㅅ ㅜ있도록 스트림 생성
        //OutputStream을 열 수 있는 기능 메소드
        try {
            FileOutputStream fos= openFileOutput("data.txt", MODE_APPEND);
            PrintWriter writer= new PrintWriter(fos); // 바이트 스트림을 문자 스트림으로 바꿔줌
            writer.println(data);
            writer.flush();
            writer.close();

            Toast.makeText(this,"saved",Toast.LENGTH_SHORT).show();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void clickLoad(View view) {

        try {
            FileInputStream fis= openFileInput("data.txt");
            InputStreamReader isr= new InputStreamReader(fis);
            BufferedReader reader= new BufferedReader(isr);

            StringBuffer buffer= new StringBuffer();
            while(true) {
                String line= reader.readLine();
                if(line==null) break;
                buffer.append(line+ "\n");
            }

            tv.setText( buffer.toString() );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void clickClear(View view) {

    }
}
