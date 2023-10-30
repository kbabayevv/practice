package loanmanagementsystem.service;

import loanmanagementsystem.model.LoanApplication;
import loanmanagementsystem.model.LoanRange;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoanManagementService {
    public static void main(String[] args) {
        Map<String, LoanApplication> loanApplications = new HashMap<>();

        Map<String, LoanRange> banks = new HashMap<>();
        banks.put("VTB", new LoanRange(0, 500));
        banks.put("Bank Respublika", new LoanRange(500, 1000));
        banks.put("Express", new LoanRange(500, 1000));
        banks.put("Rabita", new LoanRange(1000, 2000));
        banks.put("Azerturk", new LoanRange(1000, 2000));
        banks.put("Xalq", new LoanRange(1000, 2000));
        banks.put("Kapital", new LoanRange(2000, 3000));
        banks.put("Pasha", new LoanRange(2000, 3000));
        banks.put("Ibar", new LoanRange(2000, 3000));

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your full name: ");
        String fullName = scanner.nextLine();
        System.out.print("Enter your birth date: ");
        LocalDate birthDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter loan amount: ");
        double loanAmount = scanner.nextDouble();
        System.out.print("Enter loan months: ");
        int loanMonths = scanner.nextInt();


        if (loanAmount < 500) {
            System.out.println("Error: Loan amount must be at least 500.");
        } else {
            String selectedBank = null;
            for (Map.Entry<String, LoanRange> entry : banks.entrySet()) {
                LoanRange range = entry.getValue();
                if (loanAmount >= range.getMin() && loanAmount <= range.getMax()) {
                    selectedBank = entry.getKey();
                    break;
                }
            }

            if (selectedBank == null) {
                System.out.println("Error: Loan amount not in the valid range.");
            } else {
                String applicationKey = fullName + birthDate + loanAmount + loanMonths;
                if (loanApplications.containsKey(applicationKey)) {
                    System.out.println("Error: This application is already present.");
                } else {
                    loanApplications.put(applicationKey, new LoanApplication(fullName, birthDate, loanAmount, loanMonths, selectedBank));
                    System.out.println("Your application confirmed.");
                }
            }
        }
    }
}





