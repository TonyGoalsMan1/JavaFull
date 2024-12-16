package tech.reliab.course.nikiforovda.bank.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.reliab.course.nikiforovda.bank.containers.PostgresContainer;
import tech.reliab.course.nikiforovda.bank.entity.Bank;
import tech.reliab.course.nikiforovda.bank.entity.BankAtm;
import tech.reliab.course.nikiforovda.bank.entity.BankOffice;
import tech.reliab.course.nikiforovda.bank.repository.BankAtmRepository;
import tech.reliab.course.nikiforovda.bank.repository.BankOfficeRepository;
import tech.reliab.course.nikiforovda.bank.repository.BankRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BankAtmRepositoryTests extends PostgresContainer {

    @Autowired
    private BankAtmRepository bankAtmRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private BankOfficeRepository bankOfficeRepository;

    private Bank testBank;
    private BankOffice testOffice;
    private BankAtm testAtm;

    @BeforeEach
    void setUp() {
        testBank = new Bank("Big Bank");
        testBank = bankRepository.save(testBank);

        testOffice = new BankOffice("Main Office", "123 Test Street", true, true, true, true, 5000, testBank);
        testOffice = bankOfficeRepository.save(testOffice);

        testAtm = new BankAtm(
                "A-007",
                "9 Street",
                testBank,
                testOffice,
                null,
                true,
                true,
                200
        );
        testAtm.setAtmCode("ATM-12345");
        testAtm.setTransactionCount(0);
        testAtm = bankAtmRepository.save(testAtm);
    }

    @AfterEach
    void tearDown() {
        bankAtmRepository.deleteAll();
        bankOfficeRepository.deleteAll();
        bankRepository.deleteAll();
    }

    @Test
    void testFindByAtmCode() {
        BankAtm foundAtm = bankAtmRepository.findByAtmCode("ATM-12345").orElse(null);

        assertNotNull(foundAtm);
        assertEquals("ATM-12345", foundAtm.getAtmCode());
        assertEquals("456 ATM Street", foundAtm.getAddress());
    }

    @Test
    void testIncrementTransactionCount() {
        bankAtmRepository.incrementTransactionCount(testAtm.getId());

        BankAtm updatedAtm = bankAtmRepository.findById(testAtm.getId()).orElse(null);
        assertNotNull(updatedAtm);
        assertEquals(1, updatedAtm.getTransactionCount());
    }
}
