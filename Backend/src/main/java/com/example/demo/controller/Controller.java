package com.example.demo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Job;
import com.example.demo.entity.JobSeeker;
import com.example.demo.entity.Recruiter;
import com.example.demo.service.JobSeekerService;
import com.example.demo.service.JobService;
import com.example.demo.service.RecruiterService;



@RestController
//@RequestMapping(value = "")
public class Controller {
	@Autowired
	JobSeekerService JSservice;
	
	@Autowired
	JobService Jservice;
	
	@Autowired
	RecruiterService Rservice;
	
	@GetMapping("/")
	public List<Job> allJobs(){
		return Jservice.allJobs();
	}

	@PostMapping("/registerRecruiter")
	public Recruiter registerRecruiter(@RequestBody Recruiter recruiter) throws Exception {
		return Rservice.register(recruiter);
	}

	@PostMapping("/loginRecruiter")
	public Recruiter loginRecruiter(@RequestBody Recruiter recruiter) throws Exception {
		return Rservice.loginRecruiter(recruiter);
	}

	@PostMapping("/registerJobSeeker")
	public JobSeeker registerJobSeeker(@RequestBody JobSeeker jobSeeker) throws Exception {
		return JSservice.register(jobSeeker);
	}

	@PostMapping("/loginJobSeeker")
	public JobSeeker loginJobSeeker(@RequestBody JobSeeker jobSeeker) throws Exception {
		return JSservice.loginJobSeeker(jobSeeker);
	}

	@GetMapping("fetchJob/{jobId}")
	public Job fetchJob(@PathVariable("jobId") UUID id) {
		return Jservice.findJob(id);
	}

	@GetMapping("/job/{text}")
//	@PreAuthorize("hasAuthority('ROLE_USER')")
	public List<Job> selectedJobs(@PathVariable("text") String text){
		return Jservice.selectedJobs(text);
	}
	

	@GetMapping("fetchJobSeeker/{JSid}")
//	@PreAuthorize("hasAuthority('ROLE_USER')")
	public JobSeeker fetchJobSeeker(@PathVariable("JSid") UUID id) {
		return JSservice.findJobSeeker(id);
	}

	@GetMapping("{JSid}/applyForJob/{jobId}")
//	@PreAuthorize("hasAuthority('ROLE_USER')")
	public void getJob(@PathVariable("jobId") UUID id,@PathVariable("JSid") UUID jsId) {
		JSservice.applyForJob(jsId,id);
	}

	@GetMapping("fetchRecruiter/{Rid}")
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Recruiter fetchRecruiter(@PathVariable("Rid") UUID id) {
		return Rservice.findRecruiter(id);
	}
	
	@PostMapping("/addJob/{Rid}")
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Job addJob(@PathVariable("Rid") UUID id,@RequestBody Job job) {
		return Jservice.addJob(id,job);
	}
	
	@GetMapping("/fetchAllSeeker")
	public List<JobSeeker> fetchAllSeeker(){
		return JSservice.fetchAll();
	}

	@GetMapping("/fetchAllRecruiter")
	public List<Recruiter> fetchAllRecruiter(){
		return Rservice.fetchAll();
	}

	@GetMapping("/fetchAllLocation")
	public List<String> fetchAllLocation(){
		return Jservice.getAllLocation();
	}
	
}
