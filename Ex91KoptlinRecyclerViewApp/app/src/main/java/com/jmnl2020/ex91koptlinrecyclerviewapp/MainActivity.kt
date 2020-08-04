package com.jmnl2020.ex91koptlinrecyclerviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //대량의 데이터 property [멤버변수]
    var items= ArrayList<ItemVO>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //원래는 서버에서 데이터를 읽어와야 하지만 시간상
        //그냥 하드코딩 대량의 데이터 추가

        items.add(ItemVO("HEllO","Message",R.drawable.moana03))
        items.add(ItemVO("Robin","Hello Kotlin",R.drawable.moana02))
        items.add(ItemVO("Pinn","Have a good day",R.drawable.moana01))
        items.add(ItemVO("Jake","wanna go home",R.drawable.moana03))
        items.add(ItemVO("Sam","giv me lotto!",R.drawable.moana04))
        items.add(ItemVO("HEllO","Message",R.drawable.moana05))
        items.add(ItemVO("Robin","Hello Kotlin",R.drawable.moana03))
        items.add(ItemVO("Pinn","Have a good day",R.drawable.moana02))
        items.add(ItemVO("Jake","wanna go home",R.drawable.moana01))
        items.add(ItemVO("Sam","giv me lotto!",R.drawable.moana04))

        //코틀린의 Recycler는 setAdapter를 사용하지 않고
        // RecyclerView 의 프로퍼티로서 adapter를 가지고 있음
        // 따라서 아답터를 굳이 멤버변수로 만들 필요가 없음!
        recycler.adapter = MyAdapter(this, items)


        //리사이클러뷰 레이아웃매니저 설정!
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

    override fun onResume() {
        super.onResume()

        //리사이클러뷰 갱신하기
        //recycler.adapter.notifyDataSetChanged()

        //recyclerView 안에는 adapter 라는 이름의 프로터피가 이미 존재.
        // 따라서 그 adapter가 정말 존재하는지(null) 체크한 후에 명령요청
        // 멤버변수가 null인지 체크

        // !!키워드의 툭징: 혹시 null이면 메소드를 실행저지어
        // 개발자들의 null point extends
        recycler.adapter!!.notifyDataSetChanged()

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.menu_aa -> Toast.makeText(this, "aa", Toast.LENGTH_SHORT).show()
            R.id.menu_bb -> Toast.makeText(this, "bb", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

}
