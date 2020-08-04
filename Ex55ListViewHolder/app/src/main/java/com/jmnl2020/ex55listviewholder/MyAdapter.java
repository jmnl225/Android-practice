package com.jmnl2020.ex55listviewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    ArrayList<String> items;
    Context context;

    //생성자
    public MyAdapter(ArrayList<String> items, Context context) {
        this.items = items;
        this.context = context;
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //ViewGroup parent -> card view가 누구에게 붙는지 생각 - > listview

        if(convertView==null){
            //없다면 만들어라!
            LayoutInflater inflater = LayoutInflater.from(context);
            //layout inflater를 갖고있는 context로부터 가져옴

            convertView = inflater.inflate(R.layout.listview_item, parent, false);
            //리턴해서 붙여야하기떄문에 지금 붙이지 않음!

            //참조변수를 멤버로 가지고있는 객체 생성
            ViewHolder holder = new ViewHolder( convertView );
            convertView.setTag(holder);

        }
        /////////////////////
        //재활용할 뷰에 저장되어있는 tag객체를 얻어오기
        ViewHolder holder = (ViewHolder) convertView.getTag(); // 가능한 게 너무 많아서 어떤 자료형인지 몰라 에러. 형변환을 해줘야함
        /////////////////
        //2. 뷰 객체에 값들을 연결하기 (binding view)

        TextView tv= convertView.findViewById(R.id.tv);
        //textview를 갖고있는 cardview 에게 id를 찾아달라고 부탁함. !!!

        //텍스트뷰에 넣을 현재번째(position) 데이터
        String s= items.get(position);
        holder.tv.setText(s);

        return convertView;
    }

    //이너클래스
     class ViewHolder{
        TextView tv;

        public ViewHolder(View itemView){
            tv = itemView.findViewById(R.id.tv);
        }

    }

}
