package com.ashmitagarwal.collabnotes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ashmitagarwal.collabnotes.entity.Note;

@Repository
public interface NotesRepository extends JpaRepository<Note, String> {

	@Query(value = "Select n from Note as n where n.owner.id = ?1")
	List<Note> getAllNotesByUserId(String userid);
}
