package com.jmnl2020.chattest;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class RecyclerviewAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<CardviewItem> items;

    //받아올 이미지뷰 참조변수
    ImageView iv;

    public RecyclerviewAdapter(Context context, ArrayList<CardviewItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //뷰를 만들어주는 메소드

        View itemView = LayoutInflater.from(context).inflate(R.layout.item_cardview, parent, false);
        VH holder = new VH(itemView);

        iv= itemView.findViewById(R.id.cv_img);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh= (VH) holder;

      vh.cvTitle.setText(  items.get(position).cvTitle );
      vh.cvTitle.setText(  items.get(position).cvTitle );
      vh.cvTitle.setText(  items.get(position).cvTitle );

      Glide.with(context).load(items.get(position).cvImgfile).into(vh.cvImgfile);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder {

        ImageView cvImgfile;
        TextView cvTitle;
        TextView cvName;
        TextView cvMsg;

        public VH(@NonNull View itemView) {
            super(itemView);

            cvTitle = itemView.findViewById(R.id.cv_title);
            cvName = itemView.findViewById(R.id.cv_name);
            cvMsg = itemView.findViewById(R.id.cv_msg);
            cvImgfile = itemView.findViewById(R.id.cv_img);

        }
    }

}
