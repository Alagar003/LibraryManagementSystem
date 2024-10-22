package com.example.Library.Management.Dal;

import com.example.Library.Management.Model.BorrowRecord;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BorrowManagement {
    String addBorrowRecord(String memberId, String isbn);
    String returnBook(String isbn);
    List<String> getBorrowedBooksByMemberId(String memberId);
    Map<String, BorrowRecord> getAllBorrowedRecords();
    String getMemberIdByBookId(String bookId);
}
