package com.jmnl2020.ex57recyclerview2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class MyAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<Item> items;

    //1. 생성자
    public MyAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //3. layout inflater와 뷰홀더 객체 생성

        LayoutInflater inflater = LayoutInflater.from(    context    );
        View itemview = inflater.inflate(R.layout.recycler_item, parent, false);

        VH holder = new VH(itemview);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        //5.

        VH vh= (VH) holder;
        //현재번째 데이터를 가진 item객체 얻어오기
        Item item = items.get(position);

//        vh.civ.setImageResource( item.profileimg );
        vh.tv_name.setText( item.name );
        vh.tv_msg.setText( item.msg );

        //네트워크 이미지를 불러오는 작업을 쉽게해주는 외부 라이브러리
        // glide
        Glide.with(context).load(item.imgURL).into(vh.iv);
        Glide.with(context).load(item.profileimg).into(vh.civ);

    }

    @Override
    public int getItemCount() {
        //2. 사이즈
        return items.size();
    }


    //4. item view (사실상 3번) 뷰들의 참조변수를 멤버로가진 class
    class VH extends RecyclerView.ViewHolder{
        VH holder = new VH(itemView);

        CircleImageView civ;
        TextView tv_name;
        TextView tv_msg;
        ImageView iv;

        public VH(@NonNull View itemView) {
            super(itemView);

            civ= itemView.findViewById(R.id.iv_profile);
            tv_msg = itemView.findViewById(R.id.tv_msg);
            tv_name = itemView.findViewById(R.id.tv_name);
            iv = itemView.findViewById(R.id.iv);


        }
    }



}
