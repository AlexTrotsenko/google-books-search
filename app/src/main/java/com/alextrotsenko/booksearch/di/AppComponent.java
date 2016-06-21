package com.alextrotsenko.booksearch.di;

import com.alextrotsenko.booksearch.BookSearchActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by alex on 21/06/16.
 */
@Singleton
@Component(modules={AppModule.class})
public interface AppComponent {
    void inject(BookSearchActivity activity);
}
