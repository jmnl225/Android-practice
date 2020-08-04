package com.jmnl2020.ex90hellokotlin

fun main(){
    //안드로이드에 가장 많이 사용되는 이너클래스 / 인터페이스 / 익명클래스

    //1. 이너클래스는 아웃터객체만 생성할 수 있음
    val obj = AAA()
    val b = obj.getBBBInstance()
    b.show()

    //2. 인터페이스 [추상메소드만 보유한 클래스]
    //val obj3= ClickListener() // 인터페이스는 직접 객체생성 불가
    //인터페이스를 사용하려면 인터페이스를 구현한 클래스를 만들어서
    // 그 클래스 안에서 인터페이스의 추상메소드를 오버라이드 해야함

    val obj3 = Clicker()
    obj3.onClick()



    //3. 익명클래스 [Java 문법이 완전 다름 : object 키워드 사용]
    val obj4 = object  : ClickListener{
        override fun onClick() {
            println("Anonymous class onClick!!")
        }
    }

    obj4.onClick()

}//main end.

//인터페이스는 특별할 것이 없음
interface ClickListener{
    //추상메소드
    fun onClick()
}

//인터페이스를 구현한 클래스
class Clicker : ClickListener{
    override fun onClick() {
        println("click!!")
        println()
    }
}


// 이너클래스 : 클래스 안에 있는 클래스
class AAA{
    var a: Int = 0

    fun show(){
        println("AAA 클래스의 show")
        println()
    }

    //이너클래스 객체를 생성하여 리턴해주는 메소드
    fun getBBBInstance(): BBB{
        val obj=BBB() // 아웃터는 이너클래스 객체 생성 가능
        return obj
    }

    inner class BBB{
        fun show(){
            println("아우터 클래스의 프로퍼티 a : $a ")// 내것인양 사용

            //이너 안에서 아웃터의 this 사용
            this@AAA.show()
        }
    }


}