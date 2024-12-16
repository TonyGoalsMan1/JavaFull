package tech.reliab.course.nikiforovda.bank.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String position;

    @ManyToOne
    private Bank bank;

    @Column(nullable = false)
    private boolean remoteWork;

    @ManyToOne
    private BankOffice bankOffice;

    @Column(nullable = false)
    private boolean canIssueLoans;

    @Column(nullable = false)
    private double salary;

    @OneToMany(mappedBy = "employee")
    private List<BankAtm> bankAtm;

    public Employee(String fullName, LocalDate birthDate, String position, Bank bank, boolean remoteWork, BankOffice bankOffice, boolean canIssueLoans, double salary) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.position = position;
        this.bank = bank;
        this.remoteWork = remoteWork;
        this.bankOffice = bankOffice;
        this.canIssueLoans = canIssueLoans;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", birthDate=" + birthDate +
                ", position='" + position + '\'' +
                ", bank=" + bank.getName() +
                ", remoteWork=" + remoteWork +
                ", bankOffice=" + (bankOffice != null ? bankOffice.getName() : "None") +
                ", canIssueLoans=" + canIssueLoans +
                ", salary=" + salary +
                '}';
    }
}