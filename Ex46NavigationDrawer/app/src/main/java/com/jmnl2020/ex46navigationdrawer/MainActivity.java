package com.jmnl2020.ex46navigationdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout= findViewById(R.id.layout_drawer);

        navigationView= findViewById(R.id.navi);
        //item icon색조를 적용하지 않음
        navigationView.setItemIconTintList(null);

        //네비뷰의 메뉴항목을 클릭했을 때 반응하기!!
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id= menuItem.getItemId();
                switch (id){
                    case R.id.menu_gallery:
                        Toast.makeText(MainActivity.this, "Gallery", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.menu_dm:
                        Toast.makeText(MainActivity.this, "DM", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.menu_send:
                        Toast.makeText(MainActivity.this, "Send", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.aaa:
                        Toast.makeText(MainActivity.this, "aaa", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.bb:
                        Toast.makeText(MainActivity.this, "bb", Toast.LENGTH_SHORT).show();
                        break;
                }//switch end.

                //Drawer를 닫도록 설정
                drawerLayout.closeDrawer(navigationView);

                return false;
            }
        });


//        public void clickHearicon(View v){
//            //
//            Toast.makeText(this, "뿅", Toast.LENGTH_SHORT).show();
//        }


    }
}
