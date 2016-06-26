package com.alextrotsenko.booksearch.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.alextrotsenko.booksearch.App;
import com.alextrotsenko.booksearch.BookDetailsActivity;
import com.alextrotsenko.booksearch.R;
import com.alextrotsenko.booksearch.rest.dto.EBookInfo;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * View model for EBookInfo
 */
public class BookViewModel extends BaseObservable {
    private Context context;
    protected EBookInfo eBook;

    public BookViewModel(Context context) {
        this.context = context;
    }

    @Bindable
    public String getTitle() {
        return eBook.getBookInfo().getTitle();
    }

    @Bindable
    public String getThumbnailLink() {
        return eBook.getBookInfo().getThumbnailLink();
    }

    /**
     * Displays url into the given view.
     * But first of all it rest current view image to placeholder (as it might be re-used!).
     *
     * @see DetailedBookViewModel#loadImage(android.widget.ImageView, java.lang.String)
     */
    @BindingAdapter("imageUrlSafe")
    public static void resetViewAndLoadImage(ImageView imageView, String url) {
        //dagger 2 can't @Inject into static, but this method is static because of data-binding.
        final ImageLoader imageLoader = App.getComponent().getImageLoader();

        imageView.setImageResource(R.drawable.book_placeholder_black_48dp);

        imageLoader.displayImage(url, imageView);
    }

    public void setEBook(EBookInfo eBook) {
        this.eBook = eBook;
        notifyChange();
    }

    public void openBookDetails() {
        Intent intent = BookDetailsActivity.newIntent(context, eBook);
        context.startActivity(intent);
    }
}
