package com.example.Library.Management.Dal;

import com.example.Library.Management.Model.Book;
import com.example.Library.Management.Request.BookRequest;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookManagement {
    Book addBook(BookRequest book);
    Book getBookByIsbn(String isbn);
    List<Book> getAllBooks();
    String updateBook(BookRequest book);
    String removeBook(String isbn);
}
