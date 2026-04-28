package com.egnndigest.journal.service;

import com.egnndigest.journal.entity.Journal;
import com.egnndigest.journal.entity.User;
import com.egnndigest.journal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class UserService implements  IUserService, UserDetailsService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Override
  public User saveUser(User user) {
    User user1 = new User.Builder().userName(user.getUsername())
        .password(passwordEncoder.encode(user.getPassword()))
//        .password(user.getPassword())
        .authorities(user.getRawAuthorities())
        .journals(user.getJournals())
        .build();

    return userRepository.save(user1);
  }

  @Override
  public Optional<List<User>> getAllUsers() {
    return Optional.of(userRepository.findAll());
  }

  @Override
  public Optional<User> getUserByUserName(String userName) {
    return Optional.of((User) userRepository.findByUserName(userName));
  }

  @Transactional
  public void saveJournalAgainstUser(String userName, Journal journal){
    try {
      User user = (User) userRepository.findByUserName(userName);
      if(user != null){
        user.getJournals().add(journal);
      }
      userRepository.save(user);
    }catch (Exception e){
      System.out.println(e);
      throw new RuntimeException("Exception while saving ");
    }
  }

  public Optional<Journal> getJournalById(int journalId,String userName){
    User  user = (User) userRepository.findByUserName(userName);

    if (user != null){
      for(int i=0;i<user.getJournals().size();i++){
        if(user.getJournals().get(i).getId()==(journalId)){
          return Optional.ofNullable(user.getJournals().get(i));
        }
      }
    }
    return Optional.empty();

  }

  public Optional<List<Journal>> getAllJournal(String userName){
    User  user = (User) userRepository.findByUserName(userName);

    return Optional.ofNullable(user.getJournals());

  }

  public boolean deleteJournalById(int journalId, String userName){
    User  user = (User) userRepository.findByUserName(userName);

    user.getJournals().removeIf(journal -> journal.getId()== (journalId));
    userRepository.save(user);
    return true;

  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    System.out.println("looking ofr use "+username);

    UserDetails user =  userRepository.findByUserName(username);
    System.out.println("user is "+(User)user);
    return user;
  }
}


