package com.alextrotsenko.booksearch;

import android.app.Application;

import com.alextrotsenko.booksearch.di.AppComponent;
import com.alextrotsenko.booksearch.di.AppModule;
import com.alextrotsenko.booksearch.di.DaggerAppComponent;
import com.alextrotsenko.booksearch.di.NetworkModule;

/**
 * Used for DI
 */
public class App extends Application {
    private static AppComponent component;

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        component = buildComponent();
    }

    private AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }
}
