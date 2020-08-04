package com.jmnl2020.ex15contextmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn =findViewById(R.id.btn);
        //액티비티에게 btn객체를 ContextMenu로 등록
       this.registerForContextMenu(btn);

    }

    //Context메뉴로 지정된 View(btn)이 롱-클릭 되었을때
    //보여지는 ContextMenu를 만드는 작업을 하는 콜백메소드


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        //전달받은 menu객체에 menuItem을 추가
        //경로: res/menu/context.xml 파일을 만들어 메뉴항목 작성

        //만든 xml문서를 읽어와서 menu객체로 만들기
        MenuInflater menuInflater= this.getMenuInflater();
        menuInflater.inflate(R.menu.context, menu);


        super.onCreateContextMenu(menu, v, menuInfo);
    }

    //contextmenu의 메뉴아이팀 선택했을때 자동실행 콜백메소드
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();
        switch (id){
            case R.id.menu_save:
                Toast.makeText(this, "SAVE", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_delete:
                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onContextItemSelected(item);
    }

    public void clickBtn(View view) {
        //마법의 빨간램프~~~ 아 줫나하기싫다

        Toast.makeText(this, "X)", Toast.LENGTH_SHORT).show();


    }
}
