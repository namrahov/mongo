package com.example.demo.dao.repository;

import com.example.demo.dao.document.Point;
import com.example.demo.dao.document.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PointRepository extends MongoRepository<Point, String> {
}
