package Model;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PersonalAccount extends Account {

    private static final String basePath = Paths.get("").toAbsolutePath() + "/TradingFinalProject/src/Database/DBFiles/";

    public static String filePath = basePath + "CustomerPersonalAccounts.txt/" + id + "_Account.txt";

    // Read from the database
    public PersonalAccount(String id, String type, double balance, String ownerID) {
        super(id, type, balance, ownerID);
    }

//    // Construct new Personal Account
//    public PersonalAccount(int id){
//        super();
//        this.id = id;
//        this.balance = 0;
//    }


//    public static String generateCurrentId() {
//        return "A" + getCurrentID();
//    }

    public void deposit(double amount) {
        this.balance += amount;
    }


    public static void updateCustomerFile(int id, double newMoney) {

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

            try {
                FileWriter fw = new FileWriter(filePath, true);
                fw.write(data);
                fw.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred while writing request to DBfile.");
                e.printStackTrace();
            }
            updateCustomerFile(Integer.parseInt(id), balance);
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



    public static void main(String[] args) {
        updateCustomerFile(1, 2500);
//        System.out.println(basePath);
    }
}
