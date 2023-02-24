package com.zulhke.zuello;

import com.zulhke.zuello.models.user.User;
import com.zulhke.zuello.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ZuelloApiApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Test
    void findAll() {
        int count = userRepository.findAll().size();

        assertTrue(count > 0, "number of user > 0");
    }

    @Test
    void findByEmail() {
        User user = userRepository.findByEmail("oliviaj@example.com").orElse(null);
        assertNotNull(user);
    }

}
