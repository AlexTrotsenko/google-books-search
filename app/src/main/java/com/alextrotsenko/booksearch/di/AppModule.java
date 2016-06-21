package com.alextrotsenko.booksearch.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provides app context
 */
@Module
public class AppModule {

    Context appContext;

    public AppModule(Context appContext) {
        this.appContext = appContext;
    }

    @Provides
    @Singleton
    public Context provideAppContext() {
        return appContext;
    }
}