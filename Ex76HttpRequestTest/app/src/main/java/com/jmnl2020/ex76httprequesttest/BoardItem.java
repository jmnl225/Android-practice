package com.jmnl2020.ex76httprequesttest;

public class BoardItem {

    String no;
    String name;
    String message;
    String date;

    public BoardItem(String no, String name, String message, String date) {
        this.no = no;
        this.name = name;
        this.message = message;
        this.date = date;
    }

    public BoardItem() { //빈 생성자를 꼭 만들어놓기!!!!!!!!!!
    }
}
