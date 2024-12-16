package tech.reliab.course.nikiforovda.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.reliab.course.nikiforovda.bank.entity.PaymentAccount;

public interface PaymentAccountRepository extends JpaRepository<PaymentAccount, Integer> {

    void deleteById(int id);
}
