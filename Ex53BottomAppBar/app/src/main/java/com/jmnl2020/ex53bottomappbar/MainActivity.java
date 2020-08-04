package com.jmnl2020.ex53bottomappbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomappbar.BottomAppBar;

public class MainActivity extends AppCompatActivity {

    BottomAppBar bab;
    boolean isCentre= true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bab= findViewById(R.id.bab);
        setSupportActionBar(bab);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void clickFab(View view) {
        isCentre = !isCentre;
        if (isCentre) bab.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
        else bab.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);

    }
}
