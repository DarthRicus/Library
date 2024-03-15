package kz.orynbay.springcourse.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Book {
    private int bookId;
    @NotEmpty(message = "Name of book shouldn't be empty!")
    private String name;
    @NotEmpty(message = "Author of book shouldn't be empty!")
    private String author;
    @Max(value = 2024, message = "Year of production shouldn't be greater than 2024!")
    @Min(value = 0, message = "Year of production shouldn't be less than 0!")
    private int year;
    private int personId;
    public Book(){

    }

    public Book(int bookId, String name, String author, int year) {
        this.bookId = bookId;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}
