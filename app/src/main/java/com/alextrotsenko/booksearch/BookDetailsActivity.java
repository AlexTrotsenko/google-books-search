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
import com.alextrotsenko.booksearch.viewmodel.DetailedBookViewModel;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BookDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_EBOOK = "com.alextrotsenko.booksearch.ebook";

    @Inject
    GoogleBookService googleBookService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.getComponent().inject(this);

        ActivityBookDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_book_details);
        //todo use subclass of BookViewModel: DetailedBookViewModel
        DetailedBookViewModel viewModel = new DetailedBookViewModel(this);
        viewModel.setEBook(getEBookInfo());
        binding.setViewModel(viewModel);

        googleBookService.getBookDetails(getEBookInfo().getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        eBookResult -> viewModel.setEBook(eBookResult),
                        error -> Log.e("AlexT", "error at request to rest api:", error)
                );
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
