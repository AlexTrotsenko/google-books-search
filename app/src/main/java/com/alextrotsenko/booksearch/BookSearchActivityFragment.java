package com.alextrotsenko.booksearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;

import com.alextrotsenko.booksearch.rest.dto.BooksInfo;
import com.alextrotsenko.booksearch.rest.services.GoogleBookService;
import com.alextrotsenko.booksearch.ui.BookAdapter;
import com.alextrotsenko.booksearch.utils.NetworkHelper;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
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

        //todo replace with Dependency Injection
        booksService = NetworkHelper.getRetrofit().create(GoogleBookService.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_search, container, false);
        ButterKnife.bind(this, view);

        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        books.setLayoutManager(layoutManager);
        BookAdapter booksAdapter = new BookAdapter();
        books.setAdapter(booksAdapter);
        //todo: implement pagination in books.addOnScrollListener

        RxTextView.textChanges(bookSearchInput)
                .debounce(1, TimeUnit.SECONDS)
                .filter(inputText -> !TextUtils.isEmpty(inputText))
                .flatMap(inputText -> booksService.getBooks(inputText).subscribeOn(Schedulers.io()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        onNext -> booksAdapter.displayNewBooks(onNext.getEBooks()),
                        error -> Log.e("AlexT", "error at request to rest api:", error)
                );


        //set number of columns to max fitting the screen
        books.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        books.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        int viewWidth = books.getMeasuredWidth();
                        float cardViewWidth = getActivity().getResources().getDimension(R.dimen.book_thumbnail_width);
                        int newSpanCount = (int) Math.floor(viewWidth / cardViewWidth);
                        layoutManager.setSpanCount(newSpanCount);
                        layoutManager.requestLayout();
                    }
                });

        return view;
    }

    /**
     *  Display books for new user input.
     */
    private void displayNewBooks(BooksInfo onNext) {
        Log.e("AlexTr", "rest request result: " + onNext);
    }
}
