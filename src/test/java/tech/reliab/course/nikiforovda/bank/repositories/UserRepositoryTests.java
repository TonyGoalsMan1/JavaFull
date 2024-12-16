package tech.reliab.course.nikiforovda.bank.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.reliab.course.nikiforovda.bank.containers.PostgresContainer;
import tech.reliab.course.nikiforovda.bank.entity.User;
import tech.reliab.course.nikiforovda.bank.repository.UserRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserRepositoryTests extends PostgresContainer {

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User("Alice Johnson", LocalDate.of(1985, 7, 20), "Doctor");
        testUser.setMonthlyIncome(7000);
        testUser.setCreditRating(800);
        testUser = userRepository.save(testUser);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void testFindUserById() {
        User foundUser = userRepository.findById(testUser.getId()).orElse(null);
        assertNotNull(foundUser);
        assertEquals(testUser.getFullName(), foundUser.getFullName());
    }

    @Test
    void testDeleteUser() {
        userRepository.deleteById(testUser.getId());
        boolean exists = userRepository.existsById(testUser.getId());
        assertFalse(exists);
    }
}
