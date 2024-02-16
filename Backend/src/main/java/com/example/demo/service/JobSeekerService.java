package com.example.demo.service;

import com.example.demo.dao.JobSeekerRepository;
import com.example.demo.entity.Job;
import com.example.demo.entity.JobSeeker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class JobSeekerService {

	@Autowired
	JobSeekerRepository repo;
	
	@Autowired
	JobService Jservice;

	public JobSeeker register(JobSeeker jobSeeker) throws Exception {
		JobSeeker tempObj = repo.findByEmail(jobSeeker.getEmail());
		if(tempObj!=null) {
			throw new Exception("This user already exists ");
		}
		if(jobSeeker.getJobList()==null) {
			jobSeeker.setJobList(new ArrayList<Job>());
		}
		return repo.save(jobSeeker);
	}

	public JobSeeker loginJobSeeker(JobSeeker jobSeeker) throws Exception {
		JobSeeker tempObj = repo.findByEmail(jobSeeker.getEmail());
		if(tempObj==null) {
			throw new Exception("User didn't exists");
		}
//		else if(!user.getPassword().equals(tempObj.getPassword())) {
		else if(!jobSeeker.getPassword().equals(tempObj.getPassword())) {
			throw new Exception("Bad credentials");
		}

		return tempObj;
	}

	public void applyForJob(UUID jsId, UUID id) {
//		Job tempJob=Jservice.findJob(id);
//		JobSeeker tempJobSeeker = repo.findById(jsId).orElse(null);
//		List<Job> tempJobList = tempJobSeeker.getJobList();
//		if(tempJobList==null) {
//			tempJobList = new ArrayList<Job>();
//		}
//		tempJobList.add(tempJob);
//		repo.findById(jsId).orElse(null).setJobList(tempJobList);
//		List<JobSeeker> tempJobSeekerList = tempJob.getJobSeekerList();
//		if(tempJobSeekerList==null) {
//			tempJobSeekerList=new ArrayList<JobSeeker>();
//		}
//		tempJobSeekerList.add(repo.findById(jsId).orElse(null));
//		Jservice.findJob(id).setJobSeekerList(tempJobSeekerList);
		Job tempJob=Jservice.findJob(id);
		System.out.println("This is here");
		if(findJobSeeker(jsId).getJobList()==null) {
			System.out.println(findJobSeeker(jsId).getJobList());
			findJobSeeker(jsId).setJobList(new ArrayList<Job>());
			System.out.println("is it working");
		}
		findJobSeeker(jsId).getJobList().add(tempJob);
		JobSeeker tempJobSeeker = findJobSeeker(jsId);
//		System.out.println(tempJobSeeker);
		if(Jservice.findJob(id).getJobSeekerList()==null) {
			Jservice.findJob(id).setJobSeekerList(new ArrayList<JobSeeker>());
			System.out.println("working");
		}
		List<JobSeeker> tempJobSeekerList = Jservice.findJob(id).getJobSeekerList();
		tempJobSeekerList.add(tempJobSeeker);
		Jservice.findJob(id).setJobSeekerList(tempJobSeekerList);
		tempJobSeeker.setJobList(findJobSeeker(jsId).getJobList());
		repo.saveAndFlush(tempJobSeeker);
	}


	public JobSeeker findJobSeeker(UUID id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElse(null);
	}

    public List<JobSeeker> fetchAll() {
		return repo.findAll();
    }
}
