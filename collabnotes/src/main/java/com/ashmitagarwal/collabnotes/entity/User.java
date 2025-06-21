package com.ashmitagarwal.collabnotes.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {

	@Id
	private String id;
	
	private String email;
	
	private String password;
	
	private String fullName;
	
	@OneToMany(mappedBy = "owner")
	private List<Note> notesCreated;
	
	@OneToMany(mappedBy = "user")
	private List<NotesShared> sharedNotes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public List<Note> getNotesCreated() {
		return notesCreated;
	}

	public void setNotesCreated(List<Note> notesCreated) {
		this.notesCreated = notesCreated;
	}

	public List<NotesShared> getSharedNotes() {
		return sharedNotes;
	}

	public void setSharedNotes(List<NotesShared> sharedNotes) {
		this.sharedNotes = sharedNotes;
	}
}
