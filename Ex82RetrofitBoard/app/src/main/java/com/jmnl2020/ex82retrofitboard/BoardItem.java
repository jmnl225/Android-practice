package com.jmnl2020.ex82retrofitboard;

//서버에서 읽어온 게시글 market테이블의 한 record(row) 의 데이터를 저장하는 vo클래스
public class BoardItem {

    int no;         //저장된 아이템 번호
    String name;    // 작성자
    String title;   // 제목
    String msg;     // 내용
    String price;   // 가격
    String file;    //업로드 이미지 파일 경로
    int fav; //좋아요 여부 [mysql에 true/false를 1,0 으로 대체하여 저장]
    String date;    //작성 일자

    public BoardItem() {
    }

    public BoardItem(int no, String name, String title, String msg, String price, String file, int fav, String date) {
        this.no = no;
        this.name = name;
        this.title = title;
        this.msg = msg;
        this.price = price;
        this.file = file;
        this.fav = fav;
        this.date = date;
    }
}
