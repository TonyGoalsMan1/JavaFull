package tech.reliab.course.nikiforovda.bank.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.reliab.course.nikiforovda.bank.containers.PostgresContainer;
import tech.reliab.course.nikiforovda.bank.entity.Bank;
import tech.reliab.course.nikiforovda.bank.entity.CreditAccount;
import tech.reliab.course.nikiforovda.bank.entity.User;
import tech.reliab.course.nikiforovda.bank.repository.BankRepository;
import tech.reliab.course.nikiforovda.bank.repository.CreditAccountRepository;
import tech.reliab.course.nikiforovda.bank.repository.UserRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreditAccountRepositoryTests extends PostgresContainer {

    @Autowired
    private CreditAccountRepository creditAccountRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private UserRepository userRepository;

    private CreditAccount testCreditAccount;
    private Bank testBank;
    private User testUser;

    @BeforeEach
    void setUp() {
        testBank = new Bank("Test Bank");
        testBank.setRating(5);
        testBank.setTotalMoney(1000000);
        testBank.setInterestRate(3.5);
        testBank = bankRepository.save(testBank);

        testUser = new User("John Doe", LocalDate.of(1990, 1, 1), "Software Engineer");
        testUser.setMonthlyIncome(5000);
        testUser.setCreditRating(750);
        testUser = userRepository.save(testUser);

        testCreditAccount = new CreditAccount(
                testUser,
                testBank,
                LocalDate.of(2024, 1, 1),
                12,
                5.0,
                null,
                null
        );
        testCreditAccount.setLoanAmount(10000);
        testCreditAccount.setMonthlyPayment(850);
        testCreditAccount = creditAccountRepository.save(testCreditAccount);
    }

    @AfterEach
    void tearDown() {
        creditAccountRepository.deleteAll();
        userRepository.deleteAll();
        bankRepository.deleteAll();
    }

    @Test
    void testFindCreditAccountById() {
        CreditAccount foundAccount = creditAccountRepository.findById(testCreditAccount.getId()).orElse(null);
        assertNotNull(foundAccount);
        assertEquals(testCreditAccount.getLoanAmount(), foundAccount.getLoanAmount());
    }

    @Test
    void testDeleteCreditAccount() {
        creditAccountRepository.deleteById(testCreditAccount.getId());

        boolean exists = creditAccountRepository.existsById(testCreditAccount.getId());
        assertFalse(exists);
    }
}
