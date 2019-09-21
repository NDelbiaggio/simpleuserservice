package com.example.user.api.simpleuserservice;

import com.example.user.api.simpleuserservice.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService implements IUserService<User, String>{


    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
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
        return null;
    }

    @Override
    public <S extends User> S save(S user) {
        return this.userRepository.save(user);
    }
    

    @Override
    public User delete(String id) throws EntityNotFoundException {
        User user = findById(id);
        userRepository.delete(user);
        return user;
    }


    @Override
    public Map<String, List<User>> findAllGroupByGroupId() {
        return null;
    }

}
