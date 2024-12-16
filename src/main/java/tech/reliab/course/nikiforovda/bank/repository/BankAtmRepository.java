package tech.reliab.course.nikiforovda.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import tech.reliab.course.nikiforovda.bank.entity.BankAtm;

import java.util.List;
import java.util.Optional;

public interface BankAtmRepository extends JpaRepository<BankAtm, Integer> {

    // Найти все банкоматы по ID банка
    List<BankAtm> findAllByBankId(int id);

    // Найти банкомат по уникальному коду
    Optional<BankAtm> findByAtmCode(String atmCode);

    // Удалить банкомат по ID
    void deleteById(int id);

    // Обновить количество транзакций у банкомата
    @Transactional
    @Modifying
    @Query("UPDATE BankAtm b SET b.transactionCount = b.transactionCount + 1 WHERE b.id = :id")
    void incrementTransactionCount(int id);
}
