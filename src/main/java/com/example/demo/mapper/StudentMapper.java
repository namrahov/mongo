package com.example.demo.mapper;

import com.example.demo.dao.document.Student;
import com.example.demo.model.StudentDto;

import java.util.ArrayList;
import java.util.List;

public class StudentMapper {

    public static List<StudentDto> collectionToDtoList(List<Student> studentList) {
        List<StudentDto> studentDtoList = new ArrayList<>();

        for(Student student: studentList) {
            StudentDto dto = new StudentDto();
            dto.setId(student.getId());
            dto.setFirstName(student.getFirstName());
            dto.setLastName(student.getLastName());
            dto.setEmail(student.getEmail());
            dto.setGender(String.valueOf(student.getGender()));
            dto.setAddress(student.getAddress());
            dto.setFavouriteSubject(student.getFavouriteSubject());
            dto.setTotalSpentInBooks(student.getTotalSpentInBooks());

            studentDtoList.add(dto);
        }

        return studentDtoList;
    }
}
