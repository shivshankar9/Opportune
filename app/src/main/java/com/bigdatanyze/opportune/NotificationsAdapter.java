package com.bigdatanyze.opportune;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotificationViewHolder> {

	private Context context;
	private List<Notification> notificationList;

	public NotificationsAdapter(Context context, List<Notification> notificationList) {
		this.context = context;
		this.notificationList = notificationList;
	}

	@NonNull
	@Override
	public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(context).inflate(R.layout.item_notification, parent, false);
		return new NotificationViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
		Notification notification = notificationList.get(position);
		holder.title.setText(notification.getTitle());
		holder.message.setText(notification.getMessage());
	}

	@Override
	public int getItemCount() {
		return notificationList.size();
	}

	class NotificationViewHolder extends RecyclerView.ViewHolder {

		TextView title, message;

		public NotificationViewHolder(@NonNull View itemView) {
			super(itemView);
			title = itemView.findViewById(R.id.notification_title);
			message = itemView.findViewById(R.id.notification_message);
		}
	}
}
