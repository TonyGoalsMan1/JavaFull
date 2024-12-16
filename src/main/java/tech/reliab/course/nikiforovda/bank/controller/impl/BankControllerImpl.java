package tech.reliab.course.nikiforovda.bank.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.reliab.course.nikiforovda.bank.entity.Bank;
import tech.reliab.course.nikiforovda.bank.service.BankService;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/banks")
public class BankControllerImpl {

    private final BankService bankService;

    /**
     * Создание банка с базовым параметром имени
     */
    @PostMapping
    public ResponseEntity<Bank> createBank(@RequestParam String bankName) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bankService.createBank(bankName));
    }

    /**
     * Создание банка с расширенными параметрами
     */
    @PostMapping("/full")
    public ResponseEntity<Bank> createBankWithDetails(@RequestParam String bankCode,
                                                      @RequestParam String name,
                                                      @RequestParam String region,
                                                      @RequestParam LocalDate foundationDate) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bankService.createBank(bankCode, name, region, foundationDate));
    }

    /**
     * Получение банка по ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Bank> getBankById(@PathVariable UUID id) {
        return ResponseEntity.ok(bankService.getBankById(id));
    }

    /**
     * Получение банка по уникальному коду
     */
    @GetMapping("/code/{bankCode}")
    public ResponseEntity<Bank> getBankByCode(@PathVariable String bankCode) {
        return ResponseEntity.ok(bankService.getBankByCode(bankCode));
    }

    /**
     * Получение всех банков
     */
    @GetMapping
    public ResponseEntity<List<Bank>> getAllBanks() {
        return ResponseEntity.ok(bankService.getAllBanks());
    }

    /**
     * Обновление имени банка по ID
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Bank> updateBank(@PathVariable UUID id,
                                           @RequestParam String bankName) {
        return ResponseEntity.ok(bankService.updateBank(id, bankName));
    }

    /**
     * Удаление банка по ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBank(@PathVariable UUID id) {
        bankService.deleteBank(id);
        return ResponseEntity.noContent().build();
    }
}
