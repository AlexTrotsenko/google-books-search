package com.alextrotsenko.booksearch;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.alextrotsenko.booksearch.databinding.ActivityBookDetailsBinding;
import com.alextrotsenko.booksearch.rest.dto.EBookInfo;
import com.alextrotsenko.booksearch.rest.dto.ShortEBookInfo;
import com.alextrotsenko.booksearch.rest.services.GoogleBookService;
import com.alextrotsenko.booksearch.utils.NetworkHelper;
import com.alextrotsenko.booksearch.viewmodel.DetailedBookViewModel;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BookDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_EBOOK = "com.alextrotsenko.booksearch.ebook";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityBookDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_book_details);
        //todo use subclass of BookViewModel: DetailedBookViewModel
        DetailedBookViewModel viewModel = new DetailedBookViewModel(this);
        viewModel.setEBook(getEBookInfo());
        binding.setViewModel(viewModel);

        GoogleBookService googleBookService = NetworkHelper.getRetrofit().create(GoogleBookService.class);
        googleBookService.getBookDetails(getEBookInfo().getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        eBookResult -> viewModel.setEBook(eBookResult),
                        error -> Log.e("AlexT", "error at request to rest api:", error)
                );


        /**
         * TODO: do request to https://www.googleapis.com/books/v1/volumes/{getEBookInfo().getId()}
         * and display DetailedBookViewModel by setting it to BookViewModel
         */
    }

    public static Intent newIntent(Context context, EBookInfo eBook) {
        Intent intent = new Intent(context, BookDetailsActivity.class);
        intent.putExtra(EXTRA_EBOOK, eBook);
        return intent;
    }

    /**
     * @return initial minimal book data for displaying on activity start-up.
     */
    protected ShortEBookInfo getEBookInfo() {
        return (ShortEBookInfo) getIntent().getSerializableExtra(EXTRA_EBOOK);
    }
}
