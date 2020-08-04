package com.jmnl2020.fragmentpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    ViewPager pager;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager= findViewById(R.id.pager);
        adapter= new MyAdapter(getSupportFragmentManager());

        pager.setAdapter(adapter);

    }
}
