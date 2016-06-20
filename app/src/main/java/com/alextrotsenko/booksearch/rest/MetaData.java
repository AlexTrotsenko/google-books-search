package com.alextrotsenko.booksearch.rest;

/**
 * Url-related constants holder.
 */
public interface MetaData {
    String GOOGLE_BOOKS_BASE_URL = "https://www.googleapis.com";

    interface VolumesList {
        String PATH = "books/v1/volumes";
        String SEARCH_QUERY_PARAM = "q";
    }
}
