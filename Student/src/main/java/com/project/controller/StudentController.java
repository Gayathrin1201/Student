package com.project.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Student;
import com.project.model.Subject;
import com.project.service.StudentService;
@RestController
public class StudentController {
	@Autowired
	private StudentService studentService;

	@PostMapping("/addStudent")
	public ResponseEntity<Student> addStudentWithClassAndSubject(@RequestBody Student student) {
	    Integer classId = student.getClasses().getClassId();
	   

	    Student newStudent = studentService.addStudent(student, classId);
	    return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);
	}
	
	@PutMapping("/updateStudent")
	public ResponseEntity<?> updateStudent(@RequestBody Student student) {
		
			
			Student updatedStudent = studentService.updateStudent(student);
			if (updatedStudent != null) {
				return ResponseEntity.ok("Student edited successfully : "+student.getStudentId());
			} else {
				return ResponseEntity.notFound().build();
			}
	}
	
	@DeleteMapping("/deleteStudent/{studentId}")
	public String deleteStudent(@PathVariable int studentId)
	{
		studentService.deleteStudent(studentId);
		return "student is deleted succesfully";
	}
//	@GetMapping("/getAllStudents")
//    public List<Student> getAllStudents() {
//        return studentService.getAllStudents();
//    }
//
    @GetMapping("/getStudentById/{studentId}")
    public Student getStudentById(@PathVariable("studentId") int studentId) {
        return studentService.getStudentById(studentId);
    }
    
    @GetMapping("/byClass/{classId}")
    public ResponseEntity<List<Student>> getStudentsByClassId(@PathVariable int classId) {
        List<Student> students = studentService.getStudentsByClassId(classId);
        return ResponseEntity.ok(students);
    }



}
