package com.jmnl2020.fragmentpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Page3Fragment extends Fragment {

    ListView listView;
    ArrayAdapter adapter;
    ArrayList<String> datas= new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        datas.add("아직은 신이 아니야");
        datas.add("씨앗");
        datas.add("크로스 토크");
        datas.add("나는 소망한다 내게 금지된 것을");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_page3, container, false);

        listView= view.findViewById(R.id.listview);
        adapter= new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, datas);

        listView.setAdapter(adapter);

        return view;
    }
}
