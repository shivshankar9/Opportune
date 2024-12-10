package com.bigdatanyze.Opportune;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs") // Ensure this is correct
public class JobController {

	@Autowired
	private JobRepository jobRepository;

	// POST: /api/jobs/jobpost to create a new job
	@PostMapping("/jobpost")
	public ResponseEntity<Job> postJob(@RequestBody Job job) {
		if (job == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Job savedJob = jobRepository.save(job);
		return new ResponseEntity<>(savedJob, HttpStatus.CREATED);
	}

	// GET: /api/jobs to retrieve all jobs
	@GetMapping
	public ResponseEntity<List<Job>> getAllJobs() {
		List<Job> jobs = jobRepository.findAll();
		if (jobs.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(jobs, HttpStatus.OK);
	}
}
