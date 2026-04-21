package com.egnndigest.journal.controller;

import com.egnndigest.journal.entity.Journal;
import com.egnndigest.journal.service.IJournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journalEntry")
public class JournalEntryController {

  @Autowired
  IJournalEntryService iJournalEntryService;

  @PostMapping("/username/{userName}")
  public Journal savejournal(@RequestBody Journal journal, @PathVariable String userName) {
    System.out.println("journal is " + journal.toString());
    return iJournalEntryService.saveJournal(journal,userName);
  }

  @GetMapping("/getAll/userName/{userName}")
  public ResponseEntity<List<Journal>> getAllJournal(@PathVariable String userName) {
    Optional<List<Journal>> journals = iJournalEntryService.getAllJournal(userName);

    return journals.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
  }

  @GetMapping("/id/{journalId}/userName/{userName}")
  public ResponseEntity<Journal> getJournalById(@PathVariable int journalId,@PathVariable String userName) {
    Optional<Journal> journal = iJournalEntryService.getJournalById(journalId, userName);

    return journal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
  }

  @DeleteMapping("/id/{journalId}/userName/{userName}")
  public ResponseEntity<?> deleteById(@PathVariable int journalId, @PathVariable String userName){
    iJournalEntryService.deleteById(journalId,userName);
    return ResponseEntity.noContent().build();

  }
}
