package com.solwyz.doctorlab.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solwyz.doctorlab.Entity.Feedback;
import com.solwyz.doctorlab.Service.FeedbackService;

import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/feedback")
@Tag(name = "feedback Authentication", description = "APIs for feedback related operations")
public class FeedbackController {
	
	@Autowired
    private FeedbackService feedbackService;

    @PostMapping("/submit")
    public ResponseEntity<?> submitFeedback(@RequestBody Feedback feedback) {
        return ResponseEntity.ok(feedbackService.submitFeedback(feedback));
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Feedback>> getFeedbackByDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(feedbackService.getFeedbackByDoctor(doctorId));
    }

    @GetMapping("/clinic/{clinicId}")
    public ResponseEntity<List<Feedback>> getFeedbackByClinic(@PathVariable Long clinicId) {
        return ResponseEntity.ok(feedbackService.getFeedbackByClinic(clinicId));
    }
    

}
