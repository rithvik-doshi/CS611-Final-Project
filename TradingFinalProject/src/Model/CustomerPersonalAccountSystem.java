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
    private PersonalTransactionHistory personalTransactionHistory;

    public CustomerPersonalAccountSystem(Customer customer){
        this.customer = customer;
        personalAccount = new PersonalAccount(customer.getID(), customer.getBalance());
        personalTransactionHistory = new PersonalTransactionHistory(""+customer.getID(), customer.getName());
    }
//Yuxi Ge get current customer object
    public Customer getCustomer(){
        return customer;

    }


    public double getPersonalAccountBalance(){
        return customer.getBalance();
    }

    public ArrayList<String[]> readDataFromFile(String filePath) {
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


}
