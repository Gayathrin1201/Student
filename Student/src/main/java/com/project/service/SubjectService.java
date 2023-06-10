package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Classes;
import com.project.model.Subject;
import com.project.repository.ClassRepository;
import com.project.repository.SubjectRepository;
@Service
public class SubjectService {
	
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	public Subject saveSubject(Subject subject)
	{
		return subjectRepository.save(subject);
	}
	
	public Subject updateSubject(Subject subject)
	{
		Subject existingSubject= subjectRepository.findById(subject.getSubjectId())
				.orElseThrow(() -> new IllegalArgumentException("Class not found with id "));
		existingSubject.setSubjectName(subject.getSubjectName());
		return subjectRepository.save(existingSubject);
	}
	
	
	public List<Subject> getSubjects()
	{
		return subjectRepository.findAll();
	}
	public void deleteSubject(int subjectId)
	{
		subjectRepository.deleteById(subjectId);
	}

}
