package com.solwyz.doctorlab.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solwyz.doctorlab.Entity.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByDoctorId(Long doctorId);
   // List<Feedback> findByClinicId(Long clinicId);
}