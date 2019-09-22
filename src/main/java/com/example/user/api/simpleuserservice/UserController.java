package com.example.user.api.simpleuserservice;

import com.example.user.api.simpleuserservice.exceptions.EntityNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Api(tags = "Users")
public class UserController {

    @Autowired
    private final IUserService<User, String> userService;

    public UserController(final IUserService<User, String> userService) {
        this.userService = userService;
    }

    @GetMapping
    @ApiOperation(value = "Get all users")
    public List<User> getAllUsers(){
        return userService.findAll();
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Get a specific user")
    public User getUserById(@PathVariable String id) throws EntityNotFoundException {
        return userService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a user")
    public User createUser(@Valid @RequestBody User user){
        return userService.save(user);
    }

    @PostMapping("/several")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create several users")
    public List<User> createUsers(@Valid @RequestBody List<User> users){
        return userService.saveAll(users);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete a specific user")
    public User deleteUser(@PathVariable String id) throws EntityNotFoundException{
        return userService.delete(id);
    }

    @GetMapping("/groups")
    @ApiOperation(value = "Get all groups with their belonging users")
    public Map<String, List<User>> getGroupByGroupId(){
        return userService.findAllGroupByGroupId();
    }
}
