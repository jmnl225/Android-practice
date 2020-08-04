package com.jmnl2020.ex52bottomnavigationview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    FragmentManager fragmentManager;
    Fragment[] fragments = new Fragment[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //프레그먼트의 동적 추가/ 삭제/ 제거를 위한 관리자 객체 소환
        fragmentManager = getSupportFragmentManager();

        //각 탭 화면의 프래그먼트 생성
        fragments[0] = new Tab1Fragment();
        fragments[1] = new Tab2Fragment();
        fragments[2] = new Tab3Fragment();
        fragments[3] = new Tab4Fragment();


        bottomNavigationView = findViewById(R.id.bottomnav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                //프레그먼트 작업 트랜잭션 시작
                FragmentTransaction tran = fragmentManager.beginTransaction();

                switch ( menuItem.getItemId() ){
                    case R.id.munu1:
                        tran.replace(R.id.container, fragments[0]);
                        break;

                    case R.id.munu2:
                        tran.replace(R.id.container, fragments[1]);
                        break;

                    case R.id.munu3:
                        tran.replace(R.id.container, fragments[2]);
                        break;

                    case R.id.munu4:
                        tran.replace(R.id.container, fragments[3]);
                        break;

                }

                //트랜잭션 작업 완료 명령
                tran.commit();

                //return true 가 아니면 탭은 선택이 되지만 선택 효과가 이동하지 않음.
                return true;
            }


        });

    }
}
