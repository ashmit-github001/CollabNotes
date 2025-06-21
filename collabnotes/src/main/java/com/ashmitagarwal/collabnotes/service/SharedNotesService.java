package com.ashmitagarwal.collabnotes.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ashmitagarwal.collabnotes.entity.NotesShared;
import com.ashmitagarwal.collabnotes.repository.NotesRepository;
import com.ashmitagarwal.collabnotes.repository.SharedNotesRepository;
import com.ashmitagarwal.collabnotes.repository.UserRepository;
import com.ashmitagarwal.collabotes.enums.NoteAccessLevel;

@Service
public class SharedNotesService {
	
	private NotesRepository notesRepository;
	
	private UserRepository userRepository;
	
	private SharedNotesRepository sharedNotesRepository;
	
	public SharedNotesService(NotesRepository notesRepository, UserRepository userRepository,
			SharedNotesRepository sharedNotesRepository) {
		this.notesRepository = notesRepository;
		this.userRepository = userRepository;
		this.sharedNotesRepository = sharedNotesRepository;
	}
	public boolean shareNoteWithUsersBasedOnAccess(String noteId, Map<String, String> userAccessMap) {
		
		List<NotesShared> listOfSharedNoteData = new ArrayList<>();
		
		if(notesRepository.existsById(noteId)) {
			
			for(String userId : userAccessMap.keySet()) {
				if(userRepository.existsById(userId)) {
					NotesShared notesShared = new NotesShared();
					notesShared.setUser(userRepository.findById(userId).get());
					notesShared.setNote(notesRepository.findById(noteId).get());
					notesShared.setAccessLevel(NoteAccessLevel.valueOf(userAccessMap.get(userId)));
					
					listOfSharedNoteData.add(notesShared);
				}
				else {
					System.out.println(String.format("User {0} does not exist, hence skipped", userId));
				}
			}
			
			sharedNotesRepository.saveAll(listOfSharedNoteData);
			return true;
		}
		
		System.out.println("Note does not exist.");
		return false;
	}
}
