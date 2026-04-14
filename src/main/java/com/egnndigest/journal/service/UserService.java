package com.egnndigest.journal.service;

import com.egnndigest.journal.entity.Journal;
import com.egnndigest.journal.entity.User;
import com.egnndigest.journal.repository.JournalEntryRepository;
import com.egnndigest.journal.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class UserService implements  IUserService{

  @Autowired
  UserRepository userRepository;

  @Override
  public User saveUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public Optional<List<User>> getAllUsers() {
    return Optional.of(userRepository.findAll());
  }

  @Override
  public Optional<User> getUserByUserName(String userName) {
    return Optional.of(userRepository.findByUserName(userName));
  }

  @Transactional
  public void saveJournalAgainstUser(String userName, Journal journal){
    try {
      User user = userRepository.findByUserName(userName);
      if(user != null){
        user.getJournals().add(journal);
      }
      userRepository.save(user);
    }catch (Exception e){
      System.out.println(e);
      throw new RuntimeException("Exception while saving ");
    }
  }

  public Optional<Journal> getJournalById(ObjectId journalId,String userName){
    User  user = userRepository.findByUserName(userName);

    if (user != null){
      for(int i=0;i<user.getJournals().size();i++){
        if(user.getJournals().get(i).getId().equals(journalId)){
          return Optional.ofNullable(user.getJournals().get(i));
        }
      }
    }
    return Optional.empty();

  }

  public Optional<List<Journal>> getAllJournal(String userName){
    User  user = userRepository.findByUserName(userName);

    return Optional.ofNullable(user.getJournals());

  }

  public boolean deleteJournalById(ObjectId journalId, String userName){
    User  user = userRepository.findByUserName(userName);

    user.getJournals().removeIf(journal -> journal.getId().equals(journalId));
    userRepository.save(user);
    return true;

  }
}


