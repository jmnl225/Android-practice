package com.jmnl2020.ex40sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText etName, etAge, etEmail;

    String dbName= "test.db";
    String tableName= "member";

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName= findViewById(R.id.et_name);
        etAge= findViewById(R.id.et_age);
        etEmail= findViewById(R.id.et_email);

        //dbName으로 데이터베이스 파일 열기 또는 생성하기
        //문서를 열면 그 DB를 제어하는 객체(SQLiteDatabase)를 리턴해줌
        db= openOrCreateDatabase(dbName, MODE_PRIVATE, null); //db기본값

        //db객체를 이용해서 DBMS시스템에 명령(SQL) 가능
        db.execSQL("create table if not exists "+tableName+"(num integer primary key autoincrement, name text not null, age integer, email text)");
        //테이블을 만들때 하나는 꼭 primary key가 있어야함
    }

    public void clickInsert(View view) {
        String name = etName.getText().toString();
        int age= Integer.parseInt(etAge.getText().toString());
        String email= etEmail.getText().toString();

        //data를 삽입하는 SQL명령
        db.execSQL("insert into "+tableName+"(name, age, email) values('" +name+ "', '"+age+"', '"+email+"')");

        etName.setText("");
        etEmail.setText("");
        etAge.setText("");

    }

    public void clickSelectAll(View view) {

        //where절이 없으면 모든 데이터(record)를 달라. ( horizontal data)
        Cursor cursor =db.rawQuery("SELECT * FROM "+tableName, null);
        //Cursor= 결과표에서 읽는 시작점
        //리턴된 결과표를 가리키는 Cursor객체를 이용해서 Data를 한 줄씩 (Row, Record) 이동하며 읽음
        if(cursor==null) return; //꼭 적어두기!

        StringBuffer buffer= new StringBuffer();

        while (cursor.moveToNext()){ //movetoNext가 반환하는 값이 false 일 때까지 반복
            //현재 가리키는 줄 의 각 칸들(column)의 값들을 얻어오기
            int num = cursor.getInt(0);
            String name= cursor.getString(1);
            int age= cursor.getInt(2);
            String email= cursor.getString(cursor.getColumnIndex("name"));
            // column 넘버를 직접쓰지 않고 이름으로 그 열을 찾아 사용 -> 저 자주 사용
            //인덱스는 절대값이 아니고, 전체를 불러오지 않고 일부만 가져왔을 때 처음 열이 0번

            buffer.append(num+":    "+name+"    "+age+"    "+email+"\n");

        }// while end.

        //누적한 결과 문자열데이터를 AlertDialog에 보여주기
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //메소드체인뭐시기 .. Builder의 리턴값은 Builder 본인이기에 계속 연결할 수 있음
        builder.setMessage(buffer.toString()).create().show();

//        cursor.getCount(); 테이블객수카운트
    }

    public void clickSbn(View view) {
        //db- member table에서 검색할 이름
        String name = etName.getText().toString();

        Cursor cursor = db.rawQuery("select name, email FROM "+tableName+" WHERE name=?", new String[]{
                name, "25"        });

        if(cursor==null) return;

        StringBuffer buffer = new StringBuffer();

        while( cursor.moveToNext() ){
            String name2= cursor.getString(0);
            String email= cursor.getString(1);

            buffer.append(name2+"    "+email+"\n");

        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(buffer.toString()).create().show();

    }

    public void clickUpdate(View view) {
        String name = etName.getText().toString();
        db.execSQL("UPDATE "+tableName+" SET age = 30", email='aa@aabfa.com' WHERE name=?,
        new String[]{name});
    }

    public void clickDel(View view) {
        String name = etName.getText().toString();
        db.execSQL("DELETE FROM mrmber+ WHERE name= ?, new String []{new}");
    }
}
