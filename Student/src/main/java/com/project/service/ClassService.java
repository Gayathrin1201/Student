package com.project.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Classes;
import com.project.model.Student;
import com.project.repository.ClassRepository;

@Service
public class ClassService {
	
	@Autowired
	private ClassRepository classRepository;
	
	public Classes saveClass(Classes classes)
	{
		return classRepository.save(classes);
	}
	
	public Classes updateClass(Classes classes)
	{
		Classes existingClasses = classRepository.findById(classes.getClassId())
				.orElseThrow(() -> new IllegalArgumentException("Class not found with id "));
		existingClasses.setClassName(classes.getClassName());
		return classRepository.save(existingClasses);
	}
	
	public void deleteClass(int classId)
	{
		classRepository.deleteById(classId);
	}

	  public Classes getClassById(int classId) {
	        return classRepository.findById(classId)
	                .orElseThrow(() -> new NoSuchElementException("Class not found"));
	    }

}
