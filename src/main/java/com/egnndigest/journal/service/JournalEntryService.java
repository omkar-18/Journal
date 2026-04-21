package com.egnndigest.journal.service;

import com.egnndigest.journal.entity.Journal;
import com.egnndigest.journal.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService implements IJournalEntryService{

  @Autowired
  JournalEntryRepository journalEntryRepository;

  @Autowired
  UserService userService;


  @Override
  @Transactional
  public Journal saveJournal(Journal journal, String userName) {
    try {
      Journal savedJournal =  journalEntryRepository.save(journal);
      userService.saveJournalAgainstUser(userName,savedJournal);
      return savedJournal;
    }catch (Exception e){
      System.out.println(e);
      throw new RuntimeException("Exception while saving journal");
    }
  }

  @Override
  public Optional<List<Journal>> getAllJournal(String userName) {
    return userService.getAllJournal(userName);
  }

  @Override
  public Optional<Journal> getJournalById(int journalId, String userName) {
    return userService.getJournalById(journalId,userName);
  }

  @Override
  public void deleteById(int journalId, String userName) {
    boolean deletedJournal = userService.deleteJournalById(journalId,userName);
    if(deletedJournal){
      journalEntryRepository.deleteById(journalId);
    }
  }
}
