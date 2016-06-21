package com.alextrotsenko.booksearch.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.alextrotsenko.booksearch.BookDetailsActivity;
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

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView view, String url) {
        //todo use Dependency Injection
        final ImageLoader imageLoader = ImageLoader.getInstance();

        imageLoader.displayImage(url, view);
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
