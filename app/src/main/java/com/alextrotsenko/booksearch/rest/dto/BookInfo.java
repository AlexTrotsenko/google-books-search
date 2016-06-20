package com.alextrotsenko.booksearch.rest.dto;

/**
 * Short list of data about the book to display in "books list".
 */
public class BookInfo {
    String title;
    ImageLinks imageLinks;

    public String getTitle() {
        return title;
    }

    public String getThumbnailLink() {
        return imageLinks.thumbnail;
    }

    public static class ImageLinks {
        String thumbnail;

        @Override
        public String toString() {
            return "ImageLinks{" +
                    "thumbnail='" + thumbnail + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "title='" + title + '\'' +
                ", imageLinks=" + imageLinks +
                '}';
    }
}
