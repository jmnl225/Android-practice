package com.jmnl2020.chattest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<CardviewItem> cardviewItems = new ArrayList<>();
    RecyclerviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //액티비티가 실행될 때

        recyclerView= findViewById(R.id.recycler);
        adapter = new RecyclerviewAdapter(this, cardviewItems);
        recyclerView.setAdapter(adapter);


    }

    @Override
    protected void onResume() {
        super.onResume();


        //firebase정보 가져오기

        final FirebaseDatabase firebaseDatabase;
        final DatabaseReference mDatabaseRef;

        firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseRef = firebaseDatabase.getReference();

//        mDatabaseRef.child("ChatRooms").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Iterable<DataSnapshot> snapshots =dataSnapshot.getChildren();
//
//                //기존의 아이템을 없애야함
//                cardviewItems.clear();
//                adapter.notifyDataSetChanged();
//
//                for( DataSnapshot snapshot : snapshots ){
//                    CardviewItem item= snapshot.getValue(CardviewItem.class);
//                    cardviewItems.add(0, item);
//                    adapter.notifyItemInserted(0);
//                }
//
//                adapter.notifyDataSetChanged();
//
//             }
//
//
//             //mDatabase.child("ChatRooms").addValueEventListener - > 이 경우에는 추가한 데이터의 전체적인 snapshot을 찍어서 리턴.
//            // 따라서 전체적으로 뷰를 갱신시켜야한다.
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });



        mDatabaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d("Tag", "onChildAdded:" + dataSnapshot.getKey());


                //새로 만들어진 datasnapshot을 얻어와서
                CardviewItem item = dataSnapshot.getValue(CardviewItem.class);

                cardviewItems.add(0,item);
                adapter.notifyItemInserted(0);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Iterable<DataSnapshot> snapshots =dataSnapshot.getChildren();

                    //기존의 아이템을 없애야함
                    cardviewItems.clear();
                    adapter.notifyDataSetChanged();

                    for( DataSnapshot snapshot : snapshots ){
                        CardviewItem item= snapshot.getValue(CardviewItem.class);
                        cardviewItems.add(0, item);
                        adapter.notifyItemInserted(0);
                    }

                    adapter.notifyDataSetChanged();



            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void ClickWrite(View view) {
        //write 버튼을 눌렀을때 -> 작성 페이지로 넘어감
        Intent intent = new Intent(this, EditActivity.class);
        startActivity(intent);

    }
}
