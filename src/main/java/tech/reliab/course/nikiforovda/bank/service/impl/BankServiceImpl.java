package tech.reliab.course.nikiforovda.bank.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.reliab.course.nikiforovda.bank.entity.Bank;
import tech.reliab.course.nikiforovda.bank.repository.BankRepository;
import tech.reliab.course.nikiforovda.bank.service.BankService;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;

    @Override
    public Bank createBank(String bankName) {
        Bank bank = new Bank();
        bank.setName(bankName);
        bank.setRating(generateRating());
        bank.setTotalMoney(generateTotalMoney());
        bank.setInterestRate(calculateInterestRate(bank.getRating()));
        return bankRepository.save(bank);
    }

    @Override
    public Bank createBank(String bankCode, String name, String region, LocalDate foundationDate) {
        Bank bank = new Bank();
        bank.setId(UUID.randomUUID());
        bank.setBankCode(bankCode);
        bank.setName(name);
        bank.setRegion(region);
        bank.setFoundationDate(foundationDate);
        bank.setRating(generateRating());
        bank.setTotalMoney(generateTotalMoney());
        bank.setInterestRate(calculateInterestRate(bank.getRating()));
        return bankRepository.save(bank);
    }

    @Override
    public Bank getBankById(UUID id) {
        return bankRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Bank with ID " + id + " not found"));
    }

    @Override
    public Bank getBankByCode(String bankCode) {
        return bankRepository.findByBankCode(bankCode)
                .orElseThrow(() -> new NoSuchElementException("Bank with code " + bankCode + " not found"));
    }

    @Override
    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    @Override
    public Bank updateBank(UUID id, String name) {
        Bank bank = getBankById(id);
        bank.setName(name);
        return bankRepository.save(bank);
    }

    @Override
    public void deleteBank(UUID id) {
        bankRepository.deleteById(id);
    }

    private int generateRating() {
        return (int) (Math.random() * 101);
    }

    private double generateTotalMoney() {
        return Math.random() * 1_000_000;
    }

    private double calculateInterestRate(int rating) {
        return 20.0 - (rating / 10.0);
    }
}
