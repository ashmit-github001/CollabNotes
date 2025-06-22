package com.ashmitagarwal.collabnotes.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ashmitagarwal.collabnotes.entity.Note;
import com.ashmitagarwal.collabnotes.model.NoteDto;
import com.ashmitagarwal.collabnotes.service.NotesService;

@RestController
@RequestMapping("notes")
public class NoteController {

	private NotesService notesService;
	
	public NoteController(NotesService notesService) {
		this.notesService = notesService;
	}
	
	@GetMapping("{userId}")
	public List<Note> GetUserCreatedNotes(@PathVariable("userId") String userId)
	{		
		System.out.println("To get notes of a user");
		return notesService.GetUserCreatedNotes(userId);
	}
	
	@PostMapping
	public boolean createNote(@RequestParam("userId") String userId, @RequestBody NoteDto createNoteDto)
	{
		System.out.println("To create a new note : " + createNoteDto.toString());
		return notesService.createNote(userId, createNoteDto);
	}
	
	@PutMapping
	public boolean updateNote(@RequestParam("noteId") String noteId, @RequestBody NoteDto updateNoteDto)
	{
		System.out.println("To update an existing note by a user : " + updateNoteDto.toString());
		return notesService.updateNote(noteId, updateNoteDto);
	}
	
	@DeleteMapping
	public boolean deleteNote(@RequestParam("noteId") String noteId)
	{
		System.out.println("To delete an existing note by a user : " + noteId);
		return notesService.deleteNote(noteId);
	}
}
