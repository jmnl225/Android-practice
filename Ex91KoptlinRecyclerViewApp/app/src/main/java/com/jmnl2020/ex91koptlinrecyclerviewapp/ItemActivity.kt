package com.jmnl2020.ex91koptlinrecyclerviewapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_item.*

class ItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        //인텐트객체 소환할 필요가 없다.
        // -> 인텐트가 이미 액티비티의 프로퍼티로 존재해서
        val title = intent.getStringExtra("title")
        val msg = intent.getStringExtra("msg")
        val img = intent.getIntExtra("img", R.drawable.moana03)

        //title은 제목줄에
        supportActionBar!!.title=title

        tv.text = msg
        Picasso.get().load(img).into(iv)

    }
}
