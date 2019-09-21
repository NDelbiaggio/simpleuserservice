package com.example.user.api.simpleuserservice;

import com.example.user.api.simpleuserservice.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService<User, String> userService;

    @Autowired
    public UserController(final IUserService<User, String> userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.findAll();
    }

    @GetMapping("{id}")
    public User getUserById(@PathVariable String id) throws EntityNotFoundException {
        return userService.findById(id);
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user){
        return userService.save(user);
    }


    @DeleteMapping("{id}")
    public User deleteUser(@PathVariable String id) throws EntityNotFoundException{
        return userService.delete(id);
    }
}
