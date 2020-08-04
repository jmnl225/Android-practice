package com.jmnl2020.ex50floatingactionbuttonsnackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickFab(View view) {

        //Toast.makeText(this, "뿅", Toast.LENGTH_SHORT).show();
        //토스트 업그레이드 버전 SnackBar
        Snackbar.make(view, "Snack Bar", Snackbar.LENGTH_LONG).setAction("확인", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "확인되었습니다.", Toast.LENGTH_SHORT).show();
            }
        }).show();

    }

    public void clickBtn(View view) {
        //새로운 CoordinatorLayout을 이용하면 원하는 위치에 snackBar를 보이게 할 수 있음

        //xml에서 coordinator layout 만들고 아이디 찾아오기
        View v= findViewById(R.id.layout_snackbar_container);
        Snackbar.make(v, "냠냠", Snackbar.LENGTH_LONG).setAction("끝", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        }).show();

    }
}
