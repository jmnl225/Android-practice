package com.jmnl2020.ex16popupmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn=findViewById(R.id.btn);
        btn2= findViewById(R.id.btn2);

        //버튼에 롱~클릭 리스너 객체 생성 및 설정!
        btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivity.this, "Long Click", Toast.LENGTH_SHORT).show();

                //PopupMenu 객체생성 [Menu객체가 놓여질 수 있는 양탄자 같은 친구가 필요!]
                PopupMenu popup= new PopupMenu(MainActivity.this, btn2 );
                //두번째 파라미터가 팝업메뉴가 붙을 뷰!
//                popup.getMenu().add("")

                MenuInflater menuInflater= getMenuInflater();
                menuInflater.inflate(R.menu.popup, popup.getMenu());

                //팝업메뉴 아이템을 클릭했을 때를 듣는 Listener객체 생성 및 설정
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id= item.getItemId();
                        switch (id){
                            case R.id.menu_info:
                                Toast.makeText(MainActivity.this, "info", Toast.LENGTH_SHORT).show();
                                break;

                            case R.id.menu_delete:
                                Toast.makeText(MainActivity.this, "delete", Toast.LENGTH_SHORT).show();
                                break;

                            case R.id.menu_modify:
                                Toast.makeText(MainActivity.this, "modify", Toast.LENGTH_SHORT).show();
                                break;
                        }

                        return false;
                    }
                });


                //팝업메뉴 보이기
                popup.show();

                //리턴값 : 클릭이벤트의 작업을 여기서 끝내내냐?
                //true 하면 click이 안 뜸. false하면 그냥 클릭도 됨. 보통 true ~~~
               return true;
            }
        });
    }

    public void clickBtn(View view) {
        Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
    }
}
