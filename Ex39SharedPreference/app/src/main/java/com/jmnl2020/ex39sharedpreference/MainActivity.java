package com.jmnl2020.ex39sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText et_name, et_age;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_name= findViewById(R.id.et_name);
        et_age= findViewById(R.id.et_age);
        tv= findViewById(R.id.tv);

        //Shaered Preference 는 내부 메모리/ 내장 메모리에만 저장. (앱이 삭제되면 함께 사라짐)
        //xml파일로 저장됨

    }

    public void clickSave(View view) {
        //EditeText에게 데이터 얻어오기
        String name= et_name.getText().toString();
        String ageStr= et_age.getText().toString();
        int age;
        try {
            age= Integer.parseInt(ageStr);
        }catch (Exception e){
            age=1;
        }


        //내부메모리에 Data.xml 이라는 문서에 데이터를 저장해주는 객체를 소환
        SharedPreferences pref= getSharedPreferences("Data", MODE_PRIVATE);

        SharedPreferences.Editor editor =pref.edit();// 문서저장을 시작한다! 선언을 꼭 해줘야함. 시작점.
        //Editor가 나옴.

        editor.putString("Name", name);
        editor.putInt("Age", age);
        editor.commit();

    }

    public void clickLoad(View view) {

        SharedPreferences pref= getSharedPreferences("Data", MODE_PRIVATE);
        //Save와 Load 같음

        String name= pref.getString("Name", "no name");
        int age= pref.getInt("Age", 2);

        tv.setText(name+"\n"+age);

    }
}
