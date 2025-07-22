package com.ashmitagarwal.collabnotes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ashmitagarwal.collabnotes.entity.NotesShared;
import com.ashmitagarwal.collabnotes.model.UserNoteResponseDto;

@Repository
public interface SharedNotesRepository extends JpaRepository<NotesShared,String> {

	@Query(value = "Select n.id, n.title, n.content, sn.access_level from notes_shared as sn, notes as n where n.id=sn.note_id and sn.user_id = :userId", nativeQuery = true)
	List<UserNoteResponseDto> getNotesSharedWithTheUser(String userId);
}
