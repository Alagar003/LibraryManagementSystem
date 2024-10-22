package com.example.Library.Management.Dal;
import com.example.Library.Management.Model.Book;
import com.example.Library.Management.Request.BookRequest;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class BookDao implements BookManagement {

    private final Map<String, Book> books = new ConcurrentHashMap<>();

    @Override
    public Book addBook(BookRequest bookRequest) {
        if (bookRequest == null || bookRequest.getIsbn() == null) {
            System.out.println("Book request or ISBN cannot be null");
        }
        Book book = new Book();
        book.setIsbn(bookRequest.getIsbn());
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setPublicationYear(bookRequest.getPublicationYear());
        book.setAvailable(true);

        // Add the book to the collection
        books.put(bookRequest.getIsbn(), book);
        System.out.println("Book added with ISBN: " + book.getIsbn());
        return book;
    }


    @Override
    public  Book getBookByIsbn(String isbn) {
        if (isbn == null) {
            return null;
        }
        return books.get(isbn);
    }
    @Override
    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }



    public String updateBook(BookRequest book) {
        if (book == null || book.getIsbn() == null) {
            return "Book or ISBN cannot be null";
        }
        Book newbook = new Book();
        newbook.setIsbn(book.getIsbn());
        newbook.setTitle(book.getTitle());
        newbook.setAuthor(book.getAuthor());
        newbook.setPublicationYear(book.getPublicationYear());
        newbook.setAvailable(true);

        // Use replace and check if a book was actually replaced
        Book previousBook = books.replace(book.getIsbn(), newbook);
        System.out.println("Book updated : " + previousBook);
        if (previousBook != null) {
            return "Book updated successfully"+previousBook;
        } else {
            return "Book with the given ISBN not found.";
        }
    }


    @Override
    public String removeBook(String isbn) {
        if (isbn == null) {
            return "ISBN cannot be null";
        }

        // Attempt to remove the book from the map
        Book removedBook = books.remove(isbn);
        System.out.println("Book removed : " + removedBook);
        if (removedBook != null) {
            return "Book removed successfully.";
        } else {
            return "Book with the given ISBN not found.";
        }
    }

}

