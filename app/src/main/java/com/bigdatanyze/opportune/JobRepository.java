package com.bigdatanyze.opportune;

import kotlinx.coroutines.Job;

import java.util.ArrayList;
import java.util.List;

public class JobRepository {
	private List<Job> jobs = new ArrayList<>();

	// Save a job to the repository
	public Job save(Job job) {
		jobs.add(job);
		return job;
	}

	// Retrieve all jobs from the repository
	public List<Job> findAll() {
		return new ArrayList<>(jobs);
	}
}
