package com.example.authentificationservice.Repository;

import com.example.authentificationservice.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
   Optional<User>  findUserByEmail(String email);
}