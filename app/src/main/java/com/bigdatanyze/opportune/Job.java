package com.bigdatanyze.opportune;
public class Job {
	private String id;
	private String title;
	private String description;
	private String company;
	private String location;
	private double salary;
	private String datePosted;

	// Default constructor (existing)
	public Job() {
	}

	// Constructor with parameters
	public Job(String title, String company, String location, String description) {
		this.title = title;
		this.company = company;
		this.location = location;
		this.description = description;
		// Set other fields like salary and datePosted if needed, for now leaving them null or default
	}
	// Default constructor (existing)


	// Constructor with parameters (including salary and datePosted)
	public Job(String title, String company, String location, String description, double salary, String datePosted) {
		this.title = title;
		this.company = company;
		this.location = location;
		this.description = description;
		this.salary = salary;
		this.datePosted = datePosted;
	}



	// Getters and Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(String datePosted) {
		this.datePosted = datePosted;
	}
}
