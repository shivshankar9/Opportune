package com.bigdatanyze.opportune;

public class Job {

	private String title;
	private String company;
	private String location;

	public Job(String title, String company, String location, String jobDescription) {
		this.title = title;
		this.company = company;
		this.location = location;
	}

	public String getTitle() {
		return title;
	}

	public String getCompany() {
		return company;
	}

	public String getLocation() {
		return location;
	}
}
