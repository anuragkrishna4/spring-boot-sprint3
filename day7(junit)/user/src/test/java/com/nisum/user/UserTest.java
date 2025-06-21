package com.nisum.user;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    void testUserFields() {
        User user = new User("Anurag", "anuragkrishna@gmail.com", 20);

        assertAll("User Properties",
                () -> assertEquals("Anurag", user.getName()),
                () -> assertEquals("anuragkrishna@gmail.com", user.getEmail()),
                () -> assertEquals(20, user.getAge())
        );

        assertNotNull(user.getEmail());
        assertTrue(user.getAge() >= 18);
    }
}
