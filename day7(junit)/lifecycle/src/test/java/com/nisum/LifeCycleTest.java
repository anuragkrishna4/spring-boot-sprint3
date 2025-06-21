package com.nisum;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LifeCycleTest {

    @BeforeAll
    void beforeAll() {
        System.out.println("Before All");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Before Each");
    }

    @Test
    void testOne() {
        System.out.println("Test One");
    }

    @Test
    void testTwo() {
        System.out.println("Test Two");
    }

    @AfterEach
    void afterEach() {
        System.out.println("After Each");
    }

    @AfterAll
    void afterAll() {
        System.out.println("After All");
    }
}
