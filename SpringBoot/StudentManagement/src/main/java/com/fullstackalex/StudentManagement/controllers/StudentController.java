package com.fullstackalex.StudentManagement.controllers;

import com.fullstackalex.StudentManagement.models.Student;
import com.fullstackalex.StudentManagement.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public List<Student> getAllStudents() {
       return studentRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentRepository.save(student), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Optional<Student> updateStudent = studentRepository.findById(id);

        if (!updateStudent.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        updateStudent.get().setName(student.getName());
        updateStudent.get().setEmail(student.getEmail());
        updateStudent.get().setAge(student.getAge());

        return new ResponseEntity<>(studentRepository.save(updateStudent.get()), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        Optional<Student> deleteStudent = studentRepository.findById(id);

        if (!deleteStudent.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        studentRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
