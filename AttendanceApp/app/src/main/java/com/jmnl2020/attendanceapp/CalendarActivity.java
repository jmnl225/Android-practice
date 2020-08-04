package com.jmnl2020.attendanceapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity {

    TextView tvData;
    GridView gridView;
    GridAdapter gridAdapter;

    ArrayList<String> dayList;

    Calendar mCal; //자바cal

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calendar);

        tvData = findViewById(R.id.tv_date);
        gridView = findViewById(R.id.gridview);

        //오늘의 날짜 세팅
        long now = System.currentTimeMillis();
        final Date date = new Date(now);

        //연, 월, 일 따로저장
        final SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
        final SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);
        final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);

        //현재 날짜 텍스트뷰에 넣어주자
        tvData.setText(curYearFormat.format(date) + "/" + curMonthFormat.format(date) );

        //gridview에 요일표시
        dayList = new ArrayList<String>();
        dayList.add("일");
        dayList.add("월");
        dayList.add("화");
        dayList.add("수");
        dayList.add("목");
        dayList.add("금");
        dayList.add("토");

        mCal= Calendar.getInstance();

        //이번달 1일이 무슨 요일인지 계산 mCal.set(Year, Month, Day)
        mCal.set(Integer.parseInt(curYearFormat.format(date)),
                Integer.parseInt(curMonthFormat.format(date)) -1, 1);

        int dayNum = mCal.get(Calendar.DAY_OF_WEEK);
        //1일 - 요일 매칭시키기위해 공백 add

        for(int i=1; i < dayNum; i++){
            dayList.add("");
        }
        setCalendarDate(mCal.get(Calendar.MONTH) + 1);

        gridAdapter = new GridAdapter(this, dayList);
        gridView.setAdapter(gridAdapter);

    }


    public void setCalendarDate(int month){
        mCal.set(Calendar.MONTH, month -1);

        for (int i =0; i<mCal.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
            dayList.add(""+(i+1));
        }

    }


    public class GridAdapter extends BaseAdapter{

        List<String> list;
        LayoutInflater inflater;

        public GridAdapter(Context context, List<String> list) {
            this.list = list;
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder= null;

            if(convertView == null){
                convertView = inflater.inflate(R.layout.item_calendar_gridview, parent, false);
                holder = new ViewHolder();

                holder.tvItemGridView = (TextView) convertView.findViewById(R.id.tv_item_gridview);

                convertView.setTag(holder);

            }else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tvItemGridView.setText(""+ getItem(position));

            //해당 날짜 텍스트 컬러, 배경
            mCal= Calendar.getInstance();

            //오늘 day 세팅
            Integer today = mCal.get(Calendar.DAY_OF_MONTH);
            String sToday = String.valueOf(today);

            if(sToday.equals(getItem(position))){
                //오늘이 포지션과 같은 날일때!
                holder.tvItemGridView.setTextColor(getResources().getColor(R.color.colorAccent));
            }

            return convertView;
        }
    }

    public class ViewHolder{
        TextView tvItemGridView;
    }

}
