package com.example.Library.Management.Controller;

import com.example.Library.Management.Model.Book;
import com.example.Library.Management.Request.BookRequest;
import com.example.Library.Management.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping()
    public Book addBook(@RequestBody BookRequest bookRequest) {
        return bookService.addBook(bookRequest);
    }

    @GetMapping("/{isbn}")
    public Book getBookByIsbn(@PathVariable String isbn) {
        return bookService.getBookByIsbn(isbn);
    }

    @GetMapping()
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PutMapping("/{isbn}")
    public String updateBook(@PathVariable @RequestBody BookRequest book) {
        return bookService.updateBook(book);
    }

    @DeleteMapping("/{isbn}")
    public String deleteBook(@PathVariable String isbn) {
        // Optional: Handle null case if needed
        if (isbn == null) {
            return "ISBN cannot be null";  // You may not need this check if ISBN is always required.
        }

        // Call the service method to remove the book and get the result message
        String result = bookService.removeBook(isbn);

        return result; // Return the result from the service
    }
}

//    @DeleteMapping("/{isbn}")
//    public String removeBook(@PathVariable String isbn) {
//        boolean isDeleted = bookService.removeBook(isbn);
//        return isDeleted ? "Book deleted successfully." : "Failed to delete the book.";
//    }
