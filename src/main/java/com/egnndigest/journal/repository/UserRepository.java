package com.egnndigest.journal.repository;

import com.egnndigest.journal.entity.Journal;
import com.egnndigest.journal.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends MongoRepository<User, ObjectId> {

  User findByUserName(String userName);
}
