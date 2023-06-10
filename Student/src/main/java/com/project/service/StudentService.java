package com.project.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Classes;
import com.project.model.Student;
import com.project.model.Subject;
import com.project.repository.ClassRepository;
import com.project.repository.StudentRepository;
import com.project.repository.SubjectRepository;

@Service

public class StudentService {
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private ClassRepository classesRepository;
	@Autowired
	private SubjectRepository subjectRepository;
	
	public Student addStudent(Student student, int classId) {
	    Classes classes = classesRepository.findById(classId).orElseThrow(
	            () -> new IllegalArgumentException("Invalid class ID"));

	    student.setClasses(classes);
	    
	    return studentRepository.save(student);
	}
	
	public Student updateStudent(Student student)
	{
		Student existingStudent = studentRepository.findById(student.getStudentId())
				.orElseThrow(() -> new IllegalArgumentException("Student not found with id "));
		
		
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setAddress(student.getAddress());
		existingStudent.setPhoneNumber(student.getPhoneNumber());
		existingStudent.setClasses(student.getClasses());
		
		return studentRepository.save(existingStudent);
	}
	
	public void deleteStudent(int studentId)
	{
		studentRepository.deleteById(studentId);
	}

//	 public List<Student> getAllStudents() {
//	        return studentRepository.findAll();
//	    }
//
	    public Student getStudentById(int studentId) {
	        return studentRepository.findById(studentId)
	                .orElseThrow(() -> new NoSuchElementException("Student not found"));
	    }
	    
	    public List<Student> getStudentsByClassId(int classId) {
	        return studentRepository.findByClassesClassId(classId);
	    }
}

