package com.jmnl2020.ex83firebasedb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText et;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et= findViewById(R.id.et);
        tv= findViewById(R.id.tv);

    }

    public void clickBtn(View view) {

        //EditText에 있는 글씨 얻어오기
        String text= et.getText().toString();

        //Firebase 실시간 DB에 저장

        //1. Firebase 실시간 DB 관리객체를 얻어오기
        FirebaseDatabase db= FirebaseDatabase.getInstance(); //이걸로 DB와 연결됨

        //2. 저장시킬 노드 참조객체 가져오기
        DatabaseReference rootRef= db.getReference(); //최상위 노드가 옴

        //3. 해당 노드에 값 설정

        // 3-1 : 값 갱신시키는 방법
//        rootRef.setValue(text);
//
//        //4. DB의 값을 실시간으로 읽어오기
//        rootRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                //값이 변경되었을 때 실행되는 메소드
//                // 값 읽기 버튼이 필요 없음. 실시간으로 값 전달!
//                //이 파라미터로 전달된 DataSnapshot 객체가 데이터를 가지고 옴
//                String data = dataSnapshot.getValue(String.class);
//                //받을 자료형을 바로 적어주면 되기 때문에 자료형을 직접 만들었을 때 유용. 단, 입력도 같은 자료형이어야 할 것
//
//                tv.setText(data);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


        //3-2 : 위 3-1 번 방법은 값이 바꿔치기(갱신)되기 때문에
        //값이 누적되어 추가시키는 방법.

//        DatabaseReference ref1=rootRef.push(); // 자식 노드가 새로 생기고 그 참조객체 리턴
//        ref1.setValue(text);
//
//        //값 읽기
//        rootRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                // 최상위 노드에 바로 값이 있는 것이 아님!
//                //자식 노드들이 여러개이므로 반복문을 이용해서 값들을 읽어와야함
//
//                StringBuffer buffer= new StringBuffer();
//                for( DataSnapshot snap : dataSnapshot.getChildren()){
//                    String data= snap.getValue(String.class);
//                    buffer.append(data+"\n");
//                }
//
//                tv.setText(buffer.toString());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


        //3-3: 자식노드에 '키'값 지정하여 만들기
//        final DatabaseReference dataRef = rootRef.child("data");
//        //dataRef.setValue("aaa");
//        // data 노드의 자식으로 여러개의 값을 저장
//        dataRef.push().setValue(text);
//
//        //data노드의 변경만 읽어오면 되기 때문에
//        dataRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                String s= "";
//                for( DataSnapshot snap : dataSnapshot.getChildren()){
//                    String data = snap.getValue(String.class);
//                    s+= data+"\n";
//                }
//
//                tv.setText(s);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });



        //3-4: 하나의 노드에 Value가 여러개.
//        DatabaseReference memberRef= rootRef.child("member");
//        DatabaseReference itemRef= memberRef.push(); // member 노드 아래에 임의의 식별자를 가진 자식노드
//        itemRef.child("name").setValue(text);
//        itemRef.child("age").setValue(20);


        //3-5: 나만의 클래스 [VO: value Object] 객체를 만들어서 한번에 멤버값들 저장하기
        String name = et.getText().toString();
        int age= 20;
        String address= "seoul";

        //저장할 값들을 하나의 객체로 생성하기
        Person person = new Person(name, age, address);

        // 'person'이라는 이름의 자신노드를 참조하는 객체 생성 or 참조
        DatabaseReference personRef= rootRef.child("persons");
        personRef.push().setValue(person);


        // 'persons' 노드의 밸류가 변경되는 것을 듣는 리스너 추가
        personRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Person이 여러개 이므로
                for( DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Person p = snapshot.getValue(Person.class);
                    tv.append(p.name+", "+p.age+", "+p.address+"\n");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
