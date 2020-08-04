package com.jmnl2020.ex54materialtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    TextInputLayout inputLayout;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.et);
        imageView = findViewById(R.id.iv);

        //imageView.setImageResource(R.drawable.icebg1);

        //Glide Library
        Glide.with(this).load(R.drawable.moana).into(imageView);

        inputLayout= findViewById(R.id.layout_et);
        inputLayout.setCounterEnabled(true);
        inputLayout.setCounterMaxLength(10);

        //이걸 적는 EditText의 inputType이 password 여야함
        inputLayout.setPasswordVisibilityToggleEnabled(true);

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().contains("#")){
                    inputLayout.setError("특수문자 사용금지");
                }else {
                    inputLayout.setError(null);
                }

                if(s.length()==0 ) inputLayout.clearFocus();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
