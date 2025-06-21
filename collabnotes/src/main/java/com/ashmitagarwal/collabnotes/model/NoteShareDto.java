package com.ashmitagarwal.collabnotes.model;

public class NoteShareDto {
	
	String userId;
	
	String accessLevel;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}

	@Override
	public String toString() {
		return "NoteShareDto [userId=" + userId + ", accessLevel=" + accessLevel + "]";
	}
}
