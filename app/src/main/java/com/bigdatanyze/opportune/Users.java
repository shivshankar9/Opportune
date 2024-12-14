package com.bigdatanyze.opportune;

public class Users {

	private String id;          // User ID (email or unique generated ID)
	private String name;
	private String email;
	private String phoneNo;
	private boolean isSubscribed;
	private String password;    // Optional: add a password field for login

	// Constructor with all fields
	public Users(String id, String name, String email, String phoneNo, boolean isSubscribed, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phoneNo = phoneNo;
		this.isSubscribed = isSubscribed;
		this.password = password;
	}

	// Default constructor
	public Users() {}

	// Getters and setters

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public boolean isSubscribed() {
		return isSubscribed;
	}

	public void setSubscribed(boolean isSubscribed) {
		this.isSubscribed = isSubscribed;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// toString method to display the User details
	@Override
	public String toString() {
		return "User{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", phoneNo='" + phoneNo + '\'' +
				", isSubscribed=" + isSubscribed +
				", password='" + (password != null ? "*****" : "null") + '\'' +  // Mask password for security
				'}';
	}
}
