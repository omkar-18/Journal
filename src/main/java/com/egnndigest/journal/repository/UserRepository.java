package com.egnndigest.journal.repository;

import com.egnndigest.journal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends JpaRepository<User,Integer> {

  User findByUserName(String userName);
}
