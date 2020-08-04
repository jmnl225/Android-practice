package com.jmnl2020.ex91koptlinrecyclerviewapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_intro.*
//id 를 변수로 만들겠다!

class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        //버튼의 id가 btn.
        // 그 버튼에 클릭 리스너 추가하기
        btn.setOnClickListener(object:View.OnClickListener{
            override fun onClick(v: View?) {
                //Main Activity 실행
                var intent= Intent(this@IntroActivity, MainActivity::class.java)
                startActivity(intent)
            }
        })
        //btn. 을 하고 거기에 onClickListener를 함 - 오브젝트를 만들겠다 object:
        //

    }

    fun clickExit(view: View) {

        finish()
        //액티비티 종료

    }
}
