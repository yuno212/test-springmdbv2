package com.example.springmongodb;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = Application.class)
public class AppTest {

    @Test
    void contextLoads() {
        // A simple test to check if the Spring context loads
        assertTrue(true);
    }
}