package com.ashmitagarwal.collabnotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashmitagarwal.collabnotes.entity.NotesShared;

@Repository
public interface SharedNotesRepository extends JpaRepository<NotesShared,String> {

}
