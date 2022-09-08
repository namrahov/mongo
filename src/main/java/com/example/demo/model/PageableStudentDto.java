package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageableStudentDto {
    private List<StudentDto> list;
    private boolean hasNextPage;
    private int lastPageNumber;
    private long totalCount;
}

