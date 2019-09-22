package com.example.user.api.simpleuserservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Test
    public void should_find_user_by_id() {
        User user = new User("id_1","user_name","group_1");
        user = entityManager.persistAndFlush(user);
        assertEquals(userRepository.findById(user.getId()).get(), user);
    }
}