package com.jmnl2020.plantnews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_item.view.*

class MyAdapter constructor(val context: Context, val items: ArrayList<ItemVO>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater:LayoutInflater = LayoutInflater.from(context)
        val itemView:View = layoutInflater.inflate(R.layout.activity_item, parent, false)
        val vh = VH(itemView)

        return  vh
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh:MyAdapter.VH = holder as VH //형변환
    }

    //ViewHolder 이너클래스 = itemView안의 뷰들을 관리하는 클래스
    inner class VH constructor(itemView: View) : RecyclerView.ViewHolder(itemView){

        var tvTitle = itemView.tv_title
        var tvWriter = itemView.tv_writer
        var tvDate = itemView.tv_date
        var tvUrl = itemView.tv_downloadUrl

        init {
            // java의 getLayoutPosition이 이 생성자에 있음.
            // layoutPutPosition()속성값이 항상! -1 !!
        }
    }

}