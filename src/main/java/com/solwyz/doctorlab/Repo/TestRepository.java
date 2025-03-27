package com.solwyz.doctorlab.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solwyz.doctorlab.Entity.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {

	Test save(Test test);

}
