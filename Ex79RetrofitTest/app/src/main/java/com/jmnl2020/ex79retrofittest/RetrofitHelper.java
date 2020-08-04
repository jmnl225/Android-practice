package com.jmnl2020.ex79retrofittest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    public static Retrofit getRetrofitInstance(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("http://jmnl.dothome.co.kr");
        builder.addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit= builder.build();

        return retrofit;

    }

}
