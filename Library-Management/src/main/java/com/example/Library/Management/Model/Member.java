package com.example.Library.Management.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private String id;
    private String name;
    private String City;
    private String PhoneNumber;
}
