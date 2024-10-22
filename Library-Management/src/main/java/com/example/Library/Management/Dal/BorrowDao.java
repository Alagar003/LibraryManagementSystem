package com.example.Library.Management.Dal;

import com.example.Library.Management.Model.Book;
import com.example.Library.Management.Model.BorrowRecord;
import com.example.Library.Management.Model.Member;
import com.example.Library.Management.Services.BookService;
import com.example.Library.Management.Services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Repository
public class BorrowDao implements BorrowManagement {
    private Map<String, BorrowRecord> Borrows = new HashMap<>();
    private Map<String, List<String>> MemberBorrowed = new HashMap<>();

    @Autowired
    private BookService bookService;
    @Autowired
    private MemberService memberService;

    @Override
    public String addBorrowRecord(String memberId, String isbn) {
        // Get the member and book details
        Member member = memberService.getMemberById(memberId);
        Book book = bookService.getBookByIsbn(isbn);
        // Check if the member and book exist
        if (member == null) {
            return "Member not found.";
        }
        if (book == null) {
            return "Book not found.";
        }

        // Check if the book is available
        if (!book.isAvailable()) {
            return "Book is currently unavailable.";
        }

        // Create a new borrow record
        String borrowId = generateRecordId();
        BorrowRecord borrowRecord = new BorrowRecord(borrowId, memberId, isbn, LocalDate.now(), LocalDate.now().plusDays(14));

        // Mark the book as unavailable
        book.setAvailable(false);
        book.setBorrowerId(borrowId);

        // Add the borrow record to the borrowRecordHashMap
        Borrows.put(borrowId, borrowRecord);

        // Add the borrow record to the member's borrowed books list
        MemberBorrowed.computeIfAbsent(memberId, k -> new ArrayList<>()).add(borrowId);

        return "Borrowing successful: " + borrowRecord.toString(); // Borrowing was successful;
    }
        public String returnBook(String isbn) {

            Book book = bookService.getBookByIsbn(isbn);
            String memberId = Borrows.get(book.getBorrowerId()).getMemberId();

            List<String> list = new ArrayList<>();

            for (String borrowId : MemberBorrowed.get(memberId)) {
                if (!borrowId.equals(book.getBorrowerId())) {
                    list.add(borrowId);
                }
            }
            MemberBorrowed.replace(memberId, list);
            LocalDate lastdate = Borrows.get(book.getBorrowerId()).getEndDate();
            book.setAvailable(true);
            book.setBorrowerId("null");
            if (lastdate.isBefore(LocalDate.now())) {
                long daysBetween = ChronoUnit.DAYS.between(lastdate, LocalDate.now());
                return "Fine: " + (daysBetween * 20);
            } else {
                return "Success: Book returned successfully.";
            }
    }

    @Override
    public List<String> getBorrowedBooksByMemberId(String memberId) {
        if (memberId == null || memberId.isEmpty()) {
            return Collections.emptyList();
        }

        List<String> borrowedBooks = MemberBorrowed.get(memberId);

        if (borrowedBooks == null) {
            return Collections.emptyList();
        }

        return borrowedBooks;
    }


    public String getMemberIdByBookId(String bookId) {
        // Retrieve the book from the DAO using the provided bookId
        Book book = bookService.getBookByIsbn(bookId);

        // Check if the book exists
        if (book == null) {
            return "Book not found";
        }

        // Get the ISBN of the book
        String id = book.getBorrowerId();

        // Retrieve the borrow record associated with the ISBN from the Borrows map
        BorrowRecord borrowRecord = Borrows.get(id);

        // Check if the borrow record exists
        if (borrowRecord != null) {
            // Return the member ID associated with the borrow record
            return borrowRecord.getMemberId();
        } else {
            // If no borrow record is found for the ISBN, return a message indicating that
            return "No member found for this book";
        }
    }



    @Override
    public Map<String,BorrowRecord> getAllBorrowedRecords() {
        return Borrows;
    }

    // Helper method to generate a unique borrow record ID
    private String generateRecordId() {
        return "BR" + (Borrows.size() + 1);
    }
}





