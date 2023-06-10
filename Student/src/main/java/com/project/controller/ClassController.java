package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Classes;
import com.project.model.Student;
import com.project.service.ClassService;
@RestController
public class ClassController {
	
	@Autowired
	private ClassService classService;
	@PostMapping("/addClass")
	public Classes saveClass(@RequestBody Classes classes)
	{
		return classService.saveClass(classes);
	}
	@PutMapping("/updateClass")
	public ResponseEntity<?> updateClass(@RequestBody Classes classes) {
		
			
			Classes updatedclass = classService.updateClass(classes);
			if (updatedclass != null) {
				return ResponseEntity.ok("Student edited successfully : "+classes.getClassId());
			} else {
				return ResponseEntity.notFound().build();
			}
	}
	
	@DeleteMapping("/deleteClass")
	public String deleteClass(int classId)
	{
		classService.deleteClass(classId);
		return "Class is deleted succesfully";
	}
	
	@GetMapping("/getClassById/{classId}")
    public Classes getStudentById(@PathVariable("classId") int classId) {
        return classService.getClassById(classId);
    }

}
