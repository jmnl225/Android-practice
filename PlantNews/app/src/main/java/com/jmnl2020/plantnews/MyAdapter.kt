package com.jmnl2020.plantnews

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*
import kotlin.String as String

class MyAdapter constructor(val context: Context, val items: ArrayList<ItemVO>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //Log.i("test","뷰홀더 생성")
        val layoutInflater:LayoutInflater = LayoutInflater.from(context)
        val itemView = layoutInflater.inflate(R.layout.item, parent, false)
        val vh = VH(itemView)

        return  vh
    }

    override fun getItemCount(): Int {
        //Log.i("test","아이템 사이즈"+items.size)
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh = holder as VH //형변환

        val item = items.get(position)
        Log.i("test","포지션"+position)

        //코틀린은 set 말고 속성으로 설정.
        vh.itemView.tv_title.text = item.title
        vh.itemView.tv_writer.text = item.writer
        vh.itemView.tv_date.text = item.date
        vh.itemView.tv_downloadUrl.text = item.url


        //item.writer

//        item.title = vh.tvtitle as String
//        item.writer = vh.tvwriter as String
//        item.date = vh.tvdate as String
//        item.url = vh.tvurl as String

//        vh.tvtitle = item.title
//        vh.tvwriter
//        vh.tvdate
//        vh.tvurl


    }

    //ViewHolder 이너클래스 = itemView안의 뷰들을 관리하는 클래스
    inner class VH constructor(itemView: View) : RecyclerView.ViewHolder(itemView){

        var tvtitle = itemView.tv_title
        var tvwriter = itemView.tv_writer
        var tvdate = itemView.tv_date
        var tvurl = itemView.tv_downloadUrl

    }

}