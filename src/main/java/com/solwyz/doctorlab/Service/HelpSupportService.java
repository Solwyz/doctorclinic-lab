package com.solwyz.doctorlab.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solwyz.doctorlab.Entity.HelpSupport;
import com.solwyz.doctorlab.Repo.HelpSupportRepo;

@Service
public class HelpSupportService {
	
	 @Autowired
	    private HelpSupportRepo helpSupportRepository;

	    public HelpSupport submitQuery(HelpSupport helpSupport) {
	        return helpSupportRepository.save(helpSupport);
	    }

	    public List<HelpSupport> getAllQueries() {
	        return helpSupportRepository.findAll();
	    }

}
