package com.jmnl2020.ex89youtubeapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {

    // 요청 파라미터 : key(필수), part(필수), q(검색어 지정) , maxResults(결과 개수 지정: 0-50 / default: 5)
    @GET("/youtube/v3/search")
    Call<String> searchVieos(@Query("key") String key,
                             @Query("part") String part,
                             @Query("q") String q,
                             @Query("maxResults") int maxResults);

}
