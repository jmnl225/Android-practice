package com.jmnl2020.chattest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChattingActivity extends AppCompatActivity {

    ListView listView;
    EditText editText;

    ArrayList<MessageItem> messageItems = new ArrayList<>();

    FirebaseDatabase firebaseDatabase;
    DatabaseReference chatRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);

        listView = findViewById(R.id.listview);
        editText = findViewById(R.id.ct_msg);


    }
}
