package com.wonokoyo.erpmus.database;

import com.wonokoyo.erpmus.util.Path;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Path.BASE_PATH)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static RhkServiceInterface rhkService() {
        return RetrofitInstance.getRetrofit().create(RhkServiceInterface.class);
    }
}
