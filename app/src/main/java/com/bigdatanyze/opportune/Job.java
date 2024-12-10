package com.bigdatanyze.opportune;

public class Job {

	private String jobTitle;
	private String companyName;
	private String location;

	// Constructor
	public Job(String jobTitle, String companyName, String location, String jobDescription) {
		this.jobTitle = jobTitle;
		this.companyName = companyName;
		this.location = location;
	}

	// Getter methods
	public String getJobTitle() {
		return jobTitle;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getLocation() {
		return location;
	}
}
