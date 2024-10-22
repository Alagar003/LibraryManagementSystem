package com.example.Library.Management.Controller;
import com.example.Library.Management.Request.BorrowRequest;
import com.example.Library.Management.Model.BorrowRecord;
import com.example.Library.Management.Services.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/borrow")
public class BorrowController {
    @Autowired
    private BorrowService borrowService;


    @PostMapping("/borrowBook")
    public String addBorrowRecord(@RequestBody BorrowRequest request) {
        System.out.println("Received ID: " + request.getId());
        System.out.println("Received ISBN: " + request.getIsbn());

        if (request.getId() == null || request.getIsbn() == null) {
            System.out.println("ID or ISBN is null");
            return "ID or ISBN cannot be null";
        }

        return borrowService.addBorrowRecord(request.getId(), request.getIsbn());
    }



    @PostMapping("/returnBook/{id}")
    public String returnBook(@PathVariable("id") String id) {
        System.out.println("Received ID: " + id);
        return borrowService.returnBook(id);
    }
//    @PostMapping("/returnBook/{id}")
//    public String returnBook(@PathVariable String id, @RequestBody BorrowRequest request) {
//        System.out.println("Received ID from path: " + id);
//        System.out.println("Received ID from body: " + request.getId());
//        System.out.println("Received ISBN: " + request.getIsbn());
//
//        if (request.getId() == null || request.getIsbn() == null) {
//            System.out.println("ID or ISBN is null");
//            return "ID or ISBN cannot be null";
//        }
//
//        return borrowService.returnBook(request.getIsbn());
//    }


    @GetMapping("/member/{memberId}")
    public List<String> getBorrowedBooksByMemberId(@PathVariable String memberId) {
        return borrowService.getBorrowedBooksByMemberId(memberId);
    }


    @GetMapping()
    public Map<String, BorrowRecord> getAllBorrowedRecords() {
        return borrowService.getAllBorrowedRecords();
    }

    @GetMapping("/book/{bookId}")
    public String getMemberIdByBookId(@PathVariable String bookId) {
        return borrowService.getMemberIdByBookId(bookId);
    }
}


