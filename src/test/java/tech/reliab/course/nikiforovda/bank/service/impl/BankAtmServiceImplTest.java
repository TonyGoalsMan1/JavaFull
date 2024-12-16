package tech.reliab.course.nikiforovda.bank.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tech.reliab.course.nikiforovda.bank.entity.Bank;
import tech.reliab.course.nikiforovda.bank.entity.BankAtm;
import tech.reliab.course.nikiforovda.bank.model.BankAtmRequest;
import tech.reliab.course.nikiforovda.bank.repository.BankAtmRepository;
import tech.reliab.course.nikiforovda.bank.service.BankOfficeService;
import tech.reliab.course.nikiforovda.bank.service.BankService;
import tech.reliab.course.nikiforovda.bank.service.EmployeeService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BankAtmServiceImplTest {

    @Mock
    private BankAtmRepository bankAtmRepository;

    @Mock
    private BankService bankService;

    @Mock
    private BankOfficeService bankOfficeService;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private BankAtmServiceImpl bankAtmService;

    private BankAtm bankAtm;
    private BankAtmRequest bankAtmRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        bankAtm = new BankAtm();
        bankAtm.setId(1);
        bankAtm.setAtmCode("ATM-12345");
        bankAtm.setName("Test ATM");

        bankAtmRequest = new BankAtmRequest(
                "ATM-12345",
                "Test ATM",
                "123 Main Street",
                1, 2, 3, true, true, 150.50
        );

        when(bankAtmRepository.findByAtmCode("ATM-12345")).thenReturn(Optional.of(bankAtm));
        when(bankAtmRepository.save(any(BankAtm.class))).thenReturn(bankAtm);
    }

    @Test
    void testGetBankAtmByAtmCode() {
        BankAtm foundAtm = bankAtmService.getBankAtmByAtmCode("ATM-12345");

        assertNotNull(foundAtm);
        assertEquals("ATM-12345", foundAtm.getAtmCode());
        assertEquals("Test ATM", foundAtm.getName());
    }

    @Test
    void testIncrementTransactionCount() {
        when(bankAtmRepository.findById(1)).thenReturn(Optional.of(bankAtm));
        doNothing().when(bankAtmRepository).incrementTransactionCount(1);

        bankAtmService.incrementTransactionCount(1);

        verify(bankAtmRepository, times(1)).incrementTransactionCount(1);
    }

    @Test
    void testCreateBankAtm() {
        BankAtm createdAtm = bankAtmService.createBankAtm(bankAtmRequest);

        assertNotNull(createdAtm);
        assertEquals("ATM-12345", createdAtm.getAtmCode());
        assertEquals("Test ATM", createdAtm.getName());
        verify(bankAtmRepository, times(1)).save(any(BankAtm.class));
    }
}
