package com.solwyz.doctorlab.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.solwyz.doctorlab.Entity.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {

	Test save(Test test);
	
	@Query("SELECT t FROM Test t WHERE t.category.id = :categoryId")
	List<Test> findByCategoryId(@Param("categoryId") Long categoryId);


}
