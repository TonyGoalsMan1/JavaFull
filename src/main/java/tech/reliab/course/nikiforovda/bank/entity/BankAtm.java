package tech.reliab.course.nikiforovda.bank.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bank_atms")

public class BankAtm {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(nullable = false, unique = true)
    private String atmCode; // Уникальный код банкомата

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private boolean cashWithdrawal;

    @Column(nullable = false)
    private boolean cashDeposit;

    @Column(nullable = false)
    private double atmMoney;

    @Column(nullable = false)
    private double maintenanceCost;

    @Column(nullable = false)
    private LocalDate installationDate; // Дата установки банкомата

    @Column(nullable = false)
    private int transactionCount; // Количество транзакций

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BankAtmStatus status;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private BankOffice location;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Bank bank;

    public BankAtm(String atmCode, String name, String address, Bank bank, BankOffice location, Employee employee, boolean cashWithdrawal, boolean cashDeposit, double maintenanceCost, LocalDate installationDate) {
        this.atmCode = atmCode;
        this.name = name;
        this.address = address;
        this.status = BankAtmStatus.WORKING;
        this.bank = bank;
        this.location = location;
        this.employee = employee;
        this.cashWithdrawal = cashWithdrawal;
        this.cashDeposit = cashDeposit;
        this.maintenanceCost = maintenanceCost;
        this.installationDate = installationDate;
        this.transactionCount = 0; // Изначально 0 транзакций
    }

    @Override
    public String toString() {
        return "BankAtm{" +
                "id=" + id +
                ", atmCode='" + atmCode + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                ", bank=" + bank.getName() +
                ", location=" + location.getName() +
                ", employee=" + (employee != null ? employee.getFullName() : "None") +
                ", cashWithdrawal=" + cashWithdrawal +
                ", cashDeposit=" + cashDeposit +
                ", atmMoney=" + atmMoney +
                ", maintenanceCost=" + maintenanceCost +
                ", installationDate=" + installationDate +
                ", transactionCount=" + transactionCount +
                '}';
    }
}
