package com.alextrotsenko.booksearch.di;

import com.alextrotsenko.booksearch.BookDetailsActivity;
import com.alextrotsenko.booksearch.BookSearchActivityFragment;
import com.nostra13.universalimageloader.core.ImageLoader;

import javax.inject.Singleton;

import dagger.Component;

/**
 * App-wide component.
 */
@Component(modules = {AppModule.class, NetworkModule.class})
@Singleton
public interface AppComponent {

    void inject(BookSearchActivityFragment bookViewModel);

    void inject(BookDetailsActivity bookViewModel);

    /**
     * Work-around: use getter as we need ImageLoader in the static method BookViewModel.loadImage().
     *
     * Use @Inject in all none-static methods.
     */
    ImageLoader getImageLoader();
}
