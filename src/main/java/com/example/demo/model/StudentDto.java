package com.example.demo.model;

import com.example.demo.dao.document.Point;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private Address address;
    private List<String> favouriteSubject;
    private BigDecimal totalSpentInBooks;
    private LocalDateTime createdAt;
    private Point point;
}
