package com.jmnl2020.ex17search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //After onCreate method, to make OptionView
    //callback method


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.actionbar, menu);
        //Inflate작업을 통해 서치뷰가 생성!
        //그 메뉴아이템객체에게 SearchView를 참조하기위해 얻어오기.
        MenuItem item= menu.findItem(R.id.menu_search);
        searchView = (SearchView)item.getActionView();

        //서치뷰의 힌트글씨 변경
        searchView.setQueryHint("뭔갈 쓰세요");


        //소프트 Key패드의 검색버튼을 클릭하는 것을 듣는 리스너객체
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast
                return false;
            }

            //서치뷰의 EditText 글씨를 변경할때마다 실생더;러
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
