package com.bigdatanyze.opportune;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {

	private List<Job> jobList;

	public JobAdapter(List<Job> jobList) {
		this.jobList = jobList;
	}

	@Override
	public JobViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_job, parent, false);
		return new JobViewHolder(view);
	}

	@Override
	public void onBindViewHolder(JobViewHolder holder, int position) {
		Job job = jobList.get(position);
		holder.titleTextView.setText(job.getTitle());
		holder.companyTextView.setText(job.getCompany());
		holder.locationTextView.setText(job.getLocation());
		holder.salaryTextView.setText(String.format("$%,.2f", job.getSalary()));
		holder.datePostedTextView.setText(job.getDatePosted());

	}

	@Override
	public int getItemCount() {
		return jobList.size();
	}

	public static class JobViewHolder extends RecyclerView.ViewHolder {

		TextView titleTextView;
		TextView companyTextView;
		TextView locationTextView;
		TextView salaryTextView;
		TextView datePostedTextView;

		public JobViewHolder(View itemView) {
			super(itemView);
			titleTextView = itemView.findViewById(R.id.job_title);
			companyTextView = itemView.findViewById(R.id.job_company);
			locationTextView = itemView.findViewById(R.id.job_location);
			salaryTextView = itemView.findViewById(R.id.job_salary);
			datePostedTextView = itemView.findViewById(R.id.job_date_posted);
		}
	}
}
