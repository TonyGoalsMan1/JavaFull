package tech.reliab.course.nikiforovda.bank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAtmRequest {
    private String atmCode;
    private String name;
    private String address;
    private int bankId;
    private int locationId;
    private int employeeId;
    private boolean cashWithdrawal;
    private boolean cashDeposit;
    private double maintenanceCost;
}