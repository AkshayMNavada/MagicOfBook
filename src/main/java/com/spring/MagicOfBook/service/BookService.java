package com.spring.MagicOfBook.service;

import com.spring.MagicOfBook.entity.Book;
import com.spring.MagicOfBook.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> getBooksByBookTitle(String title) {
        return bookRepository.findByBookTitle(title);
    }

    public List<Book> getBooksByPublicationName(String publicationName) {
        return bookRepository.findByPublicationName(publicationName);
    }

    public List<Book> getBooksByPriceRange(int startPrice, int endPrice) {
        return bookRepository.findBooksByPriceRange(startPrice, endPrice);
    }

    public List<Book> sortAllBooksByPrice() {
        return bookRepository.sortBooksFromLowToHigh();
    }

    public Book insertBook(Book book) {
        return bookRepository.save(book);

    }

    public Book updateBook(Book book) {
        if (bookRepository.existsById(book.getBookId())) {
            Book book1 = bookRepository.findById(book.getBookId()).get();
            book1.setBookTitle(book.getBookTitle());
            book1.setAuthor(book.getAuthor());
            book1.setBookGenre(book.getBookGenre());
            book1.setPublicationName(book.getPublicationName());
            book1.setPrice(book.getPrice());
            bookRepository.save(book1);
            return book1;
        } else {
            return null;
        }
    }

    public Book deleteBook(String bookId) {
        if (bookRepository.existsById(bookId)) {
            Book book = bookRepository.findById(bookId).get();
            bookRepository.deleteById(bookId);
            return book;
        } else {
            return null;
        }
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(String bookId) {
        if (bookRepository.existsById(bookId)) {
            return bookRepository.findById(bookId).get();
        }
        return null;
    }
}
