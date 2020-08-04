package com.jmnl2020.ex90hellokotlin

fun main(){
    //코틀린 상속
    var obj = Second()
    obj.show() //오버라이드된 Second의 show()

    // 상속의 최종연습
    val p = Person ("sam", 20)
    p.show()

    val s = Student("robin", 25, "android")
    s.show()

    val pro = Professor("hong", 50, "optimization")
    pro.show()

    val alba = AlbaStudent("Kim", 28, "web", "managment")
    alba.show()

}

open class First{

    var a: Int = 10

    //오버라이드 해줄 메소드는 반드시 open 키워드 필요
    //즉, open이 없으면 자바의 final method와 같은 것

    open fun show(){
        println(" a: $a")
    }

}


//상속의 문법 [ 클래스명 뒤에: 후 부모클래스명() 작성 <- 부모클래스명 뒤에 주생성자 호출() 주의! ]
class Second : First() {
     var b:Int = 20

    //상속받은 메소드의 기능을 변경: override
    //기존 메소드와 같은 이름과 파라미터로 만든 메소드
    // 오버라이드를 하려면 반드시 override 키워드 추가

    override fun show(){
        super.show() // 부모의 출력은 부모가 알아서 하도록
        println("b : $b")
    }

}
