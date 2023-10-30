package atmapp;

import java.util.Scanner;

public class ATM {
    // DB
    static String[] cardNumbers = {"9916578510987985", "9876543210987654"};
    static String[] pinCodes = {"8787", "8719"};
    static String[] userNames = {"Kamran Babayev", "Orxan Rahimov"};
    static Double[] balances = {5000.00, 17000.00};
    static Boolean[] cardBlocked = {false, false};
    static double usdToAznRate = 1.7;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your 16-digit card number: ");
        String cardNumber = scanner.nextLine();
        System.out.print("Enter your 4-digit PIN: ");
        String pin = scanner.nextLine();

        int userIndex = authenticateUser(cardNumber, pin);

        if (userIndex != -1) {
            String userName = userNames[userIndex];
            System.out.println("Welcome, " + userName);

            int option;
            do {
                System.out.println("Options:");
                System.out.println("1. Card to Card");
                System.out.println("2. Show Balance");
                System.out.println("3. Cash by Code");
                System.out.println("4. Exchange");

                System.out.print("Enter your choice (1-4): ");
                option = scanner.nextInt();

                if (option == 1) {
                    double amount = 100.00;
                    boolean success = cardToCardTransfer(userIndex, amount);
                    if (success) {
                        System.out.println("Transfer successful. Your balance: " + balances[userIndex]);
                    } else {
                        System.out.println("Transfer failed. Check your balance or card status.");
                    }
                } else if (option == 2) {
                    double balance = getBalance(userIndex);
                    System.out.println("Your balance: " + balance);
                } else if (option == 3) {
                    System.out.println("1. Generate Cash by Code");
                    System.out.println("2. Get Amount by Code");
                    int cashOption = scanner.nextInt();
                    if (cashOption == 1) {
                        String code = generateCashCode(userIndex, 100.00);
                        System.out.println("Cash code: " + code);
                    } else if (cashOption == 2) {
                        System.out.print("Enter the code: ");
                        String code = scanner.next();
                        double codeAmount = getCodeAmount(userIndex, code);
                        if (codeAmount >= 0) {
                            System.out.println("Code redeemed successfully. Your balance: " + balances[userIndex]);
                        } else {
                            System.out.println("Invalid code.");
                        }
                    }
                } else if (option == 4) {
                    System.out.print("Enter the amount in USD to exchange: ");
                    double usdAmount = scanner.nextDouble();
                    boolean exchangeSuccess = exchangeCurrency(userIndex, usdAmount);
                    if (exchangeSuccess) {
                        System.out.println("Exchange successful. Your balance: " + balances[userIndex]);
                    } else {
                        System.out.println("Exchange failed. Check your balance or card status.");
                    }
                } else {
                    System.out.println("Invalid option. Please select 1-4.");
                }
            } while (option != 0);
        } else {
            System.out.println("Authentication failed. Please check your card number and PIN.");
        }
    }



    static int authenticateUser(String cardNumber, String pin) {
        for (int i = 0; i < cardNumbers.length; i++) {
            if (cardNumbers[i].equals(cardNumber) && pinCodes[i].equals(pin) && !cardBlocked[i]) {
                return i;
            }
        }
        return -1;
    }

    static double getBalance(int userIndex) {
        return balances[userIndex];
    }

    static boolean cardToCardTransfer(int userIndex, double amount) {
        if (amount <= 0 || amount > balances[userIndex]) {
            return false;
        }
        balances[userIndex] -= amount;
        return true;
    }

    static String generateCashCode(int userIndex, double amount) {
        String code = "1234";
        balances[userIndex] -= amount;
        return code;
    }

    static double getCodeAmount(int userIndex, String code) {
        if (code.equals("1234")) {
            double amount = 100.00;
            balances[userIndex] += amount;
            return amount;
        }
        return -1;
    }

    static boolean exchangeCurrency(int userIndex, double usdAmount) {
        if (usdAmount <= 0 || usdAmount > balances[userIndex]) {
            return false;
        }
        double aznAmount = usdAmount * usdToAznRate;
        if (aznAmount <= balances[userIndex]) {
            balances[userIndex] -= usdAmount;
            balances[userIndex] -= (usdAmount * 0.01);
            return true;
        }
        return false;
    }
}
