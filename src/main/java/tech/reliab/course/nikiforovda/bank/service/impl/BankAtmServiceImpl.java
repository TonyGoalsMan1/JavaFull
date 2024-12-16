package tech.reliab.course.nikiforovda.bank.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.reliab.course.nikiforovda.bank.entity.Bank;
import tech.reliab.course.nikiforovda.bank.entity.BankAtm;
import tech.reliab.course.nikiforovda.bank.entity.BankAtmStatus;
import tech.reliab.course.nikiforovda.bank.model.BankAtmRequest;
import tech.reliab.course.nikiforovda.bank.repository.BankAtmRepository;
import tech.reliab.course.nikiforovda.bank.service.BankAtmService;
import tech.reliab.course.nikiforovda.bank.service.BankOfficeService;
import tech.reliab.course.nikiforovda.bank.service.BankService;
import tech.reliab.course.nikiforovda.bank.service.EmployeeService;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class BankAtmServiceImpl implements BankAtmService {

    private final BankAtmRepository bankAtmRepository;
    private final BankService bankService;
    private final BankOfficeService bankOfficeService;
    private final EmployeeService employeeService;

    @Override
    public BankAtm createBankAtm(BankAtmRequest bankAtmRequest) {
        Bank bank = bankService.getBankById(bankAtmRequest.getBankId());
        BankAtm bankAtm = new BankAtm(
                bankAtmRequest.getAtmCode(), // Новый уникальный код банкомата
                bankAtmRequest.getName(),
                bankAtmRequest.getAddress(),
                bank,
                bankOfficeService.getBankOfficeById(bankAtmRequest.getLocationId()),
                employeeService.getEmployeeById(bankAtmRequest.getEmployeeId()),
                bankAtmRequest.isCashWithdrawal(),
                bankAtmRequest.isCashDeposit(),
                bankAtmRequest.getMaintenanceCost(),
                LocalDate.now()
        );
        bankAtm.setStatus(BankAtmStatus.randomStatus());
        bankAtm.setAtmMoney(generateAtmMoney(bank));
        return bankAtmRepository.save(bankAtm);
    }

    private double generateAtmMoney(Bank bank) {
        return new Random().nextDouble(bank.getTotalMoney());
    }

    @Override
    public BankAtm getBankAtmById(int id) {
        return bankAtmRepository.findById(id).orElseThrow(() -> new NoSuchElementException("BankAtm was not found"));
    }

    @Override
    public List<BankAtm> getAllBankAtms() {
        return bankAtmRepository.findAll();
    }

    @Override
    public List<BankAtm> getAllBankAtmsByBankId(int bankId) {
        return bankAtmRepository.findAllByBankId(bankId);
    }

    @Override
    public BankAtm updateBankAtm(int id, String name) {
        BankAtm bankAtm = getBankAtmById(id);
        bankAtm.setName(name);
        return bankAtmRepository.save(bankAtm);
    }

    @Override
    public void deleteBankAtm(int id) {
        bankAtmRepository.deleteById(id);
    }

    /**
     * Поиск банкомата по уникальному коду
     */
    public BankAtm getBankAtmByAtmCode(String atmCode) {
        return bankAtmRepository.findByAtmCode(atmCode)
                .orElseThrow(() -> new NoSuchElementException("BankAtm with code " + atmCode + " not found"));
    }

    /**
     * Увеличение количества транзакций банкомата
     */
    @Transactional
    public void incrementTransactionCount(int id) {
        bankAtmRepository.incrementTransactionCount(id);
    }
}
