package com.jmnl2020.ex90hellokotlin

fun main(){

    println("========================   시작   ===========================")

    //1. 클래스 선언 및 객체 생성 : new 키워드는 없음
    var obj = MyClass()
    obj.show()

    //1.1 다른 .kt파일에 class만들기 [당연히 확장자 .kt]
    var obj2= MyKtClass()
    obj2.show()


    //2. *** 생성자 ***
    // 코틀린에서는 생성자가 두 종류
    // 1) 주생성자
    // 2) 보조생성자

    //2-1. 주생성자 [Primary Constructor]
    var s = Simple()

    //2.2 주생성자에 값 전달하기
    var s2= Simple2(10, 20)

    //2.3 만약 생성자 오버로딩을 구현하고싶다면
    //보조생성자 활용 [c, java와 흡사한 결과)
    var s3 = Simple3()
    var s4 = Simple3(100) // 오버로딩 호출

    //2.4 주생성자와 보조생성자 동시사용 [ 주 생성자를 만들어놓고 나중에 오버로딩할때 ]
    var s5 = Simple4() //주생성자 호출
    var s6 = Simple4(200) // 오버로딩된 보조생성자 호출


    //참고
    var s7 = Simple5()



}



//2.4 주 생성자 + 보조생성자
class Simple4 constructor(){
    init {
        println("Simple4 init")
    }
    // 주 생성자가 불러짐

    //보조생성자 추가하고싶다면 명시적으로 주생성자를 호출하는 코드가 옆에 있으면 됨
    constructor(num: Int):this(){
        println("Simple4 Secondary constructor")
    }
}


//2.3  보조 생성자 [클래스 안에 construct()블럭 구현]
//보조생성자만 구현해도 주생성자는 원래부터 존재하고 있는 것으로 간주
class Simple3{
    //init
    init {
        println("여기는 객체 처음 만들때 무조건 실행")
    }

    //보조생성자
    constructor(){
        println("Simple3의 Secondary 생성자")
        println()
    }

    //보조생성자는 오버로딩이 가능
    //보조생성자는 var키워드로 한번에 멤버변수 만들기 불가
    constructor(num: Int){
        println("Simple3 의 Secondary constructor : $num")
        println()
    }

}


//2.2 주생성자 파라미터 받기 [주생성자는 오버로딩 불가]

class Simple2 constructor(var num:Int, num2:Int){//var키워드를 사용하면 그 매개변수는 멤버변수가 됨
    //num2 는 var이 없으므로 매개변수(지역변수)

    init {
        println("Simple2 생성")
        println("num: $num")
        println("num2 : $num2")
    }

    fun show(){
        println("show method num: $num")
//        println("show method num: $num2") //num2 는 주 생성자의 지역변수
    }

    //멤버변수에 매개변수값 대입을 생성자 블럭에서 안 해도 됨!! -----------------------------------------------
    var n:Int= num2

}


//2.1 주생성자 : 클래스명 옆에 constructor() 라고 명시
class Simple constructor(){
    //주생성자는 {}가 없기에 코드 작성할 곳이 없음
    //주생성자를 위한 별도의 초기화

    init {
        println("심플! 주 생성자 실행!!")
    }

}



//1. 클래스 선언 [멤버변수(Field)를 property[프로퍼티: 속성] 이라고 부름]
class MyClass{
    //멤버변수[property] - 반드시 초기화 되어있어야 함
    var a:Int = 10

    //메소드 : Method
    fun show(){
        println("show: ${a}")
        println()
    }

}



//참고로 constructor키워드 접근제한자나 어노테이션이 없다면 생략 가능
class Simple5(){
    init {
        println("Simple5 주 생성자")
    }

}