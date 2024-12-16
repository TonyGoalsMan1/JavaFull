package tech.reliab.course.nikiforovda.bank.service;

import tech.reliab.course.nikiforovda.bank.entity.BankAtm;
import tech.reliab.course.nikiforovda.bank.model.BankAtmRequest;

import java.util.List;

public interface BankAtmService {
    BankAtm createBankAtm(BankAtmRequest bankAtmRequest);

    BankAtm getBankAtmById(int id);

    List<BankAtm> getAllBankAtms();

    List<BankAtm> getAllBankAtmsByBankId(int bankId);

    BankAtm updateBankAtm(int id, String name);

    void deleteBankAtm(int id);

    BankAtm getBankAtmByAtmCode(String atmCode);

    void incrementTransactionCount(int id);
}
