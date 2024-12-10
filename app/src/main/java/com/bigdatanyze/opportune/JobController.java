//package com.bigdatanyze.opportune;
//
//import com.bigdatanyze.opportune.Job;
//import com.bigdatanyze.opportune.JobRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/jobs")
//public class JobController {
//
//	@Autowired
//	private JobRepository jobRepository;
//
//	// POST endpoint to save a job
//	@PostMapping
//	public Job postJob(@RequestBody Job job) {
//		return jobRepository.save(job);
//	}
//
//	// GET endpoint to retrieve all jobs
//	@GetMapping
//	public List<Job> getAllJobs() {
//		return jobRepository.findAll();
//	}
//}
