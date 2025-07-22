package com.ashmitagarwal.collabnotes.model;

import com.ashmitagarwal.collabotes.enums.NoteAccessLevel;

public class UserNoteResponseDto {
	
	private String id;

	private String title;
	
	private String content;
	
	private NoteAccessLevel accessLevel;
	
	public String getId() {
		return title;
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

	public NoteAccessLevel getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(NoteAccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}

	@Override
	public String toString() {
		return "AllUserNoteDto [id=" + id + ", title=" + title + ", content=" + content + ", accessLevel=" + accessLevel.toString() + "]";
	}
}
