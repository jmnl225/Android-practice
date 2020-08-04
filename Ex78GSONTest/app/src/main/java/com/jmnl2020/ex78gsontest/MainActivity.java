package com.jmnl2020.ex78gsontest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    ListView listView;
    ArrayAdapter adapter;
    List<Person> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv= findViewById(R.id.tv);
        listView= findViewById(R.id.listview);

    }

    public void clickBtn(View view) {
        //GSON Library를 이용하여 json문자열을 Person객체로 곧바로 파싱

        String jsonStr= "{'name': 'robin', 'age':25}";

        Gson gson = new Gson();
        Person p = gson.fromJson(jsonStr, Person.class); //json 문자열 -> Person 객체
        tv.setText(p.name+" , "+p.age);

    }

    public void clickBtn2(View view) {
        Person p = new Person("robin", 23);

        Gson gson = new Gson();
        String jsonStr= gson.toJson(p);
        tv.setText(jsonStr);

    }

    public void clickBtn3(View view) {
//        //json 배열도 손쉽게 가능
//        String str = "[{'name':'hong', 'age':20}, {'name':'kim', 'age':25}]";
//
//        Gson gson = new Gson();
//        Person[] personArr = gson.fromJson(str, Person[].class);
//
//        for(Person p : personArr){
//            items.add(p.name+", "+p.age);
//        }
//
//        //배열->리스트
//        items= Arrays.asList(personArr);
//
//        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);

    }


    public void clickBtn4(View view) {

        final String serverUrl="http://jmnl.dothome.co.kr/test.json";
        //지역변수는 익명클래스에서 알아듣지 못하므로 final

        new Thread(){
            @Override
            public void run() {


                try {
                    URL url = new URL(serverUrl);

                    HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setDoInput(true);
                    connection.setUseCaches(false);

                    InputStream is = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);

                    //Reader까지만 있으면 GSON이 알아서 읽어와 객체로 파싱
                    Gson gson = new Gson();
                    final Person p = gson.fromJson(isr, Person.class);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv.setText(p.name+", "+p.age);
                        }
                    });


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();



    }
}
