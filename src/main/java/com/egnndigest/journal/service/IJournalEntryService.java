package com.egnndigest.journal.service;

import com.egnndigest.journal.entity.Journal;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface IJournalEntryService {

  public Journal saveJournal(Journal journal, String userName);

  public Optional<List<Journal>> getAllJournal(String userName);

  public Optional<Journal> getJournalById(ObjectId journalId, String userName);

  public void deleteById(ObjectId journalId, String userName);
}
