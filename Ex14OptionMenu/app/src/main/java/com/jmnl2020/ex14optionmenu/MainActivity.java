package com.jmnl2020.ex14optionmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //상수화된 변수(final 상수)
    final int MENU_fir= 1;
    final int MENU_sec= 2;

    //이 액티비티가 처음 생성되면 자동으로 호출되는 콜백메소드
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    //OnCreate()메소드가 실행된 후 자동으로 Optionmenu를 만드는 작업을하는 콜백메소드


    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //방법1. 메뉴아이템 추가를 Java언어의 add()메소드로하기

        //옵션메뉴에 들어갈 메뉴아이템을 생성하여 추가하면 됨
//        menu.add(0, 1, 0, ":)"); //Morning 이라는 글씨를 가진 MenyItem객체 생성 및 추가
//        menu.add(0, 2, 0, "XD");


        //방법2. ㅁㅔ뉴항목 설계를 xml에 하고 이걸 Java에서 객체로 만들어 적용
        //경로 : res/menu/option.xml 문서를 만들고 그 안에 메뉴항목들을 설계
        //menu폴더의 xml문서를 읽어와서 Menu객체로 만들어(부풀어서 후~ 불어 만듦!)달라고 요청
        // -> MenuInflater

        MenuInflater menuInflater=this.getMenuInflater();
        menuInflater.inflate(R.menu.option, menu);

        //overflow된 메뉴들은 기본적으로 icon이 보여지지 않음.
        //그런데 꼭~~~~ 보고 싶다면?
        if( menu instanceof MenuBuilder ){
            MenuBuilder menuBuilder= (MenuBuilder) menu;
            menuBuilder.setOptionalIconsVisible(true);
        }


        return super.onCreateOptionsMenu(menu);
    }

    //OptionMenu의 메뉴항목Item 을 선택했을 때
    //자동으로 실행되는 콜백메소드가 있음!!!

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //선택한 MenuItem객체를 파라미터(매개변수: item)으로 전달받음

        //전달된 Item객체를 **id를 이용하여** 식별해서 원하는 작업 수행
        int id= item.getItemId();
        switch (id){
            case R.id.menu_search:
                Toast t=Toast.makeText(this, "Search", Toast.LENGTH_SHORT);
                t.show();
                break;
            case R.id.menu_add:
                Toast.makeText(this,"ADD",Toast.LENGTH_SHORT).show();
                break;
        }
        //아 그런데~~  switch 1,2 이렇게 써두면 어케 아냐고요 ...? final 상수같은 걸 만들어둬야하지 않겠음? 위로~
        return super.onOptionsItemSelected(item);
    }
}//main end.
