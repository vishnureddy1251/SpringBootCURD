package com.example.SpringBootCURDApp.controller;

import com.example.SpringBootCURDApp.model.Student;
import com.example.SpringBootCURDApp.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    private StudentRepo studentRepo;

  @PostMapping ("/addStudent")
  public ResponseEntity<Student> saveStudent(@RequestBody Student student){

      Student studentObj = studentRepo.save(student);
      return new ResponseEntity<>(studentObj, HttpStatus.OK);

  }

    @GetMapping("/getStudent")
    public ResponseEntity<List<Student>> getStudent() {
        try {
            List<Student> studentList = new ArrayList<>();
            studentRepo.findAll().forEach(studentList::add);  // Call 'findAll' on the injected instance

            if (studentList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(studentList, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping ("/updateStudent/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id){

        Optional<Student> student = studentRepo.findById(id);
        if (student.isPresent()){
            Student updateStudent = student.get();
            updateStudent.setStudentName(updateStudent.getStudentName());
            updateStudent.setStudentEmail(updateStudent.getStudentEmail());
            updateStudent.setStudentAddress(updateStudent.getStudentAddress());

            Student studentObj = studentRepo.save(updateStudent);
            return new ResponseEntity<>(studentRepo.save(student.get()), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping ("/deleteStudent/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id){
        studentRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
