package com.ashmitagarwal.collabnotes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ashmitagarwal.collabnotes.entity.Note;
import com.ashmitagarwal.collabnotes.entity.User;
import com.ashmitagarwal.collabnotes.model.NoteDto;
import com.ashmitagarwal.collabnotes.repository.NotesRepository;
import com.ashmitagarwal.collabnotes.repository.UserRepository;

@Service
public class NotesService {
	
	private NotesRepository notesRepository;
	
	private UserRepository userRepository;
	
	public NotesService(NotesRepository notesRepository, UserRepository userRepository) {
		this.notesRepository = notesRepository;
		this.userRepository = userRepository;
	}

	public List<Note> GetUserCreatedNotes(String userId){
		return notesRepository.getAllNotesByUserId(userId);
	}

	public boolean createNote(String userId, NoteDto createNoteDto) {
		
		Note note = mapNoteDtoToEntity(createNoteDto);
		
		if(userRepository.existsById(userId)) {
			User user = userRepository.findById(userId).get();
			note.setOwner(user);
			notesRepository.save(note);
			return true;
		}
		
		System.out.println("No such user exists.");
		return false;
	}
	
	public boolean updateNote(String noteId, NoteDto updateNoteDto) {
		
		if(notesRepository.existsById(noteId)) {
			Note note = notesRepository.findById(noteId).get();
			note.setTitle(updateNoteDto.getTitle());
			note.setContent(updateNoteDto.getContent());
			notesRepository.save(note);
			return true;
		}
		
		System.out.println("No such note exists.");
		return false;
	}
	
	public boolean deleteNote(String noteId) {
		notesRepository.deleteById(noteId);
		return true;
	}
	
	private Note mapNoteDtoToEntity(NoteDto dto) {
		Note note = new Note();
		note.setTitle(dto.getTitle());
		note.setContent(dto.getContent());	
		return note;
	}
}
