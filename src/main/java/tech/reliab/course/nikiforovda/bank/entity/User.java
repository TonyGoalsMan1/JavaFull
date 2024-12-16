package tech.reliab.course.nikiforovda.bank.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String job;

    @Column(nullable = false)
    private double monthlyIncome;

    @Column(nullable = false)
    private int creditRating;

    @ManyToMany
    @JoinTable(
            name = "user_banks",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "bank_id")
    )
    private List<Bank> banks;

    @OneToMany(mappedBy = "user")
    private List<CreditAccount> creditAccounts;

    @OneToMany(mappedBy = "user")
    private List<PaymentAccount> paymentAccounts;

    public User(String fullName, LocalDate birthDate, String job) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.job = job;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", birthDate=" + birthDate +
                ", job='" + job + '\'' +
                ", monthlyIncome=" + monthlyIncome +
                ", creditRating=" + creditRating +
                ", banks=" + banks.stream().map(Bank::getName).toList() +
                ", creditAccounts=" + creditAccounts.size() +
                ", paymentAccounts=" + paymentAccounts.size() +
                '}';
    }
}
