package com.alextrotsenko.booksearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alextrotsenko.booksearch.rest.MetaData;
import com.alextrotsenko.booksearch.rest.dto.BooksInfo;
import com.alextrotsenko.booksearch.rest.services.GoogleBookService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *  1st screen with the list of google book, matching search request.
 */
public class BookSearchActivityFragment extends Fragment {

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
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        booksService = retrofit.create(GoogleBookService.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_search, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnTextChanged(R.id.book_search_input)
    public void onSearchTextChanged(CharSequence lookupText) {
        final Call<BooksInfo> booksCall = booksService.getBooks(lookupText);
        booksCall.enqueue(new Callback<BooksInfo>() {
            @Override
            public void onResponse(Call<BooksInfo> call, Response<BooksInfo> response) {
                Log.e("Alex", "result: " + response.body());
            }

            @Override
            public void onFailure(Call<BooksInfo> call, Throwable t) {
                Log.e("Alex", "error: " + t);
            }
        });
    }
}
