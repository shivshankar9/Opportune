package com.bigdatanyze.Opportune;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OpportuneApplication implements CommandLineRunner {

	@Autowired
	private JobRepository jobRepository;

	public static void main(String[] args) {
		SpringApplication.run(OpportuneApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Create and save some jobs for testing
		Job job1 = new Job();
		job1.setTitle("Software Engineer");
		job1.setDescription("Develop and maintain software applications.");

		Job job2 = new Job();
		job2.setTitle("Data Scientist");
		job2.setDescription("Analyze and interpret complex data.");

		jobRepository.save(job1);
		jobRepository.save(job2);

		System.out.println("Jobs saved:");
		jobRepository.findAll().forEach(job -> System.out.println(job.getTitle()));
	}
}
