package com.solwyz.doctorlab.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solwyz.doctorlab.Entity.Feedback;
import com.solwyz.doctorlab.Repo.FeedbackRepository;

@Service
public class FeedbackService {
	@Autowired
	private FeedbackRepository feedbackRepository;

	public Feedback submitFeedback(Feedback feedback) {
		return feedbackRepository.save(feedback);
	}

	public List<Feedback> getFeedbackByDoctor(Long doctorId) {
		return feedbackRepository.findByDoctorId(doctorId);
	}

	public List<Feedback> getFeedbackByClinic(Long clinicId) {
		return feedbackRepository.findByClinicId(clinicId);
	}

}
