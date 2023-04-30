package com.spring.MagicOfBook.entity;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "book_id")
    private String bookId;
    @Column(name = "book_title")
    private String bookTitle;
    @Column(name = "book_genre")
    private String bookGenre;
    @Column(name = "author")
    private String author;
    @Column(name = "publication_name")
    private String publicationName;
    @Column(name = "price")
    private int price;

    public Book() {

    }

    public Book(String bookId, String bookTitle, String bookGenre, String author, String publicationName, int price) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookGenre = bookGenre;
        this.author = author;
        this.publicationName = publicationName;
        this.price = price;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublicationName() {
        return publicationName;
    }

    public void setPublicationName(String publicationName) {
        this.publicationName = publicationName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookTitle='" + bookTitle + '\'' +
                ", bookGenre='" + bookGenre + '\'' +
                ", author='" + author + '\'' +
                ", publicationName='" + publicationName + '\'' +
                ", price=" + price +
                '}';
    }
}
