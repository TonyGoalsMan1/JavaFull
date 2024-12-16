package tech.reliab.course.nikiforovda.bank.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import tech.reliab.course.nikiforovda.bank.model.BankAtmRequest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.yaml")
public class BankAtmIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testFullBankAtmLifecycle() throws Exception {
        // 1. Создание банкомата
        BankAtmRequest request = new BankAtmRequest(
                "ATM-12345",
                "New ATM",
                "123 Main Street",
                1, 2, 3, true, true, 150.50
        );

        String response = mockMvc.perform(post("/bank-atms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.atmCode").value("ATM-12345"))
                .andExpect(jsonPath("$.name").value("New ATM"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        // 2. Получение банкомата по коду
        mockMvc.perform(get("/bank-atms/code/{atmCode}", "ATM-12345"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.atmCode").value("ATM-12345"));

        // 3. Увеличение количества транзакций
        mockMvc.perform(patch("/bank-atms/1/increment"))
                .andExpect(status().isOk());

        // 4. Проверка количества транзакций
        mockMvc.perform(get("/bank-atms/code/{atmCode}", "ATM-12345"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionCount").value(1));
    }
}
