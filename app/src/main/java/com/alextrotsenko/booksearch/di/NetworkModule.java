package com.alextrotsenko.booksearch.di;

import android.content.Context;
import android.support.annotation.NonNull;

import com.alextrotsenko.booksearch.rest.MetaData;
import com.alextrotsenko.booksearch.rest.services.GoogleBookService;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Contains API for getting data from network (Google Books): json data and images.
 */
@Module
public class NetworkModule {

    @Provides
    @NonNull
    @Singleton
    public ImageLoader provideImageLoader(Context appContext) {
        final DisplayImageOptions imageOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .resetViewBeforeLoading(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                .build();

        final ImageLoaderConfiguration imageConfig = new ImageLoaderConfiguration.Builder(appContext)
                .threadPriority(Thread.MIN_PRIORITY)
                .defaultDisplayImageOptions(imageOptions)
                .denyCacheImageMultipleSizesInMemory()
                .build();

        final ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(imageConfig);

        return imageLoader;
    }

    @Provides
    @NonNull
    @Singleton
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(MetaData.GOOGLE_BOOKS_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @NonNull
    @Singleton
    public GoogleBookService provideGoogleBookService(Retrofit retrofit) {
        return retrofit.create(GoogleBookService.class);
    }
}
