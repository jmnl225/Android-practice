package com.jmnl2020.ex43fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyFragment extends Fragment {

    //뷰들의 참조변수
    TextView tv;
    Button btn, btn2;


    // 마치 MainActivity의 onCreate() 같은 역할의 메소드
    //즉 Fragment가 화면에 보여줄 뷰들을 만들어 리턴하는 콜백메소드


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //이 Fragment가 보여줄 view를 만들기
        // 첫번째 파라미터 inflater -> fragment xml
        View view= inflater.inflate(R.layout.fragment_my, container, false);
        //container에게 붙일건데 , false: 지금은 안 붙임 => 사이즈 계산


        ////////////////////////////////////
        //inflate를 한 후에 view 실행 가능
        //만들어진 뷰 안에있는 TextView와 Button 참조하기.

        tv= view.findViewById(R.id.tv_myf);
        btn= view.findViewById(R.id.btn1);
        btn2= view.findViewById(R.id.btn2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("Hello fragment");
            }
        });


//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //이 프레그먼트를 보여주는 Activity의 TextView를 제어
//                //액티비티 참조하기
//
//                MainActivity activity= (MainActivity) getActivity();
//                activity.tv.setText("바껴라 뿅!");
//            }
//        });


        return view;
    }

    public void changeText(String msg){ //String 바꾸는 메소드를 따로 만드는 게 나음
        tv.setText(msg);
    }

}
