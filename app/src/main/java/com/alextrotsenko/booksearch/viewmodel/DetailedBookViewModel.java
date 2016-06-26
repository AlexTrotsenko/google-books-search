package com.alextrotsenko.booksearch.viewmodel;

import android.content.Context;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;

import com.alextrotsenko.booksearch.App;
import com.alextrotsenko.booksearch.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * contains binding for detailed information, which is present in DetailedEBookInfo.
 */
public class DetailedBookViewModel extends BookViewModel {

    public DetailedBookViewModel(Context context) {
        super(context);
    }

    @Bindable
    public CharSequence getDescription() {
        final String bookDescription = eBook.getBookInfo().getDescription();

        if (bookDescription == null) return null;

        return Html.fromHtml(bookDescription);
    }

    /**
     * Displays url into the given view.
     * If loading fails - displays placeholder image.
     *
     * @see #resetViewAndLoadImage(android.widget.ImageView, java.lang.String)
     */
    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView imageView, String url) {
        //dagger 2 can't @Inject into static, but this method is static because of data-binding.
        final ImageLoader imageLoader = App.getComponent().getImageLoader();

        imageLoader.displayImage(url, imageView, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                ((ImageView) view).setImageResource(R.drawable.book_placeholder_black_48dp);
            }
        });
    }
}
