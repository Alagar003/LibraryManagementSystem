package com.example.Library.Management.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class BorrowRecord {
    private String id;
    private String memberId; // ID of the member
    private String isbn;
    private LocalDate StartDate;
    private LocalDate EndDate;
}