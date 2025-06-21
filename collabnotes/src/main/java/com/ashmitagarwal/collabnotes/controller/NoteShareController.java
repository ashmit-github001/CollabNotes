package com.ashmitagarwal.collabnotes.controller;

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
	public void shareNote(@RequestParam("noteId") String noteId, @RequestBody String listOfUserIds, @RequestParam("accessLevel") String accessLevel) {
		
	}
	
	@GetMapping("/accessLevel")
	public void getAccessLevels() {
		
	}
	
}
