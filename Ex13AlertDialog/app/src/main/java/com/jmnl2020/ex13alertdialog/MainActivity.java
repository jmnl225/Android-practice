package com.jmnl2020.ex13alertdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void clickBtn(View view) {
        //1. AlertDialog 생성 - > 만들어주는 건축가(Builder) 객체 생성

        AlertDialog.Builder builder= new AlertDialog.Builder(this);

        //2. 건축가에게 아ㅏㅏ 나도 건축가되고싶다 만들고자하는 AlertDialog에 원하는 요구사항
        // -> 제목 / 아이콘 설정 (선택사항)

        builder.setTitle("저장은 했냐?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        //다이얼로그에 보일 메세지 설정
//        builder.setMessage("Do you wanna go home?");

        //다이얼로그에 보일 커스텀뷰 설정
        // xml로 뷰를 설계하고 이를 객체로 생성하여 다이얼로그에 설정
        // res/layout/dialog.xml를 자바의 view객체로 만들어주는 객체소환 (Layoutinflater 소환
        LayoutInflater inflater= this.getLayoutInflater();
        View v=inflater.inflate(R.layout.dialog, null);

        //만들어진 v(LinearLayout)에게 안에있는 EditText와 TextView를 찾아달라고 부탁!
        //*************id를 이용해서 view를 찾아와 참조변수에 대입***************
        et= v.findViewById(R.id.et); //*************중요!!!!*************
        tv= v.findViewById(R.id.tv);

        builder.setView(v);

        //다이얼로그에 버튼 달기
        builder.setPositiveButton("YES!!!!!!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //이 버튼이 눌렸을때 자동으로 실행되는 메소드
                Toast t= Toast.makeText(MainActivity.this,"YEAH~~~!", Toast.LENGTH_LONG);
                t.show();

            }
        });

        builder.setNegativeButton("응 이거 다하고 ^^", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast t= Toast.makeText(MainActivity.this, "ㅎㅎ~~~", Toast.LENGTH_LONG);
                t.show();
            }
        });

        //건축가한테 요구사항 다 말함
        // 요구사항대로 Alret Dialog 만들어달라고 요청

        AlertDialog dialog= builder.create();

        //만들어진 Dialog를 화면에 보이기
        dialog.show();

        //Dialog의 바깥쪽을 터치했을 때 창이 사라지는지 여부설정
//        dialog.setCanceledOnTouchOutside(false);

        dialog.setCancelable(false);

    }//clickBtn method end.


//다이얼로그안에있는 커스텀뷰 안에있는 버튼 클릭시 실행되는 메소드

    public void clickdialogBtn(View v){

        //EditText에 적힌 글씨를 얻어와서
        String s= et.getText().toString();

        //얻어온 글씨를 TextView에 찍어낼것!
        tv.setText(s);

    }


}//mainActivity class end.

