package com.bigdatanyze.opportune;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {

	private List<Job> jobList;
	private Context context; // Declare a context variable

	public JobAdapter(List<Job> jobList) {
		this.jobList = jobList;
	}

	@Override
	public JobViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		if (context == null) {
			context = parent.getContext(); // Retrieve context from parent
		}
		View view = LayoutInflater.from(context).inflate(R.layout.item_job, parent, false);
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

		// When apply button is clicked, open the apply link in the browser
		holder.applyButton.setOnClickListener(v -> {
			String applyLink = job.getApplyLink();
			if (applyLink != null && !applyLink.isEmpty()) {
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(applyLink));
				context.startActivity(intent);
			} else {
				Toast.makeText(context, "No apply link available", Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public int getItemCount() {
		return jobList.size();
	}

	public void updateData(List<Job> newJobList) {
		this.jobList = newJobList;
		notifyDataSetChanged();
	}

	public static class JobViewHolder extends RecyclerView.ViewHolder {
		TextView titleTextView;
		TextView companyTextView;
		TextView locationTextView;
		TextView salaryTextView;
		TextView datePostedTextView;
		Button applyButton;

		public JobViewHolder(View itemView) {
			super(itemView);
			titleTextView = itemView.findViewById(R.id.job_title);
			companyTextView = itemView.findViewById(R.id.job_company);
			locationTextView = itemView.findViewById(R.id.job_location);
			salaryTextView = itemView.findViewById(R.id.job_salary);
			datePostedTextView = itemView.findViewById(R.id.job_date_posted);
			applyButton = itemView.findViewById(R.id.apply_button);
		}
	}
}
