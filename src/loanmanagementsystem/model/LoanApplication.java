package loanmanagementsystem.model;

import java.time.LocalDate;
public class LoanApplication {
    private String fullName;
    private LocalDate birthDate;
    private double loanAmount;
    private int loanMonths;
    private String bank;

    public LoanApplication(String fullName, LocalDate birthDate, double loanAmount, int loanMonths, String bank) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.loanAmount = loanAmount;
        this.loanMonths = loanMonths;
        this.bank = bank;
    }
}