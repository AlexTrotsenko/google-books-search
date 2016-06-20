package com.alextrotsenko.booksearch.rest.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Contains list of books, that matches search request and total books count.
 */
public class BooksInfo {
    @SerializedName(DtoSchema.Books.COUNT)
    Integer matchingBooksCount;

    @SerializedName(DtoSchema.Books.EBOOKS_LIST)
    List<EBookInfo> eBooks;

    public Integer getMatchingBooksCount() {
        return matchingBooksCount;
    }

    public List<EBookInfo> getEBooks() {
        return eBooks;
    }

    public static class EBookInfo {
        @SerializedName(DtoSchema.EBook.ID)
        String id;

        @SerializedName(DtoSchema.EBook.BOOK_INFO)
        BookInfo bookInfo;

        @Override
        public String toString() {
            return "EBookInfo{" +
                    "id='" + id + '\'' +
                    ", bookInfo=" + bookInfo +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "BooksInfo{" +
                "matchingBooksCount=" + matchingBooksCount +
                ", eBooks=" + eBooks +
                '}';
    }
}
