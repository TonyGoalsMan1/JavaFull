package tech.reliab.course.nikiforovda.bank.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.reliab.course.nikiforovda.bank.containers.PostgresContainer;
import tech.reliab.course.nikiforovda.bank.entity.Bank;
import tech.reliab.course.nikiforovda.bank.entity.PaymentAccount;
import tech.reliab.course.nikiforovda.bank.entity.User;
import tech.reliab.course.nikiforovda.bank.repository.BankRepository;
import tech.reliab.course.nikiforovda.bank.repository.PaymentAccountRepository;
import tech.reliab.course.nikiforovda.bank.repository.UserRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PaymentAccountRepositoryTests extends PostgresContainer {

    @Autowired
    private PaymentAccountRepository paymentAccountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BankRepository bankRepository;

    private PaymentAccount testPaymentAccount;
    private User testUser;
    private Bank testBank;

    @BeforeEach
    void setUp() {
        testBank = new Bank("Test Bank");
        testBank.setRating(5);
        testBank.setTotalMoney(1000000);
        testBank.setInterestRate(4.5);
        testBank = bankRepository.save(testBank);

        testUser = new User("Jane Doe", LocalDate.of(1995, 3, 15), "Engineer");
        testUser.setMonthlyIncome(3000);
        testUser.setCreditRating(700);
        testUser = userRepository.save(testUser);

        testPaymentAccount = new PaymentAccount(testUser, testBank);
        testPaymentAccount.setBalance(1000);
        testPaymentAccount = paymentAccountRepository.save(testPaymentAccount);
    }

    @AfterEach
    void tearDown() {
        paymentAccountRepository.deleteAll();
        userRepository.deleteAll();
        bankRepository.deleteAll();
    }

    @Test
    void testFindPaymentAccountById() {
        PaymentAccount foundAccount = paymentAccountRepository.findById(testPaymentAccount.getId()).orElse(null);
        assertNotNull(foundAccount);
        assertEquals(testPaymentAccount.getBalance(), foundAccount.getBalance());
    }

    @Test
    void testDeletePaymentAccount() {
        paymentAccountRepository.deleteById(testPaymentAccount.getId());
        boolean exists = paymentAccountRepository.existsById(testPaymentAccount.getId());
        assertFalse(exists);
    }
}
