package com.alextrotsenko.booksearch.rest.services;

import com.alextrotsenko.booksearch.rest.MetaData;
import com.alextrotsenko.booksearch.rest.MetaData.VolumeDetails;
import com.alextrotsenko.booksearch.rest.dto.BooksInfo;
import com.alextrotsenko.booksearch.rest.dto.DetailedEBookInfo;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Server side rest API for getting books data.
 */
public interface GoogleBookService {
    @GET(MetaData.VolumesList.PATH)
    Observable<BooksInfo> getBooks(@Query(MetaData.VolumesList.SEARCH_QUERY_PARAM) CharSequence bookName);

    @GET(MetaData.VolumeDetails.PATH)
    Observable<DetailedEBookInfo> getBookDetails(@Path(VolumeDetails.ID_PATH_PARAM) CharSequence bookName);
}
