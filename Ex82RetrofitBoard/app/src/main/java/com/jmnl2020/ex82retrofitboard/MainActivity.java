package com.jmnl2020.ex82retrofitboard;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<BoardItem> boardItems= new ArrayList<>();
    BoardAdapter adapter;

    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView= findViewById(R.id.recycler);
        adapter= new BoardAdapter(this, boardItems);
        recyclerView.setAdapter(adapter);

        refreshLayout = findViewById(R.id.swipe_refresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //리사이클러뷰에 보여줄 데이터들을 리 로딩
                loadData();

                //리프레시 아이콘이 안 보이도록 ...
                refreshLayout.setRefreshing(true);
            }
        });

    }

    //액티비티가 화면에 보여질때.. 자동 실행되는 라이프사이클 메소드
    @Override
    protected void onResume() {
        super.onResume();

        //서버에서 데이터를 읽어오기
        loadData();

    }

    //서버에서 데이터를 불러들이는 작업메소드
    void loadData(){
        //테스트용
//        boardItems.add( new BoardItem(1, "aaa", "Title", "여기는 메세지 칸입니다.", "1000", null, 1, "2020-06-22"));
//        boardItems.add( new BoardItem(1, "aaa", "Title", "여기는 메세지 칸입니다.", "10000", null, 0, "2020-06-22"));

        //레트로핏 읽기
        Retrofit retrofit =RetrofitHelper.getInstance();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<ArrayList<BoardItem>> call = retrofitService.loadDataFromBoard();

        call.enqueue(new Callback<ArrayList<BoardItem>>() {
            @Override
            public void onResponse(Call<ArrayList<BoardItem>> call, Response<ArrayList<BoardItem>> response) {
                if(response.isSuccessful()){
                    //서버데이터를 읽어와 새로운 List객체 얻어옴
                    ArrayList<BoardItem> items = response.body();

                    // 리스트뷰에 ArrayList를 적용시키는
                    // 방법 1:
                    //리사이클러에서 보여줘야 하므로 위에서 얻어온 리스트로 새로운 아답터 객체생성
//                    adapter = new BoardAdapter(MainActivity.this, items);
//                    recyclerView.setAdapter(adapter);

                    //방법 2: 기존 아답터가 보여주던 boardItems 리스트 객체의 값들을 변경
                    boardItems.clear(); //일단 기존 리스트를 모두 삭제
                    adapter.notifyDataSetChanged();

                    //데이터가 만개 정도로 많아졌을때는 이 방법이 훨씬 빠름.

                    //서버에서 읽어온 items를 boarditems에 새로 추가시킴
                    for(BoardItem item: items){
                        boardItems.add(0, item);
                        adapter.notifyItemInserted(0); // 새로 추가될때마다 가장 처음(맨위)로 올라가도록.
                    }

                }
            }

            @Override
            public void onFailure(Call<ArrayList<BoardItem>> call, Throwable t) {

            }
        });

    }

    public void clickEdit(View view) {
        //글 작성 페이지로 넘어가는 동작
        Intent intent = new Intent(this, EditActivity.class);
        startActivity(intent);
    }
}
