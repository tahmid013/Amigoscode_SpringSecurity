package com.example.amigoscode.Student;

import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {
    @Getter
    private static final List<Student> LIST = Arrays.asList(
            new Student(1, "Anna"),
            new Student(2, "Amigo"),
            new Student(3, "Smith")
    );
    @GetMapping
    public List<Student> getStudent(){
        return LIST;
    }

    @PostMapping
    public String registerNewStudent(@RequestBody Student student){
        return "Registering "+ student;
    }
    @DeleteMapping(path = "{studentId}")
    public String deleteStudent( @PathVariable("studentId") Integer studentId){
        return "Deleting "+ studentId;
    }
    @PutMapping(path = "{studentId}")
    public String updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student){
        return "updating -- studentId: "+ studentId + " student" + student;
    }

}
