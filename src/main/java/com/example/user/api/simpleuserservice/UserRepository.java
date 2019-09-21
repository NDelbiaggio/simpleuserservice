package com.example.user.api.simpleuserservice;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {
}
