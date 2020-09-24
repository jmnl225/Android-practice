package com.jmnl2020.plantnewsresourceparsing

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

class MyAdapter constructor(val context: Context, val items:ArrayList<ItemVO>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        //레이아웃 생성
        val layoutInflater:LayoutInflater = LayoutInflater.from(context)
        val itemView = layoutInflater.inflate(R.layout.item, parent, false)
        val vh = VH(itemView)

        return vh
    }

    override fun getItemCount(): Int {
        //배열의 개수만큼 만들기
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //바인딩
        val vh = holder as VH

        //현재번째 아이템을 가져오기
        val item = items.get(position)

        //받아온 item에 데이터 연결
        vh.itemView.tv_title.text = item.title
        vh.itemView.tv_writer.text = item.writer
        vh.itemView.tv_date.text = item.date
        vh.itemView.tv_downloadUrl.text = item.url

    }

    //ViewHolder innerclass
    inner class VH constructor(itemView:View) : RecyclerView.ViewHolder(itemView){

        var tvTitle = itemView.tv_title
        var tvWriter = itemView.tv_writer
        var tvDate = itemView.tv_date
        var tvUrl = itemView.tv_downloadUrl

    }

}