package Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.BufferedWriter;

public class Customer extends Person {
    private PersonalAccount personalAccount;
    private TradingAccount tradingAccount;
    private double balance;

    public Customer(int ID, String name, String email, String password, double balance) {
        super(ID,name,email,password);
        this.balance = balance;
        createPersonalAccountHistory();
        this.personalAccount = new PersonalAccount(getID(), balance);
    }

    public void saveMoney(double money){
        personalAccount.deposit(money);
    }

    public boolean withDrawMoney(double money){
        return personalAccount.withdraw(money);
    }



    private void createPersonalAccountHistory(){

        String currentPath = Paths.get("").toAbsolutePath().toString();
        currentPath = currentPath + "/TradingFinalProject/src/Database/DBFiles/CustomerPersonalHistory/"+ getID() +"_PersonalHistory.txt";
        Path path = Paths.get(currentPath);
        boolean fileExists = Files.exists(path);
        if (!fileExists) {
            try {
                Files.createFile(path);
                try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.WRITE)) {
                    String header = "behavior, quantity" +
                            "\n";
                    writer.write(header);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //send request to manager to open a trading account
    public void sendTradingAccountRequest(){
        if (!checkTradingAccountExit()) {
            Request request = new Request(this.getID()+"");
            request.writeRequestToDB();
        }
    }

    public boolean checkTradingAccountExit() {
        String currentPath = Paths.get("").toAbsolutePath().toString();
        currentPath = currentPath + "/TradingFinalProject/src/Database/DBFiles/CustomerStockHistory/"+ getID() +"_StockHistory.txt";
        Path path = Paths.get(currentPath);
        return Files.exists(path);

    }

    public PersonalAccount getPersonalAccount() {
        return personalAccount;
    }

    public void setPersonalAccount(PersonalAccount personalAccount) {
        this.personalAccount = personalAccount;
    }

    public TradingAccount getTradingAccount() {
        return tradingAccount;
    }

    public void setTradingAccount(TradingAccount tradingAccount) {
        this.tradingAccount = tradingAccount;
    }

    public static String generateCurrentId(){
        return "C" + getCurrentID();
    }

    private static int getCurrentID(){
        return 0;
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
