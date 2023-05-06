package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Array;
import java.util.ArrayList;

public class CustomerPersonalAccountSystem {

    private Customer customer;
    private PersonalAccount personalAccount;

    public CustomerPersonalAccountSystem(Customer customer){
        this.customer = customer;
        personalAccount = new PersonalAccount(customer.getID(), customer.getBalance());
//        personalTransactionHistory = new PersonalTransactionHistory(""+customer.getID(), customer.getName());
    }
    //Yuxi Ge get current customer object
    public Customer getCustomer(){
        return customer;

    }


    public double getPersonalAccountBalance(){
        return customer.getBalance();
    }


    // returned sample should look like:[["Deposit", "1000"], ["Withdraw", "1000"]]
    public ArrayList<String[]> readPersonalTransactionHistoryFromDB() {
        String currentPath = Paths.get("").toAbsolutePath().toString();
        currentPath = currentPath + "/TradingFinalProject/src/Database/DBFiles/CustomerPersonalHistory/"+customer.getID()+"_PersonalHistory.txt";

//        String filePath = personalTransactionHistory.historyType(customer.getID()+"");
        ArrayList<String[]> data = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(currentPath));
            String line = reader.readLine(); // skip header line
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                data.add(fields);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }


    public void saveMoney(double money){
        customer.saveMoney(money);
    }

    public boolean withdrawMoney(double money){
        return customer.withDrawMoney(money);}


    public boolean ifCustomerHasTradingAccount(){
        return customer.checkTradingAccountExit();
    }

    public void sendOpenTradingAccountRequest(){
        customer.sendTradingAccountRequest();
    }

    public static void main(String[] args) {
        Customer c= new Customer(1,"s","s","s",1);
        CustomerPersonalAccountSystem customerPersonalAccountSystem= new CustomerPersonalAccountSystem(c);
        customerPersonalAccountSystem.saveMoney(100);
        System.out.println(c.getBalance());
    }


}
