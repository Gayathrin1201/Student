package com.project.service;




import com.project.dto.ReportCardJsonDto;
import com.project.dto.ReportCardSubjectJsonDto;
import com.project.model.ReportCard;
import com.project.model.Student;
import com.project.model.Subject;
import com.project.repository.ReportCardRepository;
import com.project.repository.StudentRepository;
import com.project.repository.SubjectRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportCardService {

    private final ReportCardRepository reportCardRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public ReportCardService(ReportCardRepository reportCardRepository, StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.reportCardRepository = reportCardRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    public void addReportCardFromJson(ReportCardJsonDto reportCardJson) {
        Student student = studentRepository.findById(reportCardJson.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student with ID " + reportCardJson.getStudentId() + " not found"));

        for (ReportCardSubjectJsonDto subjectJson : reportCardJson.getSubjects()) {
            Subject subject = subjectRepository.findById(subjectJson.getSubjectId())
                    .orElseThrow(() -> new IllegalArgumentException("Subject with ID " + subjectJson.getSubjectId() + " not found"));

            ReportCard reportCard = new ReportCard();
            reportCard.setMarks(subjectJson.getMarks());
            reportCard.setStudent(student);
            reportCard.setSubject(subject);

            reportCardRepository.save(reportCard);
        }
    }

    public void updateReportCardFromJson(int reportCardId, ReportCardJsonDto reportCardJson, ReportCardSubjectJsonDto reportCardSubjectJsonDto) {
        Optional<ReportCard> reportCardOptional = reportCardRepository.findById(reportCardId);
        if (reportCardOptional.isEmpty()) {
            throw new IllegalArgumentException("Report card with ID " + reportCardId + " not found");
        }

        ReportCard reportCard = reportCardOptional.get();
        reportCard.setMarks(reportCardSubjectJsonDto.getMarks());

        Optional<Student> studentOptional = studentRepository.findById(reportCardJson.getStudentId());
        if (studentOptional.isEmpty()) {
            throw new IllegalArgumentException("Student with ID " + reportCardJson.getStudentId() + " not found");
        }

        Student student = studentOptional.get();
        reportCard.setStudent(student);

        Optional<Subject> subjectOptional = subjectRepository.findById(reportCardSubjectJsonDto.getSubjectId());
        if (subjectOptional.isEmpty()) {
            throw new IllegalArgumentException("Subject with ID " + reportCardSubjectJsonDto.getSubjectId() + " not found");
        }

        Subject subject = subjectOptional.get();
        reportCard.setSubject(subject);

        reportCardRepository.save(reportCard);
    }

    public void deleteReportCard(int reportCardId)
	{
		reportCardRepository.deleteById(reportCardId);
	}
}
