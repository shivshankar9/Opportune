package com.bigdatanyze.opportune;

public class RegisterUserRequest {
	private String name;
	private String email;
	private String phoneNo;
	private boolean isSubscribed;
	private String password;

	// Constructor
	public RegisterUserRequest(String name, String email, String phoneNo, boolean isSubscribed, String password) {
		this.name = name;
		this.email = email;
		this.phoneNo = phoneNo;
		this.isSubscribed = isSubscribed;
		this.password = password;
	}

	// Getters and Setters
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
}
