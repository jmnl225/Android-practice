package com.jmnl2020.plantnewsresourceparsing

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL

class MainActivity : AppCompatActivity() {

    //데이터 저장할 배열
    var items = ArrayList<ItemVO>()

    //받아야할 정보들
    var getUrl:String = String()
    var getTitle:String = String()
    var getName:String = String()
    var getDate:String = String()

    //커스텀 어댑터
    var mAdapter:MyAdapter = MyAdapter(this, items)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //테스트
        //items.add(ItemVO("제1회 주간지","김가나","2020-09-16","www.naver.com")) //확인

        //어댑터 설정
        recycler.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
        recycler.adapter = mAdapter

        //데이터 파싱


    }//onCreate end.

    fun dataParsing(){
        //1. 서버에서 데이터 가져오기 -> 인터넷연결 Thread

        var t = object:Thread(){
            override fun run() {
                super.run()

//                var apiKey:String = "20200728GBYFTB84R50IWZXEIGE9A" //발급받은 키
//                var address:String =  "http://api.nongsaro.go.kr/service/weekFarmInfo/weekFarmInfoList?"+"apiKey="+apiKey
//
//                //url만들면서 URL과 연결될 인터넷 주소
//                var url: URL = URL(address)
//

            }
        }
    }

}
