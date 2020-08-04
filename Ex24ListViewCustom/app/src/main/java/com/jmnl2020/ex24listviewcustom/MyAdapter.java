package com.jmnl2020.ex24listviewcustom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {


    ArrayList<Member> members;

    LayoutInflater inflater;

    //생성자메소드: 대량의 데이터를 전달받기
    public MyAdapter(ArrayList<Member> members, LayoutInflater inflater){

        //전달받은 데이터를 멤버변수에 대입시키기!
        this.members= members;

        // 받은 inflater 를 대입하자
        this.inflater= inflater;

    }

    //이 Adapter가 만들어야하는 총 아이템개수를 리턴하는 메소드
    @Override
    public int getCount() {
        return members.size();
        //members.size 만큼 넣어줘!
    }

    //position번째의 데이터를 리턴해줌
    @Override
    public Object getItem(int position) {
        return members.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position; //Id가 식별자. 다른 id를 넣어도 되지만 보통 position과 같은 값을 입력
    }

    //뷰를 만들어서 Adpter뷰에게 리턴하는 메소드
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //new View를 해서 만들어진 순간 position 번째를 소환(getView) 함

        //1. new View

        //=============잠깐!!================ / 다 만들었는데.... #1
        //화면에 보이는 view는 한정되어있음. 그런데 내려갈수록 계속 새로 만들면 메모리가 불어나! -> 재활용하자!
        // #3 View converView 두번째 파라미터가 이걸 관리.
        //재활용할 뷰가 없냐?
        if( convertView== null){ //null이 아니면 재활용
            convertView= inflater.inflate(R.layout.listview_item, null);
            //inflater야 만들어줘~ 그치만 지금 붙이면 안댐 null;
        }

        //뷰를 설계해둔 xml 파일을 가져와서 객체로 만들기!
        // 경로: res/layout/listview_item.xml 문서를 읽어와서 View객체로 만들어주는 객체소환 ><~!

//        LayoutInflater inflater=
        //*                layoutinflater = 운영체제의 기능.  *!!!==========================================

        // ! ! ! 운영체제 기능이 이  java에는 없음!! 따라서 MainActivity에서 가져옴~~ ^^! 역시 인맥빨
        // 받은 inflater를 전역변수에 대입시켜서 그걸 이쪽으로 가져옴!

//  #2 지워     View itemView= inflater.inflate(R.layout.listview_item, null);
        //getView 메소드가 직접 붙이는 기능을 갖고있음. 따라서 만들어서 붙이면 안 됨! 그러므로 null입력


        //2. binding : Set value in View
        //만들어진 뷰 안의 내용을 바꿔서 넣기
        ImageView iv= convertView.findViewById(R.id.iv);
        TextView tvName = convertView.findViewById(R.id.tv_name);
        TextView tvNation= convertView.findViewById(R.id.tv_nation);

        Member member = members.get(position);
        tvName.setText( member.name );
        tvNation.setText( member.nation );
        iv.setImageResource(member.imgId);

        return convertView;
    }
}
