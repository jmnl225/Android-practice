package com.jmnl2020.chattest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

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
        View itemView= null;

        MessageItem item = messageItems.get(position); //현재 만들어야할 번째의 아이템

        // cardview 아이템을 불러와야함. 이미지 + String

        // 뷰를 만드는 작업 (new View / create View)
        //FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();




        //2. 값을 연결하는 작업



        //여기서 Textview와 Image 뷰 참조변수를 find 해줘서 값을 대입(set) 시킴..??

        return null;
    }
}
