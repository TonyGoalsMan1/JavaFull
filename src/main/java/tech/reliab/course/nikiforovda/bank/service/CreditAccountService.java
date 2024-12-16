package tech.reliab.course.nikiforovda.bank.service;

import tech.reliab.course.nikiforovda.bank.entity.CreditAccount;
import tech.reliab.course.nikiforovda.bank.model.CreditAccountRequest;

import java.util.List;

public interface CreditAccountService {

    CreditAccount createCreditAccount(CreditAccountRequest creditAccountRequest);

    CreditAccount getCreditAccountById(int id);

    CreditAccount getCreditAccountDtoById(int id);

    List<CreditAccount> getAllCreditAccounts();

    CreditAccount updateCreditAccount(int id, int bankId);

    void deleteCreditAccount(int id);
}
