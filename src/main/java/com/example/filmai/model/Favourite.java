package com.example.filmai.model;

public class Favourite {

    private int id;
    private int userId;
    private int bookId;
    private String bookName;


    public Favourite() {
    }

    public Favourite(int bookId) {
        this.bookId = bookId;
    }

    public Favourite(int id, int userId, int bookId, String bookName) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.bookName = bookName;
    }

    public Favourite(int userId, int bookId, String bookName) {
        this.userId = userId;
        this.bookId = bookId;
        this.bookName = bookName;
    }


    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setId(int id) {
        this.id = id;
    }
}
