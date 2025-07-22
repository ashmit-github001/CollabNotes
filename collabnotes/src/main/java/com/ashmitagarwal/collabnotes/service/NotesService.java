package com.ashmitagarwal.collabnotes.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ashmitagarwal.collabnotes.entity.Note;
import com.ashmitagarwal.collabnotes.entity.User;
import com.ashmitagarwal.collabnotes.model.NoteDto;
import com.ashmitagarwal.collabnotes.model.UserNoteResponseDto;
import com.ashmitagarwal.collabnotes.repository.NotesRepository;
import com.ashmitagarwal.collabnotes.repository.SharedNotesRepository;
import com.ashmitagarwal.collabnotes.repository.UserRepository;
import com.ashmitagarwal.collabotes.enums.NoteAccessLevel;

@Service
public class NotesService {
	
	private NotesRepository notesRepository;
	
	private UserRepository userRepository;
	
	private SharedNotesRepository sharedNotesRepository;
	
	public NotesService(NotesRepository notesRepository, UserRepository userRepository
			, SharedNotesRepository sharedNotesRepository) {
		this.notesRepository = notesRepository;
		this.userRepository = userRepository;
		this.sharedNotesRepository = sharedNotesRepository;
	}

	public Map<String, List<UserNoteResponseDto>> GetUserNotes(String userId){

		Map<String, List<UserNoteResponseDto>> userNotes = new HashMap<>();
		userNotes.put("createdNotes", mapNoteEntityToUserNoteResponseDto(notesRepository.getAllNotesByUserId(userId)));		
		userNotes.put("sharedNotes", sharedNotesRepository.getNotesSharedWithTheUser(userId));		
		return userNotes;
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
	
	private List<UserNoteResponseDto> mapNoteEntityToUserNoteResponseDto(List<Note> notes) {
		
		List<UserNoteResponseDto> userNoteResponseDtoList = new ArrayList<>();
		for(Note note : notes) {
			UserNoteResponseDto userNoteResponseDto = new UserNoteResponseDto();
			userNoteResponseDto.setId(note.getId());
			userNoteResponseDto.setTitle(note.getTitle());
			userNoteResponseDto.setContent(note.getContent());
			userNoteResponseDto.setAccessLevel(NoteAccessLevel.EDIT);
			userNoteResponseDtoList.add(userNoteResponseDto);
		}

		return userNoteResponseDtoList;
	}
	
}
