package tech.reliab.course.nikiforovda.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.reliab.course.nikiforovda.bank.entity.Bank;

import java.util.Optional;
import java.util.UUID;

public interface BankRepository extends JpaRepository<Bank, UUID> {


    void deleteById(UUID id);


    Optional<Bank> findByBankCode(String bankCode);
}
