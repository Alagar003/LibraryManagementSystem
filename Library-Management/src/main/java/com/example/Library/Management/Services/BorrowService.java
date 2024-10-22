package com.example.Library.Management.Services;

import com.example.Library.Management.Dal.BorrowManagement;
import com.example.Library.Management.Model.BorrowRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BorrowService {
    @Autowired
    private BorrowManagement borrowDao;


    public String addBorrowRecord(String memberId, String isbn) {
        return borrowDao.addBorrowRecord(memberId, isbn);
    }

    public String returnBook( String isbn) {
        return borrowDao.returnBook(isbn);
    }

    public List<String> getBorrowedBooksByMemberId(String memberId) {
        return borrowDao.getBorrowedBooksByMemberId(memberId);
    }

    public Map<String, BorrowRecord> getAllBorrowedRecords() {
        return borrowDao.getAllBorrowedRecords();
    }

    public String getMemberIdByBookId(String bookId) {
        return borrowDao.getMemberIdByBookId(bookId);
    }
}