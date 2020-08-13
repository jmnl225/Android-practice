package com.jmnl2020.bookbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var items:ArrayList<Item> = ArrayList()
    var mAdapter:RecyclerAdapter = RecyclerAdapter(this, items)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        items.add(Item("책읽는시간",R.drawable.newyork2, "홍길동", "읽은이", "지금은 책을 읽는 시간입니다."))

        //코틀린의 Recycler는 setAdapter를 사용하지 않고
        // RecyclerView의 프로퍼티로서 adapter를 가지고있다.
        // 따라서 아답터를 굳이 멤버변수로 만들 필요가 없음.

        recycler.adapter = RecyclerAdapter(this, items)

        //리사이클러뷰 레이아웃매니저 설정.
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


    }//onCreate View end.

    override fun onResume() {
        super.onResume()

        // 키워드 !! : 혹시 null이면 메소드를 실행하지 말 것
        recycler.adapter!!.notifyDataSetChanged()

    }




}
