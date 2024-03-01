package com.example.amigoscode.Student;

import lombok.Getter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class StudentManagementController {
    @Getter
    private static final List<Student> LIST = Arrays.asList(
            new Student(1, "Anna"),
            new Student(2, "Amigo"),
            new Student(3, "Smith")
    );

    // hasRole('ROLE_'), hasAnyRole('ROLE_'), hasAuthority('permission'), hasAnyAuthority('permission'),

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMIN_TRAINEE')")
    public List<Student> getStudent(){
        return LIST;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('course:write')")
    public String registerNewStudent(@RequestBody Student student){
        return "Registering "+ student;
    }
    @DeleteMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('course:write')")
    public String deleteStudent( @PathVariable("studentId") Integer studentId){
        return "Deleting "+ studentId;
    }
    @PutMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('course:write')")
    public String updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student){
        return "updating -- studentId: "+ studentId + " student" + student;
    }

}
