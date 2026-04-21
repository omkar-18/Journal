package com.egnndigest.journal.controller;
import com.egnndigest.journal.entity.User;
import com.egnndigest.journal.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  IUserService iUserService;

  @GetMapping("/getAll")
  public ResponseEntity<List<User>> getAllJournal() {
    Optional<List<User>> users = iUserService.getAllUsers();

    return users.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
  }

  @PostMapping("/save")
  public User saveUser(@RequestBody User user) {
    return iUserService.saveUser(user);
  }

  @GetMapping("/{userName}")
  public ResponseEntity<User> getJournalById(@PathVariable String userName) {
    Optional<User> user = iUserService.getUserByUserName(userName);

    return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
  }

}
