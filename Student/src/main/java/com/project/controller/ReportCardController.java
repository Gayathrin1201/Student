package com.project.controller;

import com.project.dto.ReportCardJsonDto;
import com.project.dto.ReportCardSubjectJsonDto;
import com.project.model.ReportCard;
import com.project.service.ReportCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class ReportCardController {

    private final ReportCardService reportCardService;

    @Autowired
    public ReportCardController(ReportCardService reportCardService) {
        this.reportCardService = reportCardService;
    }

    @PostMapping("/addReportCard")
    public ResponseEntity<String> addReportCardFromJson(@RequestBody ReportCardJsonDto reportCardJson) {
        reportCardService.addReportCardFromJson(reportCardJson);
        return new ResponseEntity<>("Report card added successfully", HttpStatus.CREATED);
    }
    
    @PutMapping("/update/{reportCardId}")
    public ResponseEntity<String> updateReportCardFromJson(@PathVariable int reportCardId, @RequestBody ReportCardJsonDto reportCardJsonDto) {
        ReportCardSubjectJsonDto reportCardSubjectJsonDto = reportCardJsonDto.getSubjects().get(0);
        reportCardService.updateReportCardFromJson(reportCardId, reportCardJsonDto, reportCardSubjectJsonDto);
        return new ResponseEntity<>("Report card updated successfully", HttpStatus.OK);
    }
    @DeleteMapping("/deleteReportCard/{reportCardId}")
	public String deleteReportCard(@PathVariable int reportCardId)
	{
		reportCardService.deleteReportCard(reportCardId);
		return "student is deleted succesfully";
	}

}

