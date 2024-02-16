package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import lombok.Data;

import java.util.*;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity(name="Recruiter")
@Data
@Embeddable
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Recruiter {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String email;
    private String password;
    private String position;
    private String companyName;
    @JsonIgnoreProperties({"recruiter"})
    @OneToMany(mappedBy="recruiter")
    private List<Job> jobList;

    public Recruiter() {
    }

    public Recruiter(UUID id, String name, String email, String password, String position, String companyName, List<Job> jobList) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.position = position;
        this.companyName = companyName;
        this.jobList = jobList;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Job> getJobList() {
        return jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
    }

	@Override
	public String toString() {
		return "Recruiter [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", position="
				+ position + ", companyName=" + companyName + ", jobList=" + jobList + "]";
	}

    
}