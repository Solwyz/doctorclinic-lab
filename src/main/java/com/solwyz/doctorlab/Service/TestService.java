package com.solwyz.doctorlab.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solwyz.doctorlab.Entity.Test;
import com.solwyz.doctorlab.Repo.TestRepository;

@Service
@Transactional
public class TestService {

	
	@Autowired
	private TestRepository testRepository;
	
	 private static final Logger logger = LoggerFactory.getLogger(TestService.class);
	 

	 public Test createTest(Test test) {
	        logger.info("Saving test to database: {}", test);
	        Test savedTest = testRepository.save(test);
	        logger.info("Test saved successfully with ID: {}", savedTest.getId());
	        return savedTest;
	    }
	public Test updateTest(Long id, Test testDetails) {
	    Test test = testRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Test not found")); 

	    test.setTestName(testDetails.getTestName());
	    test.setTestDetails(testDetails.getTestDetails());
	   // test.setTestCount(testDetails.getTestCount());

	    return testRepository.save(test);
	}

	public List<Test> getAllTests() {
	    return testRepository.findAll();
	}

	public Test getTestById(Long id) {
		return testRepository.findById(id).orElseThrow(() -> new RuntimeException("Test not found"));
	}

	public void deleteTest(Long id) {
		testRepository.deleteById(id);
	}


	public List<Test> getTestsByCategory(Long categoryId) {
	    return testRepository.findByCategoryId(categoryId);
	}


	

}
