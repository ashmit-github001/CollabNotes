package com.ashmitagarwal.collabnotes.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity //(name = "notes") <-- Can cause issue in writing JPQL
@Table(name = "notes")
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	private String title;
	
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User owner;
	
	@OneToMany(mappedBy = "note")
	@JsonIgnore
	private List<NotesShared> sharedWith;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<NotesShared> getSharedWith() {
		return sharedWith;
	}

	public void setSharedWith(List<NotesShared> sharedWith) {
		this.sharedWith = sharedWith;
	}
}
