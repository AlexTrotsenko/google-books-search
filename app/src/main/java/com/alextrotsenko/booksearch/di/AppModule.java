package com.alextrotsenko.booksearch.di;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provides app context.
 */
@Module
public class AppModule {

    final private Context appContext;

    public AppModule(@NonNull Context appContext) {
        this.appContext = appContext;
    }

    @Provides
    @NonNull
    @Singleton
    public Context provideAppContext() {
        return appContext;
    }
}
