package com.jmnl2020.bex_tabhost;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    TabHost host;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        host= findViewById(R.id.host);
        host.setup();

        TabHost.TabSpec spec= host.newTabSpec("tab1");
        spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(), R.drawable.icon02, null));
        spec.setContent(R.id.tab_content1);
        host.addTab(spec);

        spec= host.newTabSpec("tab2");
        spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(), R.drawable.icon03, null));
        spec.setContent(R.id.tab_content2);
        host.addTab(spec);

        spec= host.newTabSpec("tab3");
        spec.setIndicator(null, ResourcesCompat.getDrawable(getResources(), R.drawable.icon04, null));
        spec.setContent(R.id.tab_content3);
        host.addTab(spec);

    }
}
