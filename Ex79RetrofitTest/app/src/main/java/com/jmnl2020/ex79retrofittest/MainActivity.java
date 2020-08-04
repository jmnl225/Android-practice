package com.jmnl2020.ex79retrofittest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv= findViewById(R.id.tv);
    }

    public void clickBtn(View view) {
        // 네트워크에서 읽어들인 json을 곧바로 객체로 생성

        //Restrofit2 라이브러리로 HTTP통신작업 시작!

        // 1. retrofit 객체 생성 및 기본설정
        Retrofit.Builder builder= new Retrofit.Builder();
        builder.baseUrl("http://jmnl.dothome.co.kr"); // 데이터를 제공해주는 서버의 기본 주소를 지정
        builder.addConverterFactory(GsonConverterFactory.create()); //retrofit이 읽어온 data를 json을 이용해서 parse 하기 위한 설정.
        Retrofit retrofit = builder.build();

        // 2. RetrofitService 인터페이스Interface(추상메소드를 가진 클래스) 설계
        //원하는 기능의 추상메소드를 설계 : getBoardJson() 추상메소드

        //3. RetrofitService 인터페이스를 객체로 생성
        RetrofitService retrofitService= retrofit.create(RetrofitService.class);
         //서비스객체의 추상메소드의 기능들을 알아서 retrofit 이 만들어냄

        //4. 서비스객체의 원하는 기능메소드 호출, 실행해서 Call객체를 얻어오기.
        Call<BoardItem> call = retrofitService.getBoardJson();

        //5. 원하는 기능으로 network작업을 수행하도록 call객체를 큐에 삽입
        //que = 줄을 서서 순서대로 먼저 들어온 사람이 먼저 실행되도록 (네트워크를 하기 전에 줄 서있기) eg) 비행기 항공대기 활주로.
        //하지만 누가 먼저 돌아오는지는 알 수 없음. 따라서 돌아오자마자 바로 실행하는 콜백메소드 설정
        call.enqueue(new Callback<BoardItem>() {
            @Override
            public void onResponse(Call<BoardItem> call, Response<BoardItem> response) {
                Toast.makeText(MainActivity.this, "응답 받았음!", Toast.LENGTH_SHORT).show();

                if(response.isSuccessful()){
                    //응답객체로부터 Gson라이브러리에 의해 자동으로 BoardItem으로 파싱되어있는
                    // json문자열의 데이터값 body 얻어오기
                    BoardItem item= response.body();
                    tv.setText(item.name+", "+ item.msg );

                    //retrofit이 Thread를 자체적으로 만들어서 반환해줌.. 와우

                }

            }

            @Override
            public void onFailure(Call<BoardItem> call, Throwable t) {
                Toast.makeText(MainActivity.this, "응답 못 받음!", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void clickBtn2(View view) {

        // 1. retrofit 객체 생성 및 옵션 설정
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://jmnl.dothome.co.kr");
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        // 2. RetrofitService 인터페이스에 원하는 기능 추상메소드 설계
        // getBoardJsonByPath()

        // 3. RetrofitService 객체 생성
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        // 4. 원하는 기능 메소드를 호출하여 네트워크작업을 하는 객체를 리턴
        Call<BoardItem> call = retrofitService.getBoardJsonByPath("Retrofit");

        // 5. 실제 네트워크 작업 실행
        call.enqueue(new Callback<BoardItem>() {
            @Override
            public void onResponse(Call<BoardItem> call, Response<BoardItem> response) {
                Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()){
                    BoardItem item = response.body();
                    tv.setText(item.name+", "+item.msg);
                }
            }

            @Override
            public void onFailure(Call<BoardItem> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Nooooooo", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void clickBtn3(View view) {
        //1.
        Retrofit retrofit= RetrofitHelper.getRetrofitInstance();

        //2. getMethodTest()

        //3.
        RetrofitService retrofitService= retrofit.create(RetrofitService.class);

        //4.
        Call<BoardItem> call = retrofitService.getMethodTest("robin", "Nice to meet you.");

        //5.
        call.enqueue(new Callback<BoardItem>() {
            @Override
            public void onResponse(Call<BoardItem> call, Response<BoardItem> response) {
                if(response.isSuccessful()){
                    BoardItem item = response.body();
                    tv.setText(item.name+", "+item.msg);
                }
            }

            @Override
            public void onFailure(Call<BoardItem> call, Throwable t) {

            }
        });


    }

    public void clickBtn4(View view) {
        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<BoardItem> call = retrofitService.getMethodTest2("getTest.php", "hong", "Good morning");

        call.enqueue(new Callback<BoardItem>() {
            @Override
            public void onResponse(Call<BoardItem> call, Response<BoardItem> response) {
                if(response.isSuccessful()){
                    BoardItem item = response.body();
                    tv.setText(item.name+" , "+item.msg);
                }
            }

            @Override
            public void onFailure(Call<BoardItem> call, Throwable t) {

            }
        });

    }

    public void clickBtn5(View view) {
        Retrofit retrofit = RetrofitHelper.getRetrofitInstance();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        //서버에전달할 데이터들을 Map Collection에 저장
        Map<String, String> datas = new HashMap<>();
        //add는 set이나 list 방식에서 사용
        datas.put("name", "park");
        datas.put("msg", "Thanks God it's Friday!");

        Call<BoardItem> call = retrofitService.getMethodTest3(datas);
        call.enqueue(new Callback<BoardItem>() {
            @Override
            public void onResponse(Call<BoardItem> call, Response<BoardItem> response) {
                if(response.isSuccessful()){
                    BoardItem item= response.body();
                    tv.setText(item.name+" , "+item.msg);
                }
            }

            @Override
            public void onFailure(Call<BoardItem> call, Throwable t) {

            }
        });

    }

    public void clickBtn6(View view) {
        RetrofitService retrofitService= RetrofitHelper.getRetrofitInstance().create(RetrofitService.class);

        //보낼 데이터를 가진 객체
        BoardItem boardItem = new BoardItem("lee", "have a lovely day");
        Call<BoardItem> call = retrofitService.postMethodTest(boardItem);
        call.enqueue(new Callback<BoardItem>() {
            @Override
            public void onResponse(Call<BoardItem> call, Response<BoardItem> response) {
                if(response.isSuccessful()){
                    BoardItem item = response.body();
                    tv.setText(item.name+", "+item.msg);
                }
            }

            @Override
            public void onFailure(Call<BoardItem> call, Throwable t) {

            }
        });

    }


    public void clickBtn7(View view) {
        RetrofitService retrofitService = RetrofitHelper.getRetrofitInstance().create(RetrofitService.class);
        Call<BoardItem> call = retrofitService.postMethod2("Juliette", "Good night");
        call.enqueue(new Callback<BoardItem>() {
            @Override
            public void onResponse(Call<BoardItem> call, Response<BoardItem> response) {
                if(response.isSuccessful()){
                    BoardItem item = response.body();
                    tv.setText(item.name+", "+item.msg);
                }
            }

            @Override
            public void onFailure(Call<BoardItem> call, Throwable t) {

            }
        });

    }

    public void clickBtn8(View view) {
        RetrofitService retrofitService = RetrofitHelper.getRetrofitInstance().create(RetrofitService.class);
        Call<ArrayList<BoardItem>> call = retrofitService.getBoardArray();
        call.enqueue(new Callback<ArrayList<BoardItem>>() {
            @Override
            public void onResponse(Call<ArrayList<BoardItem>> call, Response<ArrayList<BoardItem>> response) {
                if(response.isSuccessful()){
                    ArrayList<BoardItem> items =response.body();
                    //tv.setText(items.size()+"");
                    for(BoardItem item : items){
                        tv.append("\n"+item.name+", "+item.msg);
                    }

                }
            }

            @Override
            public void onFailure(Call<ArrayList<BoardItem>> call, Throwable t) {

            }
        });
    }

    public void clickBtn9(View view) {
        //URL TEST 지정된 주소로 가기
        RetrofitService retrofitService = RetrofitHelper.getRetrofitInstance().create(RetrofitService.class);
        Call<BoardItem> call = retrofitService.urlTest("http://jmnl.dothome.co.kr/Retrofit/board.json");
        call.enqueue(new Callback<BoardItem>() {
            @Override
            public void onResponse(Call<BoardItem> call, Response<BoardItem> response) {
                BoardItem item = response.body();
                tv.setText(item.name);
            }

            @Override
            public void onFailure(Call<BoardItem> call, Throwable t) {

            }
        });
    }


    public void clickBtn10(View view) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://jmnl.dothome.co.kr");
        //결과를 String으로 받으려면 Gson converter를 사용하면 안 됨.
        //ScalarsConverter 사용 [converter-scalars:2.9.0 추가] **converter는 한 파일에 하나씩만. 같이 사용하고 싶을 때는 메소드를 두개 만들기
        builder.addConverterFactory(ScalarsConverterFactory.create());

        RetrofitService retrofitService = RetrofitHelper.getRetrofitInstance().create(RetrofitService.class);
        Call<String> call = retrofitService.getJsonString();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String s = response.body();
                tv.setText(s);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }
}
