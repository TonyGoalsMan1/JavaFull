package tech.reliab.course.nikiforovda.bank.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.reliab.course.nikiforovda.bank.containers.PostgresContainer;
import tech.reliab.course.nikiforovda.bank.entity.Bank;
import tech.reliab.course.nikiforovda.bank.repository.BankRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BankRepositoryTests extends PostgresContainer {

    @Autowired
    private BankRepository bankRepository;

    private Bank testBank;

    @BeforeEach
    void setUp() {
        testBank = new Bank("Test Bank");
        testBank.setRating(5);
        testBank.setTotalMoney(1000000);
        testBank.setInterestRate(3.5);
        testBank = bankRepository.save(testBank);
    }

    @AfterEach
    void tearDown() {
        bankRepository.deleteAll();
    }

    @Test
    void testFindBankById() {
        Bank foundBank = bankRepository.findById(testBank.getId()).orElse(null);
        assertNotNull(foundBank);
        assertEquals(testBank.getName(), foundBank.getName());
    }

    @Test
    void testDeleteBank() {
        bankRepository.deleteById(testBank.getId());

        boolean exists = bankRepository.existsById(testBank.getId());
        assertFalse(exists);
    }
}
