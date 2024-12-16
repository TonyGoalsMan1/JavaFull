package tech.reliab.course.nikiforovda.bank.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.reliab.course.nikiforovda.bank.containers.PostgresContainer;
import tech.reliab.course.nikiforovda.bank.entity.Bank;
import tech.reliab.course.nikiforovda.bank.entity.Employee;
import tech.reliab.course.nikiforovda.bank.repository.BankRepository;
import tech.reliab.course.nikiforovda.bank.repository.EmployeeRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EmployeeRepositoryTests extends PostgresContainer {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BankRepository bankRepository;

    private Bank testBank;
    private Employee testEmployee;

    @BeforeEach
    void setUp() {
        testBank = new Bank("Test Bank");
        testBank.setRating(5);
        testBank.setTotalMoney(1000000);
        testBank.setInterestRate(4.5);
        testBank = bankRepository.save(testBank);

        testEmployee = new Employee(
                "John Doe",
                LocalDate.of(1990, 1, 1),
                "Manager",
                testBank,
                true,
                null,
                true,
                50000
        );
        testEmployee = employeeRepository.save(testEmployee);
    }

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
        bankRepository.deleteAll();
    }

    @Test
    void testFindEmployeeById() {
        Employee foundEmployee = employeeRepository.findById(testEmployee.getId()).orElse(null);
        assertNotNull(foundEmployee);
        assertEquals(testEmployee.getFullName(), foundEmployee.getFullName());
    }

    @Test
    void testDeleteEmployee() {
        employeeRepository.deleteById(testEmployee.getId());
        boolean exists = employeeRepository.existsById(testEmployee.getId());
        assertFalse(exists);
    }
}