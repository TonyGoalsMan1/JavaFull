package tech.reliab.course.nikiforovda.bank.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tech.reliab.course.nikiforovda.bank.entity.Bank;
import tech.reliab.course.nikiforovda.bank.service.BankService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BankControllerImpl.class)
class BankControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankService bankService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createBankWithDetails_ShouldReturnCreatedBank() throws Exception {
        Bank bank = new Bank(UUID.randomUUID(), "B001", "Test Bank", "Region1", LocalDate.now(), 85, 1000000.0, 10.0);

        when(bankService.createBank(anyString(), anyString(), anyString(), any(LocalDate.class))).thenReturn(bank);

        mockMvc.perform(post("/api/v1/banks/full")
                        .param("bankCode", "B001")
                        .param("name", "Test Bank")
                        .param("region", "Region1")
                        .param("foundationDate", LocalDate.now().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test Bank"))
                .andExpect(jsonPath("$.bankCode").value("B001"))
                .andExpect(jsonPath("$.region").value("Region1"));

        verify(bankService, times(1)).createBank(anyString(), anyString(), anyString(), any(LocalDate.class));
    }

    @Test
    void getBankById_ShouldReturnBank() throws Exception {
        UUID id = UUID.randomUUID();
        Bank bank = new Bank(id, "B001", "Test Bank", "Region1", LocalDate.now(), 85, 1000000.0, 10.0);

        when(bankService.getBankById(id)).thenReturn(bank);

        mockMvc.perform(get("/api/v1/banks/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.name").value("Test Bank"));

        verify(bankService, times(1)).getBankById(id);
    }

    @Test
    void getAllBanks_ShouldReturnListOfBanks() throws Exception {
        Bank bank = new Bank(UUID.randomUUID(), "B001", "Test Bank", "Region1", LocalDate.now(), 85, 1000000.0, 10.0);

        when(bankService.getAllBanks()).thenReturn(Collections.singletonList(bank));

        mockMvc.perform(get("/api/v1/banks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Bank"));

        verify(bankService, times(1)).getAllBanks();
    }
}
