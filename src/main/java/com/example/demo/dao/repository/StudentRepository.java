package com.example.demo.dao.repository;

import com.example.demo.dao.document.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student, String> {

    Page<Student> findByFirstNameContainingIgnoreCase(String firstName, Pageable pageable);

    Optional<Student> findStudentByEmail(String email);

    Student findFirstByOrderByTotalSpentInBooksDesc();

    List<Student> findStudentByAddress_City(String city);

}
