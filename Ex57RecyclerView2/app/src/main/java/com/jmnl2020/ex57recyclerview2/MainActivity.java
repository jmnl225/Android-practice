package com.jmnl2020.ex57recyclerview2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //대량의 데이터
    ArrayList<Item> items = new ArrayList<>();

    MyAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //대량의 데이터 추가: 실무에서는 DB나 서버에서 데이터를 가져옴

        items.add( new Item("루피", "해적단 선장", R.drawable.one_chopa, "https://img.insight.co.kr/static/2020/05/20/700/1ydhlv9685uuh597oneq.jpg"));
        items.add( new Item("조로", "해적단 검사", R.drawable.one_3, "https://i.pinimg.com/originals/59/32/f6/5932f6f30d416ef4eec742d9fb6a072d.jpg"));
        items.add( new Item("나미", "해적단 항해사", R.drawable.one_nami5, "https://img.insight.co.kr/static/2020/05/20/700/1ydhlv9685uuh597oneq.jpg"));
        items.add( new Item("우솝", "해적단 저격수", R.drawable.one_chopa, "https://i.pinimg.com/originals/59/32/f6/5932f6f30d416ef4eec742d9fb6a072d.jpg"));
        items.add( new Item("상디", "해적단 요리사", R.drawable.one_chopa, "https://img.insight.co.kr/static/2020/05/20/700/1ydhlv9685uuh597oneq.jpg"));
        items.add( new Item("쵸파", "해적단 의사", R.drawable.one_chopa, "https://i.pinimg.com/originals/59/32/f6/5932f6f30d416ef4eec742d9fb6a072d.jpg"));
        items.add( new Item("니코로빈", "해적단 한량", R.drawable.one_chopa, "https://img.insight.co.kr/static/2020/05/20/700/1ydhlv9685uuh597oneq.jpg"));
        items.add( new Item("원피스", "해적단 목수", R.drawable.one_chopa, "https://i.pinimg.com/originals/59/32/f6/5932f6f30d416ef4eec742d9fb6a072d.jpg"));

        adapter = new MyAdapter(this,items);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setAdapter(adapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.aa:// Linear
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
                        LinearLayoutManager.VERTICAL, false);
                recyclerView.setLayoutManager(layoutManager);
                break;

            case R.id.bb:
                RecyclerView.LayoutManager layoutManager1 = new GridLayoutManager(this, 2);
                recyclerView.setLayoutManager (layoutManager1);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
