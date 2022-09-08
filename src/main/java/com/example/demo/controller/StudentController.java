package com.example.demo.controller;


import com.example.demo.dao.document.Student;
import com.example.demo.dao.repository.LightStudentRedisRepo;
import com.example.demo.model.LightStudent;
import com.example.demo.model.PageableStudentDto;
import com.example.demo.model.StudentDto;
import com.example.demo.service.StudentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;


    @GetMapping("/pagination")
    public PageableStudentDto getPageableStudent(@RequestParam @Min(value = 0, message = "PAGE_MIN_VALUE_SHOULD_BE_0") Integer page,
                                                 @RequestParam @Min(value = 0, message = "COUNT_MIN_VALUE_SHOULD_BE_0") Integer count,
                                                 @RequestParam @Nullable String firstName) {
        return studentService.getPageableStudent(page, count, firstName);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping
    public Student createStudent(@RequestBody StudentDto dto) {
        return studentService.createStudent(dto);
    }

    @GetMapping("/{email}")
    public Student findStudentByEmail(@PathVariable String email) {
        return studentService.findStudentByEmail(email);
    }

    @GetMapping("/max-total-spent-in-books")
    public Student findStudentMaxTotalSpentInBooks() {
        return studentService.findStudentMaxTotalSpentInBooks();
    }

    @GetMapping("/group-by-first-name")
    public Map<String, Long> groupByFirstNameWithCount() {
        return studentService.groupByFirstNameWithCount();
    }

    @GetMapping("/oldest-person-in-the-city")
    public Student oldestPersonInTheCity(@RequestParam String city) {
        return studentService.oldestPersonInTheCity(city);
    }

    @PostMapping("student-to-redis")
    public LightStudent saveStudentToRedis(@RequestBody LightStudent lightStudent) {
        return studentService.saveStudentToRedis(lightStudent);
    }

    @GetMapping("find-student-from-redis/{id}")
    public LightStudent findByIdFromRedis(@PathVariable int id) {
        return studentService.findByIdFromRedis(id);
    }

}
