package com.egnndigest.journal.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

  private static final String AUTHORITIES_DELIMITER = "::";


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NonNull
  private String userName;

  @NonNull
  private String password;

  private String authorities;

  @OneToMany
  private List<Journal> journals = new ArrayList<>();

  private User(Builder builder){
    this.userName = builder.userName;
    this.password = builder.password;
    this.authorities = builder.authorities;
    this.journals = builder.journals;
  }

  public class Builder{
    private int id;
    private String userName;
    private String password;
    private String authorities;
    private List<Journal> journals = new ArrayList<>();

    public Builder userName(String userName){
      this.userName=userName;
      return this;
    }

    public Builder password(String password){
      this.password = password;
      return this;
    }

    public Builder authorities(String authorities){
      this.authorities = authorities;
      return this;
    }
    public Builder journals(List<Journal> journals){
      this.journals = journals;
      return this;
    }
    public User build(){
      //we will add validation here
      return new User(this);
    }


  }



  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Arrays.stream(this.authorities.split(AUTHORITIES_DELIMITER))
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return "";
  }

  public List<Journal> getJournals() {
    return journals;
  }

  public void setJournals(List<Journal> journals) {
    this.journals = journals;
  }

  @Override
  public boolean isAccountNonExpired() {
    return UserDetails.super.isAccountNonExpired();
  }

  @Override
  public boolean isAccountNonLocked() {
    return UserDetails.super.isAccountNonLocked();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return UserDetails.super.isCredentialsNonExpired();
  }

  @Override
  public boolean isEnabled() {
    return UserDetails.super.isEnabled();
  }
}
