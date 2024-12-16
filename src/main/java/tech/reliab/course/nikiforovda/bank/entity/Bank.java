package tech.reliab.course.nikiforovda.bank.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "banks")
@ToString
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id; // Уникальный идентификатор

    @Column(nullable = false, unique = true)
    private String bankCode; // Уникальный код банка

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int rating;

    @Column(nullable = false)
    private double totalMoney;

    @Column(nullable = false)
    private double interestRate;

    @Column(nullable = false)
    private String region; // Регион банка

    @Column(nullable = false)
    private LocalDate foundationDate; // Дата основания банка

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private List<BankOffice> offices;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private List<BankAtm> atms;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private List<Employee> employees;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private List<CreditAccount> creditAccounts;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private List<PaymentAccount> paymentAccounts;

    public Bank(String bankCode, String name, String region, LocalDate foundationDate) {
        this.bankCode = bankCode;
        this.name = name;
        this.region = region;
        this.foundationDate = foundationDate;
    }
}
