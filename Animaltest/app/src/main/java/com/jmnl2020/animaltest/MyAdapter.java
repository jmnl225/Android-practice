package com.jmnl2020.animaltest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    ArrayList<Post> posts;

    LayoutInflater inflater;


    //생성자를 만들어서 대량의 데이터를 전달받아야함. 어댑터가 실행되면 데이터를 받아서 객체를 생성하기 떄문에.
    public MyAdapter(ArrayList<Post> posts, LayoutInflater inflater){

        //전달받은 데이터를 멤버변수에 대입
        this.posts= posts;

        this.inflater= inflater;

    }


    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //1. new View

        if( convertView== null){ //null이 아니면 재활용
            convertView= inflater.inflate(R.layout.list_item, null); }

        // 2. set View

        TextView name = convertView.findViewById(R.id.tv01);

        Post post = posts.get(position);
        name.setText(post.name);

        return convertView;
    }
}
