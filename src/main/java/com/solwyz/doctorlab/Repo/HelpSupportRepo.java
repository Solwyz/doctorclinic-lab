package com.solwyz.doctorlab.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solwyz.doctorlab.Entity.HelpSupport;

@Repository
public interface HelpSupportRepo extends JpaRepository<HelpSupport, Long>{
	
	

}
