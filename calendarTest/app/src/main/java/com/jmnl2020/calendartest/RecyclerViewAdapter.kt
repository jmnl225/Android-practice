package com.jmnl2020.calendartest

import android.view.ViewGroup
import java.util.*

class RecyclerViewAdapter (val mainActivity: MainActivity) : RecyclerView.Adapter<ViewHolderHelper>(){


        val baseCalendar = BaseCalendar()

        init {
            baseCalendar.initBaseCalendar {
                refreshView(it)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderHelper {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false)
            return ViewHolderHelper(view)
        }

        override fun getItemCount(): Int {
            return BaseCalendar.LOW_OF_CALENDAR * BaseCalendar.DAYS_OF_WEEK
        }

        override fun onBindViewHolder(holder: ViewHolderHelper, position: Int) {

            if (position % BaseCalendar.DAYS_OF_WEEK == 0) holder.tv_date.setTextColor(Color.parseColor("#ff1200"))
            else holder.tv_date.setTextColor(Color.parseColor("#676d6e"))

            if (position < baseCalendar.prevMonthTailOffset || position >= baseCalendar.prevMonthTailOffset + baseCalendar.currentMonthMaxDate) {
                holder.tv_date.alpha = 0.3f
            } else {
                holder.tv_date.alpha = 1f
            }
            holder.tv_date.text = baseCalendar.data[position].toString()
        }

        fun changeToPrevMonth() {
            baseCalendar.changeToPrevMonth {
                refreshView(it)
            }
        }

        fun changeToNextMonth() {
            baseCalendar.changeToNextMonth {
                refreshView(it)
            }
        }

        private fun refreshView(calendar: Calendar) {
            notifyDataSetChanged()
            mainActivity.refreshCurrentMonth(calendar)
        }
}