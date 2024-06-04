package com.sakurapuare.flightmanagement.mapper.user;

import com.sakurapuare.flightmanagement.pojo.entity.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testFindUserByUsername() {
        String username = "admin";
        User user = userMapper.findUserByUsername(username);
        assertNotNull(user);
        assertEquals(username, user.getUsername());
    }

    @Test
    void testFindUserByUsernameAndPassword() {
        String username = "admin";
        String password = "admin";
        User user = userMapper.findUserByUsernameAndPassword(username, password);
        assertNotNull(user);
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
    }

    @Test
    void testFindUserListByUsernameAndPassword() {
        String username = "admin";
        String password = "admin";
        List<User> userList = userMapper.findUserListByUsernameAndPassword(username, password);
        assertNotNull(userList);
        assertFalse(userList.isEmpty());
        for (User user : userList) {
            assertEquals(username, user.getUsername());
            assertEquals(password, user.getPassword());
        }
    }
}