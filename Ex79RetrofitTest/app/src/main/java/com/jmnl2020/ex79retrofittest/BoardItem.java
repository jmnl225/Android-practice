package com.jmnl2020.ex79retrofittest;

import com.google.gson.annotations.SerializedName;

public class BoardItem {

    String name;
    String msg;
    //여기서 사용하는 변수의 이름이 .json에서 사용하는 키 값(변수명)과 같아야함.

    public BoardItem() {
    }

    public BoardItem(String name, String msg) {
        this.name = name;
        this.msg = msg;

        //만약 json의 키 값과 다른 변수명을 사용하고 싶다면
//        @SerializedName("msg")
//                String message;
//
//        public void item(){
//
//        }

    }
}
