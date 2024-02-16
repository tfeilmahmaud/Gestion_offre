package com.example.demo.dao;

import java.util.UUID;

import com.example.demo.entity.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Recruiter;

@Repository
public interface RecruiterRepository extends JpaRepository<Recruiter, UUID>{

    Recruiter findByEmail(String email);

}
