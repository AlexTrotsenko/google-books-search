package com.alextrotsenko.booksearch;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alextrotsenko.booksearch.databinding.ActivityBookDetailsBinding;
import com.alextrotsenko.booksearch.rest.dto.BooksInfo.EBookInfo;
import com.alextrotsenko.booksearch.ui.BookAdapter.BookViewModel;

public class BookDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_EBOOK = "com.alextrotsenko.booksearch.ebook";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityBookDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_book_details);
        //todo use subclass of BookViewModel: DetailedBookViewModel
        BookViewModel viewModel = new BookViewModel(this);
        viewModel.setEBook(getEBookInfo());
        binding.setViewModel(viewModel);

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
    protected EBookInfo getEBookInfo() {
        return (EBookInfo) getIntent().getSerializableExtra(EXTRA_EBOOK);
    }
}
