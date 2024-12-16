package tech.reliab.course.nikiforovda.bank.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.reliab.course.nikiforovda.bank.containers.PostgresContainer;
import tech.reliab.course.nikiforovda.bank.entity.Bank;
import tech.reliab.course.nikiforovda.bank.entity.BankOffice;
import tech.reliab.course.nikiforovda.bank.repository.BankOfficeRepository;
import tech.reliab.course.nikiforovda.bank.repository.BankRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BankOfficeRepositoryTests extends PostgresContainer {

    @Autowired
    private BankOfficeRepository bankOfficeRepository;

    @Autowired
    private BankRepository bankRepository;

    private Bank testBank;
    private BankOffice testOffice;

    @BeforeEach
    void setUp() {
        testBank = new Bank("Test Bank");
        testBank = bankRepository.save(testBank);

        testOffice = new BankOffice("Main Office", "123 Test Street", true, true, true, true, 5000, testBank);
        testOffice = bankOfficeRepository.save(testOffice);
    }

    @AfterEach
    void tearDown() {
        bankOfficeRepository.deleteAll();
        bankRepository.deleteAll();
    }

    @Test
    void testFindBankOffice() {
        BankOffice office = bankOfficeRepository.findById(testOffice.getId()).orElse(null);

        assertNotNull(office);
        assertEquals("Main Office", office.getName());
    }

    @Test
    void testDeleteBankOffice() {
        bankOfficeRepository.deleteById(testOffice.getId());

        boolean exists = bankOfficeRepository.existsById(testOffice.getId());
        assertFalse(exists);
    }
}
