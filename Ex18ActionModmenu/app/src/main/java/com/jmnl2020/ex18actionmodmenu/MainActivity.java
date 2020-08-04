package com.jmnl2020.ex18actionmodmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void clickBtn(View view) {
        //액션모드메뉴 시작
        //액션모드가 처음 호출될때 한 번 만들어지는 메소드
        startActionMode(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                getMenuInflater().inflate(R.menu.actionmode, menu);

                //액션모드에 추가로 줄 수 있는 설정들
                mode.setTitle("action mode");
                mode.setSubtitle("This is the action mode");

                //액션모드의 배경색은 style.xml에서만 지정가능

                return true; // return value has to be true!! action mode start.
            }

            //액션모드메뉴가 시작될 때마다 발동하는 메소드
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) { return false; }

            //액션모드메뉴아이템을 클릭했을 때 발동하는 메소드
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

                switch (item.getItemId()){
                    case R.id.menu_aa:
                        Toast.makeText(MainActivity.this, "aa", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_bb:
                        Toast.makeText(MainActivity.this, "bb", Toast.LENGTH_SHORT).show();
                        break;
                    case  R.id.menu_cc:
                        Toast.makeText(MainActivity.this, "cc", Toast.LENGTH_SHORT).show();
                        break;

                }


                return false;
            }


            //액션모드가 닫힐 때 자동으로 실행
            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });


    }

}
