package com.solwyz.doctorlab.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solwyz.doctorlab.Entity.Test;

public interface TestRepository extends JpaRepository<Test, Long> {

	Test save(Test test);

}
