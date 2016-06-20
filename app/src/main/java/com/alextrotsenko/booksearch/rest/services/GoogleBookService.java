package com.alextrotsenko.booksearch.rest.services;

import com.alextrotsenko.booksearch.rest.MetaData;
import com.alextrotsenko.booksearch.rest.dto.BooksInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Server side rest API for getting books data.
 */
public interface GoogleBookService {
    @GET(MetaData.VolumesList.PATH)
    Call<BooksInfo> getBooks(@Query(MetaData.VolumesList.SEARCH_QUERY_PARAM) CharSequence bookName);
}
