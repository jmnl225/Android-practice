package com.jmnl2020.ex91koptlinrecyclerviewapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_item.view.*

class MyAdapter constructor(val context:Context, val items: ArrayList<ItemVO>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val itemView = layoutInflater.inflate(R.layout.recycler_item, parent, false)
        val vh= VH(itemView)
        return vh
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh = holder as VH // 코틀린에서 형변환 하는 방법 : 연산자 as 를 사용

        //현재번째 아이템
        val item = items.get(position)
        vh.itemView.tvTitle.setText(item.title)
        //vh.itemView.tvMsg.setText(item.msg)
        //코틀린에서는 setXXX메소드를 싫어함.
        //멤버변수(property:속성)로 설정하는 것을 권장
        vh.itemView.tvMsg.setText(item.mgs)


        //glide 와 같은 역할을 하는 Picasso 라이브러리
        Picasso.get().load(item.img).into(vh.itemView.iv)

        //코틀린에서는 이 위치에서 itemView의 클릭이벤트 처리를 함
//        vh.itemView.setOnClickListener(object : View.OnClickListener{
//            override fun onClick(v: View?) {
//               //아이템 상세 정보화면으로 이동
//            }
//        })

    }

    //inner class ViewHolder 클래스 : itemView안의 뷰들을 관리하는 클래스
    inner class VH constructor(itemView: View) : RecyclerView.ViewHolder(itemView){

//        val tvTitle = itemView.tvTitle
//        val tvMsg = itemView.tvMsg
//        val iv = itemView.iv

        init {
            //자바에ㅓㅅ는 원래 이 생성자에서 getLayoutPosition() 메소드로
            //클릭한 아이템을 구별했음
            // 코틀립에서는 getLayourPosition()메소드 대신에
            //이 아답터에 멤버변수로 이미 layoutPositioni이 존재함
            //근데 layoutPutposition() 속성값이 언제나 항상 -1 !!!
            //그래서 이 곳에서 클릭 위치를 찾을수가 없다.

            itemView.setOnClickListener(object :View.OnClickListener{
                override fun onClick(v: View?) {
                    //여기서 상세 아이템 페이지

                    val intent = Intent(context, ItemActivity::class.java)
                    intent.putExtra("title", items.get(layoutPosition).title)
                    intent.putExtra("msg", items.get(layoutPosition).mgs)
                    intent.putExtra("img",items.get(layoutPosition).img)

                    context.startActivity(intent)

                }
            })



        }



    }



}
