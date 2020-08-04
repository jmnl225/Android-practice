package com.jmnl2020.ex42viewpaser;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class MyAdapter extends PagerAdapter {

    ArrayList<Integer> items;

    LayoutInflater inflater;

    //생성자메소드
    public MyAdapter(ArrayList<Integer> items, LayoutInflater inflater) {
        this.items = items;
        this.inflater = inflater;
    }

    //이 아답터가 만들총 ag의 개수
    @Override
    public int getCount() {
        return items.size();
    }

    //이 아답터가 page.xml설계도면을 기반으로
    //ViewPage에 보여질 한 페이지(View 객체)를 만들어내는 작업 메소드
    //ListView에 사용되는 아답터의 getView()같은 메소드


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        //page.xml 문서ㅡㄹㄹ 읽어와서 View 객체로 만들기
        View page= inflater.inflate(R.layout.page, null);

        //이 page안에 있는 ImageViewㅇ[ 햔제반쩨 ㅇ;ㅁ;ㅈ;ㄹ,ㄹ 지정
        ImageView iv= page.findViewById(R.id.iv);
        iv.setImageResource( items.get(position));

        //만들어진 page를 viewpager(첫번째 파라미터:container)에 추가하기)
        container.addView(page);

        return page;
    }

//    //1. xml문서를 읽어와ㅓ View객체로 ㅏ\매
//    View page = inflater.inflate(R.layout.page, null);
//
//
//    //이 page안에 이쓴 ImageView에 현재번쨰 이미지 설ㅈㅇ
//    ImageView iv= page.findViewById(R.id.iv);
//        iv.setImageResource(items.get((Resources)));
//
//
//    //만들어진 page를 ViewPager(container)에 추가하지
//        container.addView(page);
//    //뷰페이저가 보여줄 viewm와 위에서 만든 [age가 같은 객체인지
//
//
//
//    ////////////////////////////////////////
//
//    //이 아답터가 pag.sml 설계도면을 기반으로
//    //view에 보여질 한 페이지를 만들어내는 작업ws
//
//    /////////////////////////

    //화면에 더이상 보이지 않아 메모리에서 page 제거하는 메소드
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView( (View) object );
    }



    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        //뷰 페이저가 보여줄 view와 위에서 만든 page 객체객체가 같으면 리턴

        return view == object;


    }


}

