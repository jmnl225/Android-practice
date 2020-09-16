package com.jmnl2020.plantnews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Xml
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL

class MainActivity : AppCompatActivity() {

    //대량의 데이터
    var items = ArrayList<ItemVO>()

    //받아야할 정보들
    var getUrl:String = String()
    var getName:String = String()
    var getDate:String = String()
    var getTitle:String = String()

    //커스텀 어댑터
    var mAdapter:MyAdapter = MyAdapter(this, items)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //테스트
        //items.add(ItemVO("제1회 주간지","김가나","2020-09-16","www.naver.com")) //OK


        //리사이클러뷰 레이아웃매니저 설정
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        downloadData()

    }//onCreate

    fun downloadData(){
        //서버에서 데이터 불러오기
        Log.i("check test","download Data")

        var t = object :Thread(){
            override fun run() {
                super.run()

                var apiKey:String = "20200728GBYFTB84R50IWZXEIGE9A" //발급받은 키
                var address:String =  "http://api.nongsaro.go.kr/service/weekFarmInfo/weekFarmInfoList?"+"apiKey="+apiKey

                //url만들면서 URL과 연결될 인터넷 주소
                var url: URL = URL(address)

                var inputStream:InputStream = url.openStream()
                var isr:InputStreamReader = InputStreamReader(inputStream)

                //XML문서를 분석해주는 객체 소환
                var xpp: XmlPullParser
                //객체를 만들어주는 공장
                var factory:XmlPullParserFactory = XmlPullParserFactory.newInstance()
                xpp = factory.newPullParser()
                xpp.setInput(isr)

                Log.i("check test","파싱 준비 완료")

                //여기까지 파싱 준비

                //가져와야할 정보 : download url, writer, title, date

                var eventType:Int = xpp.eventType

                while (eventType != XmlPullParser.END_DOCUMENT ){

                    when (eventType){
                        XmlPullParser.START_DOCUMENT -> runOnUiThread(object :Runnable{
                            override fun run() {
                                Toast.makeText(this@MainActivity,"분석시작",Toast.LENGTH_SHORT).show()
                                Log.i("check test","파싱시작")
                            }
                        })

                        XmlPullParser.START_TAG -> {
                            var tagName: String = xpp.name

                            if(tagName == "downUrl"){
                                xpp.next()
                                getUrl=xpp.text
                            }else if(tagName == "regDt"){
                                xpp.next()
                                getDate=xpp.text
                            }else if(tagName == "subject"){
                                xpp.next()
                                getTitle=xpp.text
                            }else if(tagName == "writerNm"){
                                xpp.next()
                                getName = xpp.text
                            }
                        }//startTag

                        XmlPullParser.TEXT -> {}
                        XmlPullParser.END_TAG -> {
                            Log.i("check test","End Tag")
                            var tagName2 = xpp.name
                            if (tagName2 == "item" ){

                                runOnUiThread(object:Runnable{
                                    override fun run() {
                                        items.add(element = ItemVO(getTitle, getName, getDate, getUrl))
                                        mAdapter.notifyDataSetChanged()
                                    }
                                })

                            }
                        }

                    }//when

                    eventType = xpp.next()

                }//while

                runOnUiThread(object:Runnable{
                    override fun run() {
                        Toast.makeText(this@MainActivity, "분석이 완료되었습니다.",Toast.LENGTH_SHORT).show()
                    }
                })
            }//override
        }//Thread

        t.start()

    }//fun downloadData

}// MainActivity
