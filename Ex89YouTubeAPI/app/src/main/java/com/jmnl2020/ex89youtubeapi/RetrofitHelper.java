package com.jmnl2020.ex89youtubeapi;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitHelper {

    public static Retrofit getInstance(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("https://www.googleapis.com");
        builder.addConverterFactory(ScalarsConverterFactory.create());

        return builder.build();
    }

}
