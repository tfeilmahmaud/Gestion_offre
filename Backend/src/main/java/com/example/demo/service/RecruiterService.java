package com.example.demo.service;
import java.util.List;
import java.util.UUID;

import com.example.demo.dao.RecruiterRepository;
import com.example.demo.entity.Recruiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecruiterService {

	@Autowired
	RecruiterRepository repo;

	public Recruiter register(Recruiter recruiter) throws Exception {
		Recruiter tempObj = repo.findByEmail(recruiter.getEmail());
		if(tempObj!=null) {
			throw new Exception("This user already exists ");
		}
//		if(recruiter.getJobList()==null) {
//			recruiter.setJobList(new ArrayList<Job>());
//		}
		return repo.save(recruiter);
	}

	public Recruiter loginRecruiter(Recruiter recruiter) throws Exception {
		Recruiter tempObj = repo.findByEmail(recruiter.getEmail());
		if(tempObj==null) {
			throw new Exception("User didn't exists");
		}
//		else if(!user.getPassword().equals(tempObj.getPassword())) {
		else if(!recruiter.getPassword().equals(tempObj.getPassword())) {
			throw new Exception("Bad credentials");
		}

		return tempObj;
	}

	public Recruiter findRecruiter(UUID id) {
		return repo.findById(id).orElse(null);
		
	}


    public List<Recruiter> fetchAll() {
		return repo.findAll();
    }
}
