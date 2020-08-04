package com.jmnl2020.ex76httprequesttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ThemedSpinnerAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    EditText etName, etMsg;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName= findViewById(R.id.et_name);
        etMsg = findViewById(R.id.et_msg);
        tv= findViewById(R.id.tv);

    }

    public void clickGet(View view) {
        //별도의 thread가 네트워크 작업
        new Thread(){
            @Override
            public void run() {
                //보낼 데이터 얻어오기.
                String name = etName.getText().toString();
                String msg = etMsg.getText().toString();

                //GET방식으로 데이터를 보낼 서버의 주소
                String serverUrl="http://jmnl.dothome.co.kr/Android/getTest.php";

                //GET방식은 보낼 데이터를  URL뒤에 ? 를 덧붙여서 보내는 방식

                //단, 한글은 URL에 사용이 불가능함 따라서 한글을 utf-8방식으로 인코딩을 하고 써야함!
                try {
                    name = URLEncoder.encode(name, "utf-8");
                    //encod 해서 바뀐 결과값이 name변수 안으로 들어감.
                    msg= URLEncoder.encode(msg, "utf-8");

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                //결국 DATA를 포함한 최종 요청 URL
                String getUrl= serverUrl+"?name="+name+"&msg="+msg;

                //getUrl 주소의 서버와 연결하는 무지개로드를 만들어주는 해임달
                try {
                    URL url = new URL(getUrl);

                    //URL은 inputStream만 열 수 있음.
                    //Input, Output 모두 열 수 있는 URL의 조수 객체를 사용.
                    HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setUseCaches(false);

                    //보낼 데이터가 있다면 여기서 OutputStream을 만들어서 써야함
                    //하지만 GET방식은 이미 URL에 보낼 데이터가 전달되었기에 별도의 write작업 불필요
                    //즉, GET방식은 서버와 연결하는 순간(new URL())에 이미 데이터를 보냄
                    OutputStream os = connection.getOutputStream();
                    //os.write();
                    //os.flush();

                    //getTest.php가 echo해준 문자열을 읽어오기 위해 InputStream 필요
                    InputStream is = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader reader = new BufferedReader(isr);

                    final StringBuffer buffer = new StringBuffer();
                    while(true){
                        String line = reader.readLine();
                        if (line == null) break;

                        buffer.append(line+"\n");

                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv.setText(buffer.toString());
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

    public void clickPost(View view) {

        new Thread(){
            @Override
            public void run() {

                String name= etName.getText().toString();
                String msg= etMsg.getText().toString();

                //서버주소
                String serverUrl="http://jmnl.dothome.co.kr/Android/postTest.php";

                //POST방식 이므로 Output Stream을 만들어서 데이터를 write해야함

                try {
                    URL url= new URL(serverUrl);

                    HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setUseCaches(false);

                    //보낼 데이터
                    String query= "name="+name+"&msg="+msg;

                    OutputStream os= connection.getOutputStream();
                    OutputStreamWriter writer= new OutputStreamWriter(os);

                    writer.write(query, 0, query.length());
                    writer.flush();
                    writer.close();

                    //postTest.php에서 echo된 결과 데이터를 받아오기
                    InputStream is = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader reader = new BufferedReader(isr);

                    final StringBuffer buffer = new StringBuffer();
                    while(true){
                        String line = reader.readLine();
                        if(line == null)break;

                        buffer.append(line+"\n");

                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv.setText(buffer.toString());
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

    public void clickUpload(View view) {
        //서버의 DB에 저장하기
        new Thread(){
            @Override
            public void run() {

                String name= etName.getText().toString();
                String msg = etMsg.getText().toString();

                String serverUrl = "http://jmnl.dothome.co.kr/Android/insertDB.php";

                try {
                    URL url = new URL(serverUrl);

                    HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setUseCaches(false);

                    String query= "name="+name+"&msg="+msg;

                    OutputStream os= connection.getOutputStream();
                    os.write(query.getBytes()); //String을 byte[]로 바꿔서 받음.
                    os.flush();
                    os.close();

                    InputStream is = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader reader = new BufferedReader(isr);

                    final StringBuffer buffer= new StringBuffer();
                    while(true) {
                        String line = reader.readLine();
                        if (line == null) break;


                        buffer.append(line + "\n");
                    }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tv.setText(buffer.toString());
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

    public void clickLoad(View view) {
        //서버의 DB의 정보를 읽어와서 보여주는 화면(Activity) 실행

        Intent intent = new Intent(this, BoardActivity.class);
        startActivity(intent);

    }
}
