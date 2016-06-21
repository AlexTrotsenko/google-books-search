package com.alextrotsenko.booksearch.rest.dto;

import com.google.gson.annotations.SerializedName;


public class ShortEBookInfo extends EBookInfo {
    private static final long serialVersionUID = -3487430389725168240L;

    @SerializedName(DtoSchema.EBook.BOOK_INFO)
    BookInfo bookInfo;

    public BookInfo getBookInfo() {
        return bookInfo;
    }
}
