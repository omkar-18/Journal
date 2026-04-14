package com.egnndigest.journal.service;

import com.egnndigest.journal.entity.Journal;
import com.egnndigest.journal.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface IUserService {

  public User saveUser(User user);

  public Optional<List<User>> getAllUsers();

  public Optional<User> getUserByUserName(String userName);

}
