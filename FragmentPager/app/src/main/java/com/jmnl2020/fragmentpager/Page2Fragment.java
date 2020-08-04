package com.jmnl2020.fragmentpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Page2Fragment extends Fragment {

    Button btn;
    ImageView iv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_page2, container, false);

        iv= view.findViewById(R.id.iv);
        btn= view.findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//               if((int)iv==R.drawable.icon03){
//                   iv.setImageResource(R.drawable.icon02);
//               }else if(iv.equals("R.drawable.icon02")){
//                    iv.setImageResource(R.drawable.icon03);
//                }

                iv.setImageResource(R.drawable.icon03);

            }
        });

        return view;
    }
}
