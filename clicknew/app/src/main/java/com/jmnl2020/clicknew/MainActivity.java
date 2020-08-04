package com.jmnl2020.clicknew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    ImageView start;
    LinearLayout layout01, layout02, layout03, layout04;
    ImageView[] imgs= new ImageView[12];

    LinearLayout bg;

    int tagnum;

    ArrayList<Integer> arrayList= new ArrayList<>();

    int stage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //id를 변수에 붙여주기

        bg= findViewById(R.id.bg);

        tv = findViewById(R.id.tv);
        start = findViewById(R.id.start);
        layout01 = findViewById(R.id.layout01);
        layout02 = findViewById(R.id.layout02);
        layout03 = findViewById(R.id.layout03);
        layout04 = findViewById(R.id.layout04);

        for(int i=0; i<imgs.length; i++){
            imgs[i]= findViewById(R.id.img01+ i);
        }

        //이미지를 모두 안 보이게.

        layout01.setVisibility(View.INVISIBLE);
        layout02.setVisibility(View.INVISIBLE);
        layout03.setVisibility(View.INVISIBLE);
        layout04.setVisibility(View.INVISIBLE);


        //start를 누르면 게임이 시작.

        View.OnClickListener startlistener= new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start.setImageResource(R.drawable.ing);
                tagnum=0;
                for(int i=0; i<12; i++){
                    imgs[i].setTag(i);
                    arrayList.clear();
                }
                stage = 1;
                nextStage(stage);
            }
        };
        start.setOnClickListener(startlistener);

        //Stage에 따라 넣어두는 이미지가 다름!


    }//override end.

    //이미지가 눌렸을 경우
    public void clicknum(View v){

        Log.d("aaa", "click");

        String s= v.getTag().toString();
        int num= Integer.parseInt(s);
        ImageView iv= (ImageView) v;

        if(num==tagnum){
            iv.setVisibility(View.INVISIBLE);

            //눌러야할 번호를 증가시킴
            tagnum++;

            //모두 끝났다면 다음 스테이지로 넘어감.
            if(tagnum>=12){
                stage++;
                tagnum=0;
                for(int i=0; i<12; i++){
                    imgs[i].setTag(i);
                    arrayList.clear();
                }
                nextStage(stage);
            }
        }

    }// click method end.


    public void nextStage(int stage){

                layout01.setVisibility(View.VISIBLE);
                layout02.setVisibility(View.VISIBLE);
                layout03.setVisibility(View.VISIBLE);
                layout04.setVisibility(View.VISIBLE);

        //arrayList에 숫자를 넣자
        for(int i=0; i<12; i++){
            arrayList.add(i);
        }

        //요소를 섞고,
        Collections.shuffle(arrayList);

        //이미지를 대입시킴!
        for(int i=0; i<imgs.length; i++)
            switch (stage){
                case 1:
                    bg.setBackgroundResource(R.drawable.bg1);
                    tv.setText("숫자를 순서대로 클릭하세요");
                    imgs[i].setVisibility(View.VISIBLE);
                    imgs[i].setImageResource(R.drawable.num01+arrayList.get(i));
                    break;
                case 2:
                    bg.setBackgroundResource(R.drawable.bg2);
                    tv.setText("알파벳을 순서대로 클릭하세요");
                    imgs[i].setVisibility(View.VISIBLE);
                    imgs[i].setImageResource(R.drawable.ch01 + arrayList.get(i));
                    break;
                case 3:
                    bg.setBackgroundResource(R.drawable.bg3);
                    tv.setText("십이지신을 순서대로 클릭하세요");
                    imgs[i].setVisibility(View.VISIBLE);
                    imgs[i].setImageResource(R.drawable.cha01 + arrayList.get(i));
                    break;
                case 4:
                    tv.setText("Clear!");
                    start.setImageResource(R.drawable.start);
                    bg.setBackgroundResource(R.drawable.bg4);
            }

        //태그를 붙이자.
        for(int i=0; i<imgs.length; i++){
            imgs[i].setTag(arrayList.get(i));
        }
    }


}// main end.
