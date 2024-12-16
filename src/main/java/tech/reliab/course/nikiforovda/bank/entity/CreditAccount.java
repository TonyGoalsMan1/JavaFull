package tech.reliab.course.nikiforovda.bank.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "credit_accounts")
public class CreditAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Bank bank;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private int loanTermMonths;

    @Column(nullable = false)
    private double loanAmount;

    @Column(nullable = false)
    private double monthlyPayment;

    @Column(nullable = false)
    private double interestRate;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private PaymentAccount paymentAccount;

    public CreditAccount(User user, Bank bank, LocalDate startDate, int loanTermMonths, double interestRate, Employee employee, PaymentAccount paymentAccount) {
        this.user = user;
        this.bank = bank;
        this.startDate = startDate;
        this.loanTermMonths = loanTermMonths;
        this.endDate = startDate.plusMonths(loanTermMonths);
        this.interestRate = interestRate;
        this.employee = employee;
        this.paymentAccount = paymentAccount;
    }

    @Override
    public String toString() {
        return "CreditAccount{" +
                "id=" + id +
                ", user=" + user.getFullName() +
                ", bank=" + bank.getName() +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", loanTermMonths=" + loanTermMonths +
                ", loanAmount=" + loanAmount +
                ", monthlyPayment=" + monthlyPayment +
                ", interestRate=" + interestRate +
                ", employee=" + (employee != null ? employee.getFullName() : "None") +
                ", paymentAccountId=" + (paymentAccount != null ? paymentAccount.getId() : "None") +
                '}';
    }
}

