package com.egnndigest.journal.repository;

import com.egnndigest.journal.entity.Journal;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface JournalEntryRepository extends MongoRepository<Journal , ObjectId> {

}
