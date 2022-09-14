
package com.cst438.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.server.ResponseStatusException;

import com.cst438.domain.Course;
import com.cst438.domain.CourseRepository;
import com.cst438.domain.Enrollment;
import com.cst438.domain.EnrollmentRepository;
import com.cst438.domain.ScheduleDTO;
import com.cst438.domain.Student;
import com.cst438.domain.StudentRepository;
import com.cst438.service.GradebookService;
@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://registerf-cst438.herokuapp.com/"})
public class StudentController
{
   @Autowired
   StudentRepository studentRepository;
   
   //adding a student
   private Student getStudent(String email) {
      Student student = studentRepository.findByEmail(email);
      return student;
   }
   @PostMapping("/student/new")
   @Transactional
   public void addStudent(String email, String name) {
      if ( email == null || name == null) {
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Email and name are required to creat a new student");
      } else if (getStudent(email) != null) {
         System.out.print("Student is already registered");
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Student is already registered.");
      }else {Student student = new Student();
      student.setEmail(email);
      student.setName(name);
      studentRepository.save(student);
      System.out.println("Student successfully registered!");
      }
   }
   //Hold on Student Registration
   @PostMapping("/student/addhold")
   @Transactional
   public void setHold(@PathVariable String email) {
      Student student = getStudent(email);
      if (student == null) {
         System.out.println("Student hasn't been registered or does not exist.");
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student hasn't been registered or does not exist.");
      }else {student.setStatusCode(1);
      student.setStatus("Hold");
      studentRepository.save(student);
      }
   }
   //Releasing a hold on Student Registration
   @PostMapping("/student/releasehold")
   @Transactional
   public void releaseHold(@PathVariable String email) {
      Student student = getStudent(email);
      if (student == null) {
         System.out.println("Student hasn't been registered or does not exist.");
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student hasn't been registered or does not exist.");
      } else {student.setStatusCode(0);
      student.setStatus("No Holds.");
      studentRepository.save(student);
      }
   }
}