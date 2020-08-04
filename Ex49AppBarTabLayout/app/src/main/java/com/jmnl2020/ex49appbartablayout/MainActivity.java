package com.jmnl2020.ex49appbartablayout;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.solver.widgets.ConstraintTableLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    TabLayout tabLayout;

    ViewPager pager;
    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //
        tabLayout= findViewById(R.id.tab);
        //탭버튼 객체생성

  /*      TabLayout.Tab tab1 = tabLayout.newTab();
        tab1.setText("Home");
        tab1.setIcon(R.mipmap.ic_launcher);
        tab1.setTag("tab1"); //tab의 식별자, id 역할
        tabLayout.addTab(tab1);

        TabLayout.Tab tab2 = tabLayout.newTab().setText("Theme").setIcon(R.mipmap.ic_launcher_round).setTag("Tag2");
        tabLayout.addTab(tab2);

        tabLayout.addTab(tabLayout.newTab().setText("Talk").setIcon(R.mipmap.ic_launcher).setTag("Tag3"));
   */     //

        //뷰페이저에 아답터 설정
        adapter = new MyAdapter(getSupportFragmentManager());
        pager= findViewById(R.id.pager);
        pager.setAdapter(adapter);


        //TabLayout과 ViewPager를 연동하기
        //연동하면 Tab버튼의 글씨를 뷰페이저의 아답터에서 결정.
        tabLayout.setupWithViewPager(pager);

        //연동을 시키면 기본적으로 탭버튼에 아이콘이 없음
        tabLayout.getTabAt(0).setIcon(R.mipmap.ic_launcher);



        //제목줄 관리자


        //탭이 변경되는 것을 듣는 리스너객체 추가
//        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                getSupportActionBar().setSubtitle( tab.getText() );
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });


        drawerLayout= findViewById(R.id.drawer);
        navigationView= findViewById(R.id.nav);

        drawerToggle= new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(drawerToggle); //햄버거가 돌아감!
        drawerToggle.syncState();

    }
}
