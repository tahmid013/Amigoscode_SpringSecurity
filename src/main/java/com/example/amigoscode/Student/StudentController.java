package com.example.amigoscode.Student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("api/v1/students/")
public class StudentController {

    private static final List<Student> LIST = Arrays.asList(
            new Student(1, "abc"),
            new Student(2, "def"),
            new Student(3, "ghi")
    );

    @GetMapping(path = "{studentId}")
    public Student getStudent(@PathVariable Integer studentId){
        return LIST.stream()
                .filter(student -> studentId.equals(student.getStudentId()) )
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Student " + studentId + "does not exist"));
    }

}
