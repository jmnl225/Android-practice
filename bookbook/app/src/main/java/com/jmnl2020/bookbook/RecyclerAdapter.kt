package com.jmnl2020.bookbook

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Picasso.get
import kotlinx.android.synthetic.main.item.view.*

class RecyclerAdapter(val context: Context, val items: ArrayList<Item>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater:LayoutInflater = LayoutInflater.from(context)
        val itemView:View = layoutInflater.inflate(R.layout.item, parent, false)
        val vh = VH(itemView)

        return vh;
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh: VH = holder as VH
        //코틀린에서 형변환 하는 방법: 연산자 as 사용

        //현재번째 아이템
        val item:Item = items.get(position)

        //set 말고! 멤버변수로 설정하는 것을 권장
        vh.itemView.tv_title.setText(item.title)
        vh.itemView.desc.setText(item.desc)
        vh.itemView.writer.setText(item.writer)
        vh.itemView.player.setText(item.players)
        //vh.itemView.img.setImageResource(item.img!!)

        Picasso.get().load(item.img!!).into(vh.itemView.iv)

    }

    //inner class ViewHolder 클래스 : itemView안의 뷰들을 관리하는 클래스
    inner class VH constructor(itemView: View):RecyclerView.ViewHolder(itemView){

        init {
            //자바에서는 원래 이 생성자에서 getLayoutposition()메소드로 클릭한 아이템을 구별함.
            // 코틀린에서는 getLayoutPosition()메소드 대신
            // 이 아답터에 멤버변수로 이미 layoutPosition이 존재함.
            // 그런데! layoutPosition() 속성값이 언제나! 항상! -1
            //따라서. 이 곳에서 클릭 위치를 찾을 수가 없다.

            itemView.setOnClickListener(object : View.OnClickListener{
                override fun onClick(v: View?) {
                    //여기서 상세 아이템 페이지 만들기

                    val intent = Intent(context, ChattingActivity::class.java)
                    intent.putExtra("title", items.get(layoutPosition).title)
                    intent.putExtra("desc", items.get(layoutPosition).desc)
                    intent.putExtra("writer", items.get(layoutPosition).writer)
                    intent.putExtra("players", items.get(layoutPosition).players)
                    intent.putExtra("img", items.get(layoutPosition).img)


                }
            })

        }

    }

}

