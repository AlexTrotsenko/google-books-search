package com.alextrotsenko.booksearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.alextrotsenko.booksearch.rest.MetaData;
import com.alextrotsenko.booksearch.rest.dto.BooksInfo;
import com.alextrotsenko.booksearch.rest.services.GoogleBookService;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *  1st screen with the list of google book, matching search request.
 */
public class BookSearchActivityFragment extends Fragment {

    @BindView(R.id.book_search_input)
    EditText bookSearchInput;

    @BindView(R.id.books)
    RecyclerView books;

    private GoogleBookService booksService;


    public BookSearchActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MetaData.GOOGLE_BOOKS_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        booksService = retrofit.create(GoogleBookService.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_search, container, false);
        ButterKnife.bind(this, view);

        RxTextView.textChanges(bookSearchInput)
                .debounce(1, TimeUnit.SECONDS)
                .filter(inputText -> !TextUtils.isEmpty(inputText))
                .flatMap(inputText -> booksService.getBooks(inputText).subscribeOn(Schedulers.io()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        onNext -> displayNewBooks(onNext),
                        error -> Log.e("AlexT", "error at request to rest api:", error)
                );

        return view;
    }

    private int displayNewBooks(BooksInfo onNext) {
        return Log.e("AlexTr", "rest request result: " + onNext);
    }
}
