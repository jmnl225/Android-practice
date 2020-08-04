package com.jmnl2020.ex90hellokotlin

//보조 생성자로 해보기!! - 자바와 비슷한 느낌
class Professor : Person {

    var subject:String? = null

    //보조생성자는 파라미터로 멤버변수를 만들 수 없음
    constructor(name:String, age:Int, subject:String): super(name, age){
        this.subject = subject
        println("Professor 생성")
    }

    override fun show(){
        println("name : $name , age : $age , subject : $subject")
    }

}