package com.example.user.api.simpleuserservice.service;

import com.example.user.api.simpleuserservice.domain.User;
import com.example.user.api.simpleuserservice.api.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.user.api.simpleuserservice.repository.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService<User, String> {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    public User findById(String id) throws EntityNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, User.class));
    }

    @Override
    public <S extends User> List<S> saveAll(Iterable<S> var1) {
        return userRepository.saveAll(var1);
    }


    @Override
    public <S extends User> S save(S user) {
        return userRepository.save(user);
    }


    @Override
    public User delete(String id) throws EntityNotFoundException {
        User user = findById(id);
        userRepository.delete(user);
        return user;
    }


    @Override
    public Map<String, List<User>> findAllGroupByGroupId() {
        List<User> users = userRepository.findAll();
        return users.stream().collect(Collectors.groupingBy(User::getGroupId));
    }

}
