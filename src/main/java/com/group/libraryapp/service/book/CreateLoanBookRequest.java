package com.group.libraryapp.service.book;

import org.springframework.util.Assert;

public class CreateLoanBookRequest {
    private final String userName;
    private final String bookName;

    public CreateLoanBookRequest(final String userName, final String bookName) {
        Assert.hasText(userName, "userName must not be empty");
        Assert.hasText(bookName, "bookName must not be empty");
        this.userName = userName;
        this.bookName = bookName;
    }

    public String getBookName() {
        return bookName;
    }

    public String getUserName() {
        return userName;
    }
}
