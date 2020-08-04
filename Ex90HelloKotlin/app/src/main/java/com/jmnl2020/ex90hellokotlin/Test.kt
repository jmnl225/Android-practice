package com.jmnl2020.ex90hellokotlin

// 코틀린 언어 기초문법

fun main(){


    // 1. 문장의
// 2. 변수를 만들때 자료형을 사용하지 않고
// 3. 코틀린은 함수형 언어: 함수를 객체처럼 생각ㄱ해서 변수에 저장하고 파라미터




// 1) 프로그램의 시작함수인 main 함수가 반드시 있어야함
// 2) 함수를 정의할 때 리턴타입 위치에 'fun; 키워드 사용
// fun main(){}
// 3) 화면 출력
    println("Hello World")

    // 4) 자료형과 변수
// 4.1 ) 기본자료형: Boolean, Chat, Bytem Long, Float, Double, String, Any [코틀린은 모든 변수가 객체. 즉, 모두 참조변수!
// 4.2) 변수 선언 문법 [var 키워드] ; 이 없으므로 변수의 초기화가 없으면 에러!
    //var a
// 4.3)
    var a = 10
    var b = 10.5
    var c = 3.14f
    var d = true
    var e = 'A'
    var f = "Hello"
    //자료형이 없는 게 아니라 대입될 때, 자료형이 결정남.

    //변수이므로 값 변경 가능
    a= 20
    b= 20.5

    println(a)

    //자료형이 있으므로 다른 자료형은 대입 불가
//    a= 3.14  ERROR :  Int -> Double
//    b= 20    ERROR :  Double -> Int - 자동형변환 안됨

    //명시적형변환 하는 방법 (자료형) 방법이 아님
    a= 3.14.toInt()

    //자료형을 변수선언시에 명시할 수 있음
    var aa:Int = 10
    var bb:Double = 3.14
    var cc:Boolean // 자료형을 명시하면 초기화를 안 해도 됨

    println(aa)
    println(bb)

    //좀 특별한 정수 표현식
    var a3= 10_000_000

    //화면출력의 format 만들기.
    println("이름"+"나이")
 //   println(10+"aaa"); //정수형이 자동 변환 안됨
    println (10.toString()+"aaa")

    //위에가 정신인데...
    println("aaa"+10) // 순서적으로 문자열이 먼저있으면 가능!
    println(""+10+"aaa")

    //즉, 이런 특징으로 인해 정수 숫자 2개 덧셈식 출력
    var num1 = 50
    var num2 = 30
    println(num1 + num2)

    println(num1.toString()+"+"+num2.toString()+"="+(num1+num2))

    println(""+num1+"+"+num2+"="+(num1+num2))

    // 위 두가지 모두 맘에 안 들어!
    // 마치 php처럼 ...

    println( "$num1 + $num2 = ${num1+num2} " )

    //종합하면..
    var name = "Sam"
    var age = 20
    println("이름 : $name \n나이 : $age")
    println()


    //Any타입 [ 타입이 없다는 표현식 ]
    var v: Any
    v= 10
    println(v)


    //가급적 AnyType 사용은 자제! : 자료형을 특정하기 어려울때만 사용.

    //null 값에 대한 특이점
    var  n = null
    println(n)

    //자료형을 명시하면 null 저장 불가
//     var n2:Int = null //ERROR
//    var s:String = null //ERROR

    //null 저장도 가능하다는 명시적 표기가 필요.
    var n3:Int? =null
    var s2:String? = null

    println(n3)
    println(s2)

    //상수
    val M = 10
//    M = 50 //M은 상수라서 ERROR

    //상수값 지정할 때 명시적 자료형을 사용하면
    val K:Int
    K=50
//    K=20 // 초기화가 아닌 값을 바꾸는건 불가능



    //5. 연산자 특이점
    // 숫자타입간에는 자동형변환 됨 [작은값 -> 큰 값]
    println(10+3.14)
    // 숫자 타입이 아닌 자료형과는 자동형변환 안됨
//    println(10+true) // ERROR

    // 새로운 연산자 is (자료형 검사)
    var n4 = 10
    if (n4 is Int){
        println("n4는 정수입니다.")
    }



//    // 다른 자료형 검사는 에러
//    if( n4 is String){
//
//    }

    //int형만 검사할 수 있다는 게 의미 없어 보이지만 Any 타입일 때 활용

    var obj : Any
    obj=10
    if(obj is Int) println("$obj 는 Int")
    if(obj is Double ) println("$obj 는 Double")

    // 자바의 instance of 처럼
    // 사용자정의 클래스
    class Person{
        //코틀린에서는 멤버변수라 => 속성 [property] 라고 명명
        //주의!!! 프로퍼티는 반드시 초기화가 되어있어야함.
        var name = "sam"
        var age:Int = 20

    }

    var p = Person() // new 키워드가 없음.
    if(p is Person){
        println(p.name +" "+p.age)
        println("  이름: ${p.name} , 나이: ${p.age}  ")
    }

    obj = Person() // 객체생성
    if(obj is Person){
    }

    // is 연산자의 사용법.


    //6. 제어문에서 특이한 점
//    var str= (10>5)?"aaa":"bbb" // 삼항연산자가 없음

    //대신 if 문을 삼항연산자처럼 사용
    var str = if(10>5)"aaa" else "bbb"

    println(str)

    str= if(10>5){
        "aaa"
        "kkk" //여러줄이 있으면 마지막값이 들어감. 둘 다는 안 됨!
    }else{
        "bbb"
    }
    println(str)

    //이런 특징때문에 if문을 코틀린에서는 제어문이아닌 if 표현식 이라고 부름

    //switch문법이 없어졌음 [비슷한 녀석은 있음]
    var h:Any ? = null

    var n5=30

    h= 5
    when(h){
        10-> println("h = 10")
        20-> println("bbb\n\n\n")
        "hello" -> println("Hello!")
        true -> println("ddd")
        //변수명으로 값 비교 가능
        n5-> println("eee")

        //2개 이상의 조건을 묶을 수도 있음
        40, 50 -> println("ffff")

        //default같은 역할
        //실행해야할 게 여러줄이면
        else -> {
            println("블라블라블라")
            println("fndkanklda")
        }
    }

    //when을 특정 수식으로 제어 가능
    h= 85
    when{ //수식비교를 할 때는 (h)를 붙이지 않는다!
        h>=90 && h<=100 -> println("A학점")
        h>=80 -> println("B학점")
        h>=70 -> println("C학점")
        h>=60 -> println("D학점")
        else -> println("Fail")
    }

    //when도 if문처럼 변수에 저장 가능함
    h=20
    var reseult = when(h){
        10 -> "hello"
        20 -> "nice"
        else -> "morning"
    }// 값을 대입할때는 반드시 else (default) 값이 있어야함!

    //when에서 is 연산자도 사용가능
    when(h){
        is Int-> println("Int 타입입니다.")
        is Double-> println("Double 타입입니다.")
        is Person-> println("Person 타입입니다.")
    }



    // 반복문
    //마치 for each문과 비슷하게

    for(i in 0..5){
        //??????????????? 이게 반복문
        // 0~5까지 i에 넣어줘
        println(i)
    }

    // i는 제어변수 = for문의 지역변수. - for문 바깥의 변수와 다름
    for(a in 3..10){
        println(a)
    }
    println()

    //반복문을 마지막숫자 전까지 가고 싶다면
    for( i in 0 until 10 ){
        println(i)
    }
    println()

    //2씩 증가 (STEP)
    for(i in 0..10 step 2){
        println(i)
    }

    //값의 감소
    for ( i in 10 downTo 0){
        println(i)
    }
    println()

    for (i in 10 downTo 0 step 2){
        println(i)
    }




    // 7. 배열 Array - 생성방법이 달라짐
    var arr = arrayOf(10,20,30)
    println(arr[0])
    println(arr[1])
    println(arr[2])


    //값 변경
    arr[0] = 100
    arr[1] = 200
    arr[2] = 300


    //배열의 특별한 점!
    // 마치 ArrayList 처럼 get(), set() 메소드 보유

    println(arr.get(0))
    println(arr.get(1))
    println(arr.get(2))

    println()

    arr.set(0, 200)
    arr.set(1, 300)
    arr.set(2, 500)

    println()


    //배열길이
    println("배열의 길이 ${arr.size}")
    println()

    //출력 반복문 이용
    for( n in arr ){ // 요소값들 반복
        println(n)
    }

    println()



    //반복문을 인덱스 값으로..
    for(i in arr.indices){
        println(""+i+" : "+arr[i])
    }
    println()



    //인덱스와 값을 같이 나오도록
    for( (i,v) in arr.withIndex() ){
        println(" [$i] : $v ")
    }
    println()


    //배열값 넣을 때 자료형을 명시하지 않으면
    //자동으로 Any 타입 설정

    var arr2 = arrayOf(10 ,"Hello", 3.14)
    for(t in arr2){
        println(t)
    }
    println()

    //Any 타입이여서 다른 자료형 대입 가능
    arr2[1] = 20
    for( t in arr2){
        println(t)
    }
    println()

    //단 , 배열에 타입 지정도 가능함
     var arr3= arrayOf<Int>(10,20,30)

    //타입을 지정하면 다른 자료형과 함께 사용 불가
 //   var arr4 = arrayOf<Int>(10,"Hello",true)

    //명시적 배열을 만드는 다른 방법
    var arr5= intArrayOf(10,20,30)
    var arr6 = doubleArrayOf(3.14, 1.52)



    //Bolean부터 Double까지 xxxArrayOf() - String은 없음

    //초기값없이 null 값으로 배열 만들기
    var arr7 = arrayOfNulls<Any>(5)
    for(t in arr7){
        println(t)
    }
    println()


    //배열 변수를 상수로 만들기
    val arr8 = arrayOf(10,20,30)
    arr8[0] = 100

//    arr8 = arrayOf(100, 200, 300) // 다른 배열 참조 불가

    //ArrayList 만들기

     val arrayList = arrayOf(10,20,30)
     arr8[0] = 100

    // 특정 위치에 추가하기
    //
    //
    //
    //
    // 기존에  처음 넣었던 자료형 외에는 추가불가능


    //인덱스 번호로 삭제하기


    //요소값
    // get, set도 있음
    // 삭제


    //2차원 배열..





    // 8. 함수

    // 8.1 기본 함수 호출(리턴, 파라미터 X)
    fun show(){
        println("show function")
        println()
    }


    //8.2 파라미터 전달하면서 함수 호출
    // : 매개변수 var키워드 사용금지
    fun output (a:Int, b: String){
        println(a)
        println(b)
        println()
    }


    //8.3 리턴받기 [리턴타입을 함수() 다음에 중활요 {사이에 : 하고작성}
//    fun add(x:Int, y:Int):Int{
//        println(x+y)
//    }


    //**참고 [return type
    fun display(){
        println("display")
    }








}// main end.


//8.1 에서 호출하는 함수
// 함수 정의시에는 무조건 fun
 fun show(){

    println("Show Function")
    println()

}

