package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Student;
import com.project.model.Subject;
import com.project.service.SubjectService;
@RestController
public class SubjectController {
	@Autowired
	private SubjectService subjectService;
	@PostMapping("/addSubject")
	public Subject saveSubject(@RequestBody Subject subject)
	{
		return subjectService.saveSubject(subject);
	}
	@GetMapping("/getSubjects")
	public List<Subject> getSubjects()
	{
		return subjectService.getSubjects();
	}
	
	@PutMapping("/updateSubject")
	public ResponseEntity<?> updateSubject(@RequestBody Subject subject) {
		
			
		Subject updatedSubject = subjectService.updateSubject(subject);
			if (updatedSubject != null) {
				return ResponseEntity.ok("Student edited successfully : "+subject.getSubjectId());
			} else {
				return ResponseEntity.notFound().build();
			}
	}
	
	@DeleteMapping("/deleteSubject")
	public String deleteStudent(int subjectId)
	{
		subjectService.deleteSubject(subjectId);
		return "Subject is deleted succesfully";
	}

}

