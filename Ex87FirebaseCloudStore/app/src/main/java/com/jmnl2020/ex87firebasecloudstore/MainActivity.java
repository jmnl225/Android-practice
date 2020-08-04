package com.jmnl2020.ex87firebasecloudstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv= findViewById(R.id.tv);

    }

    public void clickSave(View view) {
        //firestore db에 저장 - Map Collection을 통으로 저장
        //저장할 데이터를 Map으로 생성
        Map<String, Object> user = new HashMap<>(); //map에 들어갈 타입이 object 이므로 다 들어갈 수 있음!
        user.put("name", "Sam");
        user.put("age", 20);
        user.put("address", "Seoul");

        //firestore db객체 소환!
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

        //"users" 이라는 이름의 Collection (자식노드같은 개념) 참조
        CollectionReference userRef = firebaseFirestore.collection("users");// 파라미터: 경로 이름 (실제 폴더명) 따라서, 하위폴더 생성.
        Task task = userRef.add(user);
        task.addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(MainActivity.this, "Saved 성공!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void clickLoad(View view) {
        // firestore db에서 get()메소드를 이용하여 DB값 읽기
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference usersRef= firebaseFirestore.collection("users");

        Task<QuerySnapshot> task = usersRef.get();
        task.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    QuerySnapshot querySnapshot = task.getResult();
                    //users 안에 여러개의 문서가 있으므로
                    for(QueryDocumentSnapshot snapshot : querySnapshot){ // 가장 바깥쪽: QuerySnapshot , 그 안쪽에 있는 데이터: QueryDocumentSnapshot
                        Map<String, Object> user = snapshot.getData();
                        String name = user.get("name").toString();
                        long age = (long)user.get("age");
                        String address = user.get("address").toString();

                        tv.append(name+"\n"+age+"\n"+address+"\n================\n");

                    }
                }
            }
        });

    }
}
