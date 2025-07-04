package com.ashmitagarwal.collabnotes.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ashmitagarwal.collabnotes.service.SharedNotesService;

@RestController
@RequestMapping("/note/share")
public class NoteShareController {

	private SharedNotesService sharedNotesService;
	
	public NoteShareController(SharedNotesService sharedNotesService) {
		this.sharedNotesService = sharedNotesService;
	}
	
	@PostMapping("")
	public void shareNote(@RequestParam("noteId") String noteId, @RequestBody Map<String, String> userAccessMap) {
		sharedNotesService.shareNoteWithUsersBasedOnAccess(noteId, userAccessMap);
	}
	
	@GetMapping("/accessLevel")
	public List<String> getAccessLevels() {
		return sharedNotesService.getAccessLevels();
	}
	
}
