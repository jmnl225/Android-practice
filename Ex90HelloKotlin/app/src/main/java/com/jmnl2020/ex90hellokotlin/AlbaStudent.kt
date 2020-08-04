package com.jmnl2020.ex90hellokotlin

//constructor 키워드 생략가능
class AlbaStudent (name: String, age:Int, major:String, var task:String) : Student(name, age, major) {

    init {
        println("AlbaStudent 생성")
    }

    override fun show() {
        //super.show()
        println("name: $name , age: $age, major: $major, task: $task")
    }

}