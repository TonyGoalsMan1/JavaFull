package tech.reliab.course.nikiforovda.bank.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.reliab.course.nikiforovda.bank.entity.PaymentAccount;
import tech.reliab.course.nikiforovda.bank.model.PaymentAccountRequest;
import tech.reliab.course.nikiforovda.bank.service.PaymentAccountService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment-accounts")
public class PaymentAccountImpl {

    private final PaymentAccountService paymentAccountService;

    @PostMapping
    public ResponseEntity<PaymentAccount> createPaymentAccount(PaymentAccountRequest paymentAccountRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentAccountService.createPaymentAccount(paymentAccountRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentAccount(int id) {
        paymentAccountService.deletePaymentAccount(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PaymentAccount> updatePaymentAccount(int id, int bankId) {
        return ResponseEntity.ok(paymentAccountService.updatePaymentAccount(id, bankId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentAccount> getBankByPaymentAccount(int id) {
        return ResponseEntity.ok(paymentAccountService.getPaymentAccountDtoById(id));
    }

    @GetMapping
    public ResponseEntity<List<PaymentAccount>> getAllPaymentAccounts() {
        return ResponseEntity.ok(paymentAccountService.getAllPaymentAccounts());
    }
}
