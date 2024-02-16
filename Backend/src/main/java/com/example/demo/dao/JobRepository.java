package com.example.demo.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, UUID>{

    List<Job> findAllByTitle(String text);
}
