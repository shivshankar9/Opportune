package com.bigdatanyze.Opportune;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;  // Primary key for the Job entity

	private String title;         // Job title
	private String description;   // Job description
	private String companyName;   // Company offering the job
	private String location;      // Job location (e.g., city, state, country)
	private Double salary;        // Job salary (optional)

	// Default constructor
	public Job() {}

	// Constructor with all fields
	public Job(String title, String description, String companyName, String location, Double salary) {
		this.title = title;
		this.description = description;
		this.companyName = companyName;
		this.location = location;
		this.salary = salary;
	}

	// Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	// Override toString method for easier debugging
	@Override
	public String toString() {
		return "Job{" +
				"id=" + id +
				", title='" + title + '\'' +
				", description='" + description + '\'' +
				", companyName='" + companyName + '\'' +
				", location='" + location + '\'' +
				", salary=" + salary +
				'}';
	}
}
