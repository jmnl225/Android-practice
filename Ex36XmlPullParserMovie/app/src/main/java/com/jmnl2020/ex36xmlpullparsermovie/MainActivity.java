package com.jmnl2020.ex36xmlpullparsermovie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter adapter;

    ArrayList<String> items= new ArrayList<>();

    String apiKey="cf705c5fed128e4b46d2fdf1531ec5f3"; //kobi에서 발급받은 api 키 값을 대입

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView= findViewById(R.id.listview);
        adapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

    }

    public void clickBtn(View view) {
//        items.add("집에가고싶다"); 확인용
//        adapter.notifyDataSetChanged();

        //리스트뷰에 보여줄 데이터들을 네트워크를 통해 읽어와서 분석
        //인터넷을 사용하려면 반드시 Permission을 작성해야함!
        //네트워크작업은 반드시 별도의 Thread가 해야함

        Thread t= new Thread(){
            @Override
            public void run() {

                Date date= new Date(); //오늘날짜
                date.setTime( date.getTime()-(1000*60*60*24) ); //오늘로부터 1일 전
                SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd"); //날짜를 지정한 패턴으로 만들어줌


                String dateStr= sdf.format(date);

                //영화진흥위원회의 서버에서 일일 박스오피스 정보, xml 문서를 읽어올 것
                String address="http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml?"
                +"key=" +apiKey
                +"&targetDt="+ dateStr;

                ///////////////////////////////////////////////////////////////////////

                // api 28버전부터 인터넷주소가 https 가 아닌 http 면 동작이 안 됨.
                // 만약 되게하려면 Manifest에 추가작업을 해야함.
                // manifest에 android:usesCleartextTraffic="true" 추가.

                ///////////////////////////////////////////////////////////////////////


                //완성된 네트워크 주소와 연결하여 데이터 읽어오기
                //무지개로드(String)를 만들어주는 해임달(URL) 객체생성

                try {
                    URL url= new URL(address);

                    //URL에게 무지개로드(InputStream) 만들어줘!
                    InputStream is= url.openStream(); // 바이트스트림이라 1바이트씩 읽어옴

                    InputStreamReader isr= new InputStreamReader(is); // 문자스트림으로 바꿈

                    //XML문서를 isr로부터 받아와서 분석해주는 분서가 객체
                    XmlPullParser xpp;
                    //분석가 객체를 만들어주는 공장 세움
                    XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
                    xpp= factory.newPullParser(); //분석가 객체를 만들어냄!
                    xpp.setInput(isr); // 분석가 객체가 이미 첫 줄에 커서를 댐

                    /////////////////////////////////////////////////////////////////

                    int eventType =xpp.getEventType();
                    StringBuffer buffer= new StringBuffer(); // 문자열 후보들을 모아놓음 도토리 하나 둘 ,,

                    while(eventType != XmlPullParser.END_DOCUMENT){

                        switch (eventType){
                            case XmlPullParser.START_DOCUMENT:

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "분석 시작", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                break;

                            case XmlPullParser.START_TAG:
                                String tagName= xpp.getName();

                                if(tagName.equals("dailyBoxOffice")){
                                    buffer= new StringBuffer();
                                }else if (tagName.equals("rank")){
                                    buffer.append("순위: ");
                                    xpp.next();
                                    buffer.append(xpp.getText()+"\n");

                                }else if (tagName.equals("movieNm")){
                                    buffer.append("영화제목: ");
                                    xpp.next();
                                    buffer.append(xpp.getText()+"\n");

                                }else if(tagName.equals("openDt")){
                                    buffer.append("개봉일: ");
                                    xpp.next();
                                    buffer.append(xpp.getText()+"\n");

                                }else if (tagName.equals("audiCnt")){
                                    buffer.append("일 관객수: ");
                                    xpp.next();
                                    buffer.append(xpp.getText()+"\n");

                                }else if (tagName.equals("audiAcc")){
                                    buffer.append("누적 관객수: ");
                                    xpp.next();
                                    buffer.append(xpp.getText()+"\n");

                                }

                                break;

                            case XmlPullParser.TEXT:
                                break;

                            case XmlPullParser.END_TAG:
                                String tagName2= xpp.getName();
                                if( tagName2.equals("dailyBoxOffice")) {
                                    //지금까지 누적된 StringBuffer를 String으로 변환
                                    String s= buffer.toString();
                                    items.add(s);
                                    //리스트뷰 갱신 (화면변화는 반드시 UI Thread만)
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            adapter.notifyDataSetChanged();
                                        }
                                    });
                                }
                                break;
                        }// switch end.


                        eventType= xpp.next();

                    }// while end.

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "분석 완료", Toast.LENGTH_SHORT).show();
                        }
                    });

            } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }

            }
        };
        t.start();

    }//clickBtn end.
}
