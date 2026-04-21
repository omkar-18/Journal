package com.egnndigest.journal.repository;

import com.egnndigest.journal.entity.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface JournalEntryRepository extends JpaRepository<Journal , Integer> {

}
