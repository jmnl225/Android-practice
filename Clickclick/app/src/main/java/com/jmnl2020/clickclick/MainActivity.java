package com.jmnl2020.clickclick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    //변수 만들기
    TextView tv;
    ImageView start;
    LinearLayout layout01, layout02, layout03, layout04;
    ImageView[] imgs= new ImageView[12]; //img들을 참조하는 변수를 가진 객체

    //tag값을 갖고있는 정답 int
    int tagnum;

    //Arraylist
    ArrayList<Integer> arrayList= new ArrayList<>();

    //switch를 위한 int stage
    int stage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv = findViewById(R.id.tv);
        start = findViewById(R.id.start);
        layout01 = findViewById(R.id.layout01);
        layout02 = findViewById(R.id.layout02);
        layout03 = findViewById(R.id.layout03);
        layout04 = findViewById(R.id.layout04);


        //xml 이미지들 id를 대입

        for(int i=0; i<imgs.length; i++){
            imgs[i]= findViewById(R.id.img01+ i);
        }


        //0. 이미지가 모두 보이지 않음

        layout01.setVisibility(View.INVISIBLE);
        layout02.setVisibility(View.INVISIBLE);
        layout03.setVisibility(View.INVISIBLE);
        layout04.setVisibility(View.INVISIBLE);


        //1. start를 누르고 게임이 시작됨

        View.OnClickListener startlistener= new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start.setImageResource(R.drawable.ing);
                stage = 1;
                tv.setText("숫자를 순서대로 클릭하세요!");
                layout01.setVisibility(View.VISIBLE);
                layout02.setVisibility(View.VISIBLE);
                layout03.setVisibility(View.VISIBLE);
                layout04.setVisibility(View.VISIBLE);
                initial(stage);
            }
        };
        start.setOnClickListener(startlistener);

        //2. 이미지 숫자들을 눌러야함
        //3. 다 사라지면 다른 이미지들이 불러와짐

        //4. 다 사라지면 다른 이미지들이 불러와짐2
        //5. 끝!


    }// Create method end.

    //onClick 속성이 부여된 버튼이 클릭 되었을 때
    //layout 참조변수를 못 알아들어서 실행이 안 됨. ㅠ
//    public void start(View v){
//        start.setImageResource(R.drawable.ing);
//        layout01.setVisibility(View.VISIBLE);
//        layout02.setVisibility(View.VISIBLE);
//        layout03.setVisibility(View.VISIBLE);
//        layout04.setVisibility(View.VISIBLE);
//    }

    public void clicknum(View v){

        //이미지에 저장된 태그를 얻어와서
        String s= v.getTag().toString();
        int num= Integer.parseInt(s);
        ImageView iv= (ImageView) v;

        //이미지를 없애고

        if(num==tagnum){
            iv.setVisibility(View.INVISIBLE);

            //눌러야할 번호를 증가시킴
            tagnum++;

            //모두 끝나면 다음 Stage 그림을 호출
            if(tagnum>=12){
                stage=2;
                tv.setText("알파벳을 순서대로 클릭하세요!");
                initial(stage);
            }

        }// if end.


    }//clicknum end.


//    public void stage2() {
//
//        for (int i = 0; i < imgs.length; i++) {
//            imgs[i].setVisibility(View.VISIBLE);
//            imgs[i].setImageResource(R.drawable.alpa01 + arrayList.get(i));
//        }
//
//        //문제1. 중복된 값이 나옴 -> initial때문이 아닐까?
//        //문제2. 안 사라짐
//
//
//    }


    void initial(int stage){

        //ArrayList를 만들어서 숫자만큼 넣기.

        for(int i=0; i<12; i++){
            arrayList.add(i);
        }

        //arrayList요소들을 섞기.
        Collections.shuffle(arrayList);

        for(int i=0; i<imgs.length; i++){
            //이미지 붙여두기... ?  ? ??  ?

            switch (stage){
                case 1:
                    imgs[i].setImageResource(R.drawable.num01+arrayList.get(i));
                    //arraylist요소를 섞어두었기 때문에 거기서 값을 가져와서 더해주면 자연스럽게 섞임!
                    break;
                case 2:
                    imgs[i].setVisibility(View.VISIBLE);
                    imgs[i].setImageResource(R.drawable.alpa01 + arrayList.get(i));
                    break;
                case 3:
                    imgs[i].setImageResource(R.drawable.alpa01 + arrayList.get(i));
                    break;
            }


            //태그 붙여두기
            imgs[i].setTag(arrayList.get(i));

        }
    }

//    public void stage2(View v){
//
//        //이미지를 모두 보여줘야함
//        for(int i=0; i<imgs.length; i++){
//            imgs[i].setVisibility(View.VISIBLE);
//            imgs[i].setImageResource(R.drawable.alpa01+arrayList.get(i));
//        }
//
//        //이미지에 저장된 태그를 얻어와서
//        String s= v.getTag().toString();
//        int num= Integer.parseInt(s);
//        ImageView iv= (ImageView) v;
//
//        //이미지를 없애고
//
//        if(num==tagnum){
//            iv.setVisibility(View.INVISIBLE);
//
//            //눌러야할 번호를 증가시킴
//            tagnum++;
//
//            //모두 끝나면 다음 Stage 그림을 호출
//            if(tagnum>=12){
//                tv.setText("십이지신 순서대로 클릭하세요!");
//                initial();
//                stage3(v);
//            }
//
//        }// if end.
//
//    }//stage2 end.
//



}// Activity view end.
