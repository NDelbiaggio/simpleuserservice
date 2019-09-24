package com.example.user.api.simpleuserservice.repository;

import com.example.user.api.simpleuserservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {
}
