package com.alextrotsenko.booksearch.rest.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
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

    public static class EBookInfo implements Serializable {
        private static final long serialVersionUID = -3487430389725168240L;

        @SerializedName(DtoSchema.EBook.ID)
        String id;

        @SerializedName(DtoSchema.EBook.BOOK_INFO)
        BookInfo bookInfo;

        public String getId() {
            return id;
        }

        public BookInfo getBookInfo() {
            return bookInfo;
        }

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
