package com.jmnl2020.ex79retrofittest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface RetrofitService {

    // 1. 단순하게 GET방식으로 json문자열 읽어오는 추상메소드
    @GET("/Retrofit/board.json")
    //MainActivity에서 builder.baseUrl에 기존 홈페이지 주소를
    // 적었기 때문에 그 뒤에 덧붙일 주소만 명시
    Call<BoardItem> getBoardJson(); //요청한 것을 받아 객체로 만들어야 할 때,
    // 그 객체가 어떤 것인지 지정
    //getBoardJson에는 네트워크랑 연결해서 자료를 얻어와야하는 코드를 모두 써두었음.
    //따라서 call 객체로 그 코드들을 잡아옴

    //2. 경로의 이름을 1번처럼 고정하지 않고 파라미터로 받아 지정할 수 있음 [@Path]
    @GET("/{folder}/board.json")
    Call<BoardItem> getBoardJsonByPath(@Path("folder") String folder);


    //3. GET방식으로 서버에 데이터전달 [@Query]
    @GET("/Retrofit/getTest.php")
    Call<BoardItem> getMethodTest(@Query("name") String name, @Query("msg") String msg);

    //4. GET방식으로 값을 전달하면서 경로 파일명도 지정가능
    @GET("/Retrofit/{filename}")
    Call<BoardItem> getMethodTest2(@Path("filename") String fileName,
                                   @Query("name") String name,
                                   @Query("msg") String msg);

    //5. GET 방식으로 하되 보낼 값들을 Map collection 한방에 전달하기! [@QuerryMap]
    @GET("/Retrofit/getTest.php")
    Call<BoardItem> getMethodTest3(@QueryMap Map<String, String> datas);

    //6. POST방식으로 데이터 보내기
    // 권장방식: [@Body] - 객체를 전달하면 자동으로 json문자열로 변환하여 Body데이터에 넣어 서버로 전송
    @POST("/Retrofit/postTest.php")
    Call<BoardItem> postMethodTest(@Body BoardItem item); //객체의 멤버변수를 빼내서 보내는 게 아니라 객체 자체로 보내기

    //7. POST방식에서 멤버값들을 GET방식처럼 별도로 보내고 싶을 때.
    // [@Field] - 단, 이 어노테이션은 반드시 @FormURLEncoded와 함께 써야함.
    @FormUrlEncoded
    @POST("/Retrofit/postTest2.php")
    Call<BoardItem> postMethod2(@Field("name") String name, @Field("msg") String msg);

    //8. 응답받을 데이터가 json배열일 때
    @GET("/Retrofit/boardArray.json")
    Call<ArrayList<BoardItem>> getBoardArray();
    //Call 안에 내가 받을 자료형을 적음. 그런데 그 것이 ArrayList임을 명시


    //9. baseUrl을 무시하고 지정된 url주소로 연결 [url]
    @GET
    Call<BoardItem> urlTest(@Url String url);

    // 10. 응답결과가 항상 json이었음. 그 결과를 항상 Gson을 이용해서 Board아이템 객체로 받았음.
    //만약 그냥 String으로 받고싶다면
    @GET("/Retrofit/board.json")
    Call<String> getJsonString();


}

