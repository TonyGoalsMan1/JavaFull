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

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment_accounts")
public class PaymentAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Bank bank;

    @Column(nullable = false)
    private double balance;

    public PaymentAccount(User user, Bank bank) {
        this.user = user;
        this.bank = bank;
        this.balance = 0;
    }

    @Override
    public String toString() {
        return "PaymentAccount{" +
                "id=" + id +
                ", user=" + user.getFullName() +
                ", bank=" + bank.getName() +
                ", balance=" + balance +
                '}';
    }
}
