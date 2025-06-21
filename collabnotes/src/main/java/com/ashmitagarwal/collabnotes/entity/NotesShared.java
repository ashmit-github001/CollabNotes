package com.ashmitagarwal.collabnotes.entity;

import com.ashmitagarwal.collabotes.enums.NoteAccessLevel;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class NotesShared {
	
	@Id
	@GeneratedValue
	public String id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "note_id")
	private Note note;
	
	@Enumerated(EnumType.STRING)
	private NoteAccessLevel accessLevel;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

	public NoteAccessLevel getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(NoteAccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}

}
