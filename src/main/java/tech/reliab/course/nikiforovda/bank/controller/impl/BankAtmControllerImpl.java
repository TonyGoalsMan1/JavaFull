package tech.reliab.course.nikiforovda.bank.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.reliab.course.nikiforovda.bank.entity.BankAtm;
import tech.reliab.course.nikiforovda.bank.model.BankAtmRequest;
import tech.reliab.course.nikiforovda.bank.service.BankAtmService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bank-atms")
public class BankAtmControllerImpl {

    private final BankAtmService bankAtmService;

    @PostMapping
    public ResponseEntity<BankAtm> createBankAtm(@RequestBody BankAtmRequest bankAtmRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bankAtmService.createBankAtm(bankAtmRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBankAtm(@PathVariable int id) {
        bankAtmService.deleteBankAtm(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BankAtm> updateBankAtm(@PathVariable int id, @RequestParam String name) {
        return ResponseEntity.ok(bankAtmService.updateBankAtm(id, name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAtm> getBankAtmById(@PathVariable int id) {
        return ResponseEntity.ok(bankAtmService.getBankAtmById(id));
    }

    @GetMapping("/all-by-bank/{bankId}")
    public ResponseEntity<List<BankAtm>> getAllBankAtmByBankId(@PathVariable int bankId) {
        return ResponseEntity.ok(bankAtmService.getAllBankAtmsByBankId(bankId));
    }

    @GetMapping
    public ResponseEntity<List<BankAtm>> getAllBankAtms() {
        return ResponseEntity.ok(bankAtmService.getAllBankAtms());
    }

    /**
     * Новый эндпоинт: Поиск банкомата по уникальному коду
     */
    @GetMapping("/code/{atmCode}")
    public ResponseEntity<BankAtm> getBankAtmByAtmCode(@PathVariable String atmCode) {
        return ResponseEntity.ok(bankAtmService.getBankAtmByAtmCode(atmCode));
    }

    /**
     * Новый эндпоинт: Увеличение количества транзакций банкомата
     */
    @PatchMapping("/{id}/increment")
    public ResponseEntity<Void> incrementTransactionCount(@PathVariable int id) {
        bankAtmService.incrementTransactionCount(id);
        return ResponseEntity.ok().build();
    }
}
