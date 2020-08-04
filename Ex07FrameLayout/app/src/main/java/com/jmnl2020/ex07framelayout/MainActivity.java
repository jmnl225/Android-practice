package com.jmnl2020.ex07framelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout layout_korea, layout_aus, layout_ghana;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout_korea= findViewById(R.id.layout_korea);
        layout_aus= findViewById(R.id.layout_aus);
        layout_ghana= findViewById(R.id.layout_ghana);



    }

    public void clickBtn(View v){

        layout_ghana.setVisibility(View.INVISIBLE);
        layout_aus.setVisibility(View.INVISIBLE);
        layout_korea.setVisibility(View.INVISIBLE);

        //누른 버튼을 구별하기 위해 id 얻어오기.
        int id= v.getId();

        switch (id){
            case R.id.btn01:
                layout_korea.setVisibility(View.VISIBLE);
                break;

            case R.id.btn02:
                layout_aus.setVisibility(View.VISIBLE);
                break;

            case R.id.btn03:
                layout_ghana.setVisibility(View.VISIBLE);
                break;

        }

//        switch (id){
//            case R.id.btn01:
//                layout_korea.setVisibility(View.VISIBLE);
//                layout_aus.setVisibility(View.INVISIBLE);
//                layout_ghana.setVisibility(View.INVISIBLE);
//                break;
//
//            case R.id.btn02:
//                layout_korea.setVisibility(View.INVISIBLE);
//                layout_aus.setVisibility(View.VISIBLE);
//                layout_ghana.setVisibility(View.INVISIBLE);
//                break;
//
//            case R.id.btn03:
//                layout_korea.setVisibility(View.INVISIBLE);
//                layout_aus.setVisibility(View.INVISIBLE);
//                layout_ghana.setVisibility(View.VISIBLE);
//                break;
//
//        }


    }

}// main end.

