package com.example.demo.service;

import com.example.demo.dao.document.Point;
import com.example.demo.dao.document.Student;
import com.example.demo.dao.repository.LightStudentRedisRepo;
import com.example.demo.dao.repository.PointRepository;
import com.example.demo.dao.repository.StudentRepository;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.model.LightStudent;
import com.example.demo.model.PageableStudentDto;
import com.example.demo.model.StudentDto;
import com.example.demo.model.enums.Gender;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final LightStudentRedisRepo lightStudentRedisRepo;
    private final PointRepository pointRepository;

    public PageableStudentDto getPageableStudent(Integer page, Integer count, String firstName) {
        Pageable pageable = PageRequest.of(page, count, Sort.by("createdAt").descending());
        Page<Student> pages;

        if (firstName == null) {
            pages = studentRepository.findAll(pageable);
        } else {
            pages = studentRepository.findByFirstNameContainingIgnoreCase(firstName, pageable);
        }

        List<StudentDto> studentDtoList = StudentMapper.collectionToDtoList(pages.getContent());

        int lastPageNumber = pages.getTotalPages();

        if (lastPageNumber != 0) lastPageNumber -= 1;

        return PageableStudentDto.builder()
                .list(studentDtoList)
                .lastPageNumber(lastPageNumber)
                .hasNextPage(pages.hasNext())
                .totalCount(pages.getTotalElements())
                .build();
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student createStudent(StudentDto dto) {
        Point point = new Point();
        point.setValue(dto.getPoint().getValue());

        Student student = Student.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .gender(Gender.valueOf(dto.getGender()))
                .address(dto.getAddress())
                .favouriteSubject(dto.getFavouriteSubject())
                .totalSpentInBooks(dto.getTotalSpentInBooks())
                .createdAt(LocalDateTime.now())
                .point(point)
                .build();

        return studentRepository.save(student);
    }

   /* public Student findStudentByEmail(String email) {
        return studentRepository.findStudentByEmail(email).orElseThrow(() -> {
            throw new RuntimeException("STUDENT_NOT_FOUND");
        });
    }*/

    public Student findStudentMaxTotalSpentInBooks() {
        return studentRepository.findFirstByOrderByTotalSpentInBooksDesc();
    }

    public Map<String, Long> groupByFirstNameWithCount() {
        List<Student> studentList = studentRepository.findAll();

        return studentList.stream()
                .collect(Collectors.groupingBy(Student::getFirstName, Collectors.counting()));
    }

    public Student oldestPersonInTheCity(String city) {
        List<Student> studentsInTheCity = studentRepository.findStudentByAddress_City(city);

        Optional<Student> optionalOldestPersonInTheCity
                = studentsInTheCity.stream().max(Comparator.comparing(Student::getTotalSpentInBooks));

        return optionalOldestPersonInTheCity.orElse(null);
    }

    public LightStudent saveStudentToRedis(LightStudent lightStudent) {
        return lightStudentRedisRepo.save(lightStudent);
    }

    public LightStudent findByIdFromRedis(int id) {
        return lightStudentRedisRepo.findProductById(id);
    }



}
