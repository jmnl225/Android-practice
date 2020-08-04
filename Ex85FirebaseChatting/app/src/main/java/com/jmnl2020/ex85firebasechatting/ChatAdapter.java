package com.jmnl2020.ex85firebasechatting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter extends BaseAdapter {

    Context context;
    ArrayList<MessageItem> messageItems;

    public ChatAdapter(Context context, ArrayList<MessageItem> messageItems) {
        this.context = context;
        this.messageItems = messageItems;
    }

    @Override
    public int getCount() {
        return messageItems.size();
    }

    @Override
    public Object getItem(int position) {
        return messageItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemView = null;

        MessageItem item= messageItems.get(position); //현재 만들어야할 번째 것의 아이템

        //1. 뷰를 만드는 작업 (new view / create view)
        // 만들 뷰가 my_msgbox 혹은 other_msgbox 둘 중 하나. 그런데 누구인줄 어떻게 만드나?
        if(G.nickName.equals(item.name) ){ // 내가 쓰는 말이면
            itemView= LayoutInflater.from(context).inflate(R.layout.my_msgbox, parent, false);
        }else {
            itemView= LayoutInflater.from(context).inflate(R.layout.other_msgbox, parent, false);
        }

        //2. 값을 연결하는 작업 (bind)

        CircleImageView iv= itemView.findViewById(R.id.iv);
        TextView tvName = itemView.findViewById(R.id.tv_name);
        TextView tvMsg= itemView.findViewById(R.id.tv_msg);
        TextView tvTime = itemView.findViewById(R.id.tv_time);

        Glide.with(context).load(item.profileUrl).into(iv);

        tvName.setText(item.name);
        tvMsg.setText(item.message);
        tvTime.setText(item.time);

        return itemView;
    }
}
