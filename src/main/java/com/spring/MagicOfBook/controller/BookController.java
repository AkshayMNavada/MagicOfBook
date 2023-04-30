package com.spring.MagicOfBook.controller;

import com.spring.MagicOfBook.entity.Book;
import com.spring.MagicOfBook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<String> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        if (books.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(books.toString());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No books are present!");
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<String> getBooksByAuthor(@PathVariable String author) {
        List<Book> books = bookService.getBooksByAuthor(author);
        if (books.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(books.toString());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No books are present for author name " + author);
    }

    @GetMapping("/publication/{publicationName}")
    public ResponseEntity<String> getBooksByPublicationName(@PathVariable String publicationName) {
        List<Book> books = bookService.getBooksByPublicationName(publicationName);
        if (books.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(books.toString());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No books are present for publication name " + publicationName);
    }


    @GetMapping("bookTitle/{bookTitle}")
    public ResponseEntity<String> getBookByBookTitle(@PathVariable String bookTitle) {
        List<Book> books = bookService.getBooksByBookTitle(bookTitle);
        if (books.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(books.toString());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No book is present for book title " + bookTitle);
    }

    @GetMapping("/price/{startPrice}/{endPrice}")
    public ResponseEntity<String> getBooksByPriceRange(@PathVariable Integer startPrice, @PathVariable Integer endPrice) {
        List<Book> books = bookService.getBooksByPriceRange(startPrice, endPrice);
        if (books.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(books.toString());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No books are present with in price range " + startPrice + " " + endPrice);
    }

    @GetMapping("/sortedPrice")
    public ResponseEntity<String> getBooksSortedByPrice() {
        List<Book> books = bookService.sortAllBooksByPrice();
        if (books.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(books.toString());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No books are present");
    }

    @PostMapping
    public ResponseEntity<String> insertBook(@RequestBody Book book, HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email != null && email.contains("@admin.com")) {
            Book book1 = bookService.insertBook(book);
            if (book1 != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Book " + book.getBookTitle() + " is added");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book can not be added");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Access Denied");
    }

    @PutMapping
    public ResponseEntity<String> updateBook(@RequestBody Book book, HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email != null && email.contains("@admin.com")) {
            Book book1 = bookService.updateBook(book);
            if (book1 != null) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Book " + book.getBookTitle() + " is updated");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book can not be updated");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Access Denied");
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable String bookId, HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email != null && email.contains("@admin.com")) {
            Book book = bookService.deleteBook(bookId);
            if (book != null) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Book " + book.getBookTitle() + " is deleted");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No book found with book id : " + bookId);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Access Denied");
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<String> getBookById(@PathVariable String bookId) {
        Book book = bookService.getBookById(bookId);
        if (book != null) {
            return ResponseEntity.status(HttpStatus.OK).body(book.toString());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No book found with book id : " + bookId);
    }
}
