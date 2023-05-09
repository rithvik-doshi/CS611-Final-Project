package Model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PersonalAccount extends Account {

    private final int id;
    private double balance;
    private static final String customerPath = Paths.get("").toAbsolutePath() +"/TradingFinalProject/src/Database/DBFiles/Customer.txt";

    PersonalTransactionHistory personalTransactionHistory;

    // Customer Constructor for Read from the database
    public PersonalAccount(int id, double balance) {
        super(balance, String.valueOf(id));
        this.id = id;
        this.balance = balance;
//        System.out.println("this is balance:"+balance);
        this.personalTransactionHistory = new PersonalTransactionHistory(String.valueOf(id), "Personal");
    }

    public void deposit(double amount) {
        balance = Math.ceil(balance * 100) / 100.0;
        balance += amount;
        updateCustomerFile(customerPath,id,balance);
//        System.out.println("New balance:"+balance);
        PersonalTransaction t = new PersonalTransaction("Deposit", amount);
        personalTransactionHistory.addToHistory(t);
    }


    public static void updateCustomerFile(String filePath, int id, double newMoney) {

//        System.out.println("newMoney: " + newMoney);
        try {
            List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8));
            int lineNumber = -1;

            for (int i = 1; i < fileContent.size(); i++) {
                String[] fields = fileContent.get(i).split(", ");
                if (Integer.parseInt(fields[0]) == id) {
                    lineNumber = i;
                    break;
                }
            }

            if (lineNumber != -1) {
                String[] fields = fileContent.get(lineNumber).split(", ");
                fields[4] = String.valueOf(newMoney);
//                System.out.println("fields[4]"+fields[4]);
                fileContent.set(lineNumber, String.join(", ", fields));
                Files.write(Paths.get(filePath), fileContent, StandardCharsets.UTF_8);
//                System.out.println("Money updated successfully");
            } else {
//                System.out.println("Customer not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean withdraw(double amount) {
//        System.out.println("bruh" + Math.ceil(balance * 100) / 100.0);
        if ((balance = Math.ceil(balance * 100) / 100.0) >= amount) {
            balance -= amount;
            updateCustomerFile(customerPath,id,balance);
            PersonalTransaction t = new PersonalTransaction("Withdraw", amount);
            personalTransactionHistory.addToHistory(t);
            return true;
        } else {
            return false;
        }
    }

    public String getHistory() {
        return personalTransactionHistory.toString();
    }

    public double getBalance() {
        return balance;
    }
}