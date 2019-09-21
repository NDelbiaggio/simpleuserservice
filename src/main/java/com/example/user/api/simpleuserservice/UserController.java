package com.example.user.api.simpleuserservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return this.userService.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return this.userService.save(user);
    }
}
