package com.jmnl2020.ex56recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> items = new ArrayList<>();

    RecyclerView recyclerView;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items.add(new String("A"));
        items.add(new String("B"));
        items.add(new String("C"));
        items.add(new String("A"));
        items.add(new String("B"));
        items.add(new String("C"));
        items.add(new String("A"));
        items.add(new String("B"));
        items.add(new String("C"));
        items.add(new String("A"));
        items.add(new String("B"));
        items.add(new String("C"));

        adapter = new MyAdapter(this, items);
        recyclerView= findViewById(R.id.recycler);
        recyclerView.setAdapter(adapter);

    }
}
