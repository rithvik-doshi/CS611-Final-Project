package Model;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PersonalAccount extends Account {

    private int id;
    private double balance;
    private String filePath;
    private String currentPath;
    private static  String customerPath = Paths.get("").toAbsolutePath().toString() +"/TradingFinalProject/src/Database/DBFiles/Customer.txt";

    PersonalTransactionHistory personalTransactionHistory;

    // Customer Constructor for Read from the database
    public PersonalAccount(int id, double balance) {
        super(balance, String.valueOf(id));
        this.id = id;
        this.balance = balance;
        System.out.println("this is balance:"+balance);
        String currentPath = Paths.get("").toAbsolutePath().toString();
        currentPath = currentPath + "/TradingFinalProject/src/Database/DBFiles/";
        this.filePath = currentPath+ "CustomerPersonalHistory/"+ this.id +"_PersonalHistory.txt";
        this.personalTransactionHistory = new PersonalTransactionHistory(String.valueOf(id), "Personal");
    }

    public void deposit(double amount) {
        balance += amount;
        String data = "Deposit" + ", " + amount+"\n";
        updateCustomerFile(customerPath,id,balance);
        System.out.println("New balance:"+balance);
        PersonalTransaction t = new PersonalTransaction("Deposit", amount);
        personalTransactionHistory.addToHistory(t);
    }


    public static void updateCustomerFile(String filePath, int id, double newMoney) {

        System.out.println("newMoney: " + newMoney);
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
                System.out.println("fields[4]"+fields[4]);
                fileContent.set(lineNumber, String.join(", ", fields));
                Files.write(Paths.get(filePath), fileContent, StandardCharsets.UTF_8);
                System.out.println("Money updated successfully");
            } else {
                System.out.println("Customer not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;

            String data = "Withdraw" + ", " + amount+"\n";
            updateCustomerFile(customerPath,id,balance);
            PersonalTransaction t = new PersonalTransaction("Withdraw", amount);
            personalTransactionHistory.addToHistory(t);
            return true;
        } else {
            return false;
        }
    }

    public boolean transferMoney(double amount, TradingAccount account) {
        if (this.withdraw(amount)) {
            account.saveMoney(amount);
            return true;
        } else {
            return false;
        }
    }

    public String getHistory() {
        return personalTransactionHistory.toString();
    }

    public static void main(String[] args) {
        updateCustomerFile(customerPath, 1, 2500);
        System.out.println(customerPath);
    }
}