package com.jmnl2020.ex89youtubeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class YouTubeDataActivity extends AppCompatActivity {

    EditText et;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube_data);

        et= findViewById(R.id.et);
        tv= findViewById(R.id.tv);


    }

    public void clickSearch(View view) {

        //Youtube Data API 사용
        //검색기능 API는 REST방식으로 데이터를 제공
        // GET https://www.googleapis.com/youtube/v3/search
        // 요청 파라미터 : key(필수), part(필수), q(검색어 지정) , maxResults(결과 개수 지정: 0-50 / default: 5)

        String key ="AIzaSyD1LDQzQgfIkgOlr3LrPCSK2av0dQzrKvY";
        String part = "snippet";
        String query = et.getText().toString();
        int maxResults = 10;

        // Retrofit - 결과를 우선은 String으로 받기
        Retrofit retrofit = RetrofitHelper.getInstance();
        RetrofitService retrofitService= retrofit.create(RetrofitService.class);

        Call<String> call = retrofitService.searchVieos(key, part, query, maxResults);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String data = response.body();
                tv.setText(data);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }
}
