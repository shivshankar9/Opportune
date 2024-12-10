package com.bigdatanyze.opportune;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {

	private Context context;
	private List<Job> jobList;

	public JobAdapter(Context context, List<Job> jobList) {
		this.context = context;
		this.jobList = jobList;
	}

	@NonNull
	@Override
	public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(context).inflate(R.layout.item_job, parent, false);
		return new JobViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
		Job job = jobList.get(position);

		// Use the correct getter methods from your Job class
		holder.jobTitle.setText(job.getJobTitle());  // Correct method
		holder.companyName.setText(job.getCompanyName());  // Correct method
		holder.location.setText(job.getLocation());  // Correct method
	}

	@Override
	public int getItemCount() {
		return jobList.size();
	}

	class JobViewHolder extends RecyclerView.ViewHolder {

		TextView jobTitle, companyName, location;

		public JobViewHolder(@NonNull View itemView) {
			super(itemView);
			jobTitle = itemView.findViewById(R.id.job_title);
			companyName = itemView.findViewById(R.id.company_name);
			location = itemView.findViewById(R.id.location);
		}
	}
}
