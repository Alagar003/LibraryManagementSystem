package com.example.Library.Management.Services;

import com.example.Library.Management.Dal.BookManagement;
import com.example.Library.Management.Model.Book;
import com.example.Library.Management.Request.BookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private  BookManagement bookDao;

    public Book getBookByIsbn(String isbn) {
        return bookDao.getBookByIsbn(isbn);
    }

    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    public String updateBook(BookRequest book) {
        return bookDao.updateBook(book);
    }

    public String removeBook(String isbn) {
        return bookDao.removeBook(isbn);
    }

    public Book addBook(BookRequest book) {
        return bookDao.addBook(book);
    }
}
