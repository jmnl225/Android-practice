package com.jmnl2020.ex76httprequesttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class BoardActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<BoardItem> items= new ArrayList<>();
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        //BoardItem 데이터들 서버에서 불러오기
        loadDataFromServer();

        adapter= new MyAdapter(this, items);
        recyclerView = findViewById(R.id.recycler);

        recyclerView.setAdapter(adapter);


    }

    void loadDataFromServer(){

//        items.add(new BoardItem("1", "Sam", "hello good afternoon", "200617"));

        //서버의 DB를 읽어와서 echo해주는 loadDB.php 실행
        new Thread(){
            @Override
            public void run() {
                String serverUrl = "http://jmnl.dothome.co.kr/Android/loadDB.php";

                try {
                    URL url = new URL(serverUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");

                    connection.setDoInput(true);
                    connection.setUseCaches(false);

                    //서버에서 echo한 데이터를 읽어오기
                    InputStream is = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader reader = new BufferedReader(isr);

                    final StringBuffer buffer = new StringBuffer();
                    while(true){
                        String  line = reader.readLine();
                        if(line == null) break;

                        buffer.append(line+"\n");

                        Log.d("aaa", "test");

                    }

                    //읽어들인 문자열데이터에서 각 레코드를 분리
                    String data= buffer.toString();
                    String[] rows = data.split(";"); // ; (세미콜론) 글씨를 기준으로 문자열을 분리하여 배열로 리턴

                    Log.d("bbb", "test");

                    //각 줄의 column값들을 분리
                    for(int i=0; i<rows.length-1; i++){
                        String[] col= rows[i].split("&"); //& 기준으로 문자열 분리

                        String no = col[0];
                        String name = col[1];
                        String message = col[2];
                        String date = col[3];

                        final BoardItem item = new BoardItem(no, name, message, date);
                        //Recycler가 보여주는 대량의 데이터에 추가

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                items.add(0, item);
                                //adapter.notifyDataSetChanged(); 전체 새로 고침. 시간이 오래걸린다
                                adapter.notifyItemInserted(0);
                            }
                        });

                    }// for end.

                    //읽어오는 게 잘 되는지 확인(test)
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(BoardActivity.this, ""+buffer.toString(), Toast.LENGTH_SHORT).show();
//                        }
//                    });

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();

    }

}
