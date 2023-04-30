package com.spring.MagicOfBook.repository;

import com.spring.MagicOfBook.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findByAuthor(String author);

    List<Book> findByBookTitle(String bookTitle);

    List<Book> findByPublicationName(String publicationName);

    @Query("Select book from Book book where book.price >= :#{#startPrice} and book.price <= :#{#endPrice}")
    List<Book> findBooksByPriceRange(@Param("startPrice") int startPrice, @Param("endPrice") int endPrice);

    @Query("Select book from Book book order by book.price")
    List<Book> sortBooksFromLowToHigh();
}
