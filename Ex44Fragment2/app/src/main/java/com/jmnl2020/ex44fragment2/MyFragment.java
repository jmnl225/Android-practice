package com.jmnl2020.ex44fragment2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyFragment extends Fragment {
    //이 프래그먼트가 보여줄 뷰를 만들어서 리턴하는 콜백메소드
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_my, container, false);





        String name= bundle.get("Name");
        int age= bundle.getInt("AGe", 0);




        return view;

    }
}
