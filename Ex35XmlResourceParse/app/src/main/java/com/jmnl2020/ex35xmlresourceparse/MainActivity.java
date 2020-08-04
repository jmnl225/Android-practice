package com.jmnl2020.ex35xmlresourceparse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listview;
    ArrayAdapter adapter;

    ArrayList<String> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //리스트뷰가 잘 되는지 items에 아이템 추가
        items.add( "1. hello \n2. 안녕\n3. ㅎㅎㅎ");

        listview= findViewById(R.id.listview);
        adapter= new ArrayAdapter(this, R.layout.listview_item, items);

        listview.setAdapter(adapter);

    }

    public void clickBtn(View view) {

//        items.add("ccc");
//        adapter.notifyDataSetChanged();

        // 경로 res/xml/movies.xml을 읽어와서 분석하여(Parse) ListView의 데이터(item)에 추가

        //res 창고를 관리하는 창고관리자 객체소환
        Resources res= getResources();

        // res폴더안의 xml문서를 읽어와서 분석해주는 분석가 객체 생성
        XmlResourceParser xrp= res.getXml(R.xml.movies);
        //분석가 객체 xrp를 통해 xml문서를 해독, 분석하기!

        try {
            xrp.next(); //분석을 위한 문서의 커서 이동
            int eventType =xrp.getEventType();

            String item=""; //빈 문자열객체 생성

            //문서의 끝이 아닐때 반복
            while ( eventType != XmlResourceParser.END_DOCUMENT){

               eventType= xrp.next(); //커서를 옮기면서 바로 이벤트타입을 리턴

                switch (eventType){
                    case XmlResourceParser.START_DOCUMENT:
                        Toast.makeText(this,"Start Document", Toast.LENGTH_SHORT).show();
                        break;

                    case  XmlResourceParser.START_TAG:
                        //시작 태그문에 적힌 글씨 얻어오기
                        String name= xrp.getName();
                        if(name.equals("item")){
                            item="";// 영화 1개의 정보 시작문자열

                        }else if( name.equals("no")){
                            item = item + "번호: ";

                        }else if( name.equals("title")){
                            item = item + "제목: ";

                        }else if( name.equals("genre")){
                            item = item + "장르: ";

                        }
                        break;

                    case  XmlResourceParser.TEXT:
                        //텍스트 글씨 얻어오기
                        String text= xrp.getText();
                        item = item + text;
                        break;

                    case XmlResourceParser.END_TAG:
                        //엔드 태그일때 줄 바꾸기. 하지만 상황 변수가 많으니 지정
                        String name2= xrp.getName();


                        if (name2.equals("no")){
                            item= item+"\n";
                        }else if(name2.equals("title")){
                            item += "\n";
                        }else if(name2.equals("item")){
                            //하나의 영화 아이템이 끝났으므로 대량의 데이터 (liveview arraylist) 에 추가
                            items.add(item);
                            adapter.notifyDataSetChanged();
                        }

                        break;

                }

            }// while end.

            Toast.makeText(this,"Parsing Finish", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

//        xrp.next();
//        int eventType= xrp.getEventType();

    }
}
