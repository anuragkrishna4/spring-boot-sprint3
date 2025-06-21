package com.nisum.user;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @Test
    void testUnderageThrowsException() {
        UserService service = new UserService();
        Exception ex = assertThrows(IllegalArgumentException.class, () -> service.validateAge(16));
        assertEquals("Underage", ex.getMessage());
    }
}
