package com.jmnl2020.attendanceapp2;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PaserAdapter extends FragmentPagerAdapter {


    Fragment[] fragments = new Fragment[3];

    public PaserAdapter(@NonNull FragmentManager fm, int behavior, Context context) {
        super(fm, behavior);

        fragments[0] = new Framgment1Calendar(context);
        fragments[1] = new Framgment2();
        fragments[2] = new Framgment2();

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
