package tech.reliab.course.nikiforovda.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.reliab.course.nikiforovda.bank.entity.BankOffice;

public interface BankOfficeRepository extends JpaRepository<BankOffice, Integer> {

    void deleteById(int id);
}