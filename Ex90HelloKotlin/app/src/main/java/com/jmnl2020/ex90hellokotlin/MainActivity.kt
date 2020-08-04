package com.jmnl2020.ex90hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
/// import kotlinx.android.synthetic.main.activity_main.* 가 되어 있어야 함!!
//

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun clickBtn(view: View) {
        // 리턴함수: fun / 변수명 view : 자료형 View
        // XML에서 뷰에 지정한 id를 변수명으로 사용

        tv.setText("Nice to meet you, Kotlin!")

    }

    //overide 메소드는 반드시 fun 앞에 'override'키워드가 있어야함

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT)
    }
}
