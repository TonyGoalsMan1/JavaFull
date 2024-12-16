package tech.reliab.course.nikiforovda.bank.service;

import tech.reliab.course.nikiforovda.bank.entity.Bank;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface BankService {
    Bank createBank(String bankName);

    Bank createBank(String bankCode, String name, String region, LocalDate foundationDate);

    Bank getBankById(UUID id);

    Bank getBankByCode(String bankCode);

    List<Bank> getAllBanks();

    Bank updateBank(UUID id, String name);

    void deleteBank(UUID id);
}
