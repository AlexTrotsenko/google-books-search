package com.alextrotsenko.booksearch.utils;

import com.alextrotsenko.booksearch.rest.MetaData;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Network-related utility class.
 */
public class NetworkHelper {

    private static Retrofit retrofit;

    private NetworkHelper() {
    }

    //todo replace with Dependency Injection
    public synchronized static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(MetaData.GOOGLE_BOOKS_BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
