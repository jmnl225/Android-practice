package com.jmnl2020.ex44fragment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager= getSupportFragmentManager();

    }

    public void clickBtn(View view) {
        // R.id.container인 LinearLayout에 MyFragment를 동적 추가

        //Tragnsaction (트랜잭션): 롤백 기능이 있는 프로세스. -> commit되지 않는 이상 가상으로 진행.
        FragmentTransaction tran = fragmentManager.beginTransaction();

        //트랜잭션을 통해 원하는 작업 수행
        MyFragment fragment = new MyFragment();


        //fragment를 추가할때 데이터를 전달할 수 있음!
        Bundle bundle= new Bundle();
        bundle.putString("Name", "sam");
        bundle.putInt("Age", 20); //식별자 + value

        fragment.setArguments(bundle);

        tran.add(R.id.container, fragment); //id가 container인 뷰 그룹에 프레그먼트 동적추가
        //tran.remove(fragment); //제거
        //tran.replace(R.id.container, fragment2); //교체


        //플레그먼트를 백스택에 추가하면 뒤로가기 할 때 Activity가 바로 꺼지지 않음
        tran.addToBackStack(null);


        //트랜잭션이 완료되었다고 명령
        tran.commit();

    }
}
