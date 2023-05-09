package Model;

import Controller.CustomerPersonalAccountSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerLogin {
    private final ArrayList<Customer> customers = new ArrayList<>();
    private CustomerPersonalAccountSystem customerPersonalAccountSystem;

    public CustomerLogin(String filePath) {
        try {
            Scanner scanner = new Scanner(new File(filePath));
            if (scanner.hasNextLine()) { // Skip the first line if there's a header
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(", ");
                if (parts.length != 5) {
                    continue;
                }
                Customer customer = new Customer(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], Double.parseDouble(parts[4]));
                customers.add(customer);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //get all customer ArrayList
    public ArrayList<Customer> getCustomersList(){
        for (Customer customer : customers) {
            TradingAccount ta;
            ta = new TradingAccount(customer.getID());
            if (ta.found) {
                customer.setTradingAccount(ta);
                customer.getTradingAccount().createStockTransactionHistory();
            }
        }
        return customers;
    }


    public boolean checkLogin(String username, String password) {
        for (Customer customer : customers) {
            if (customer.getName().equals(username) && customer.getPassword().equals(password)) {
                customerPersonalAccountSystem = new CustomerPersonalAccountSystem(customer);
                return true;
            }
        }
        return false;
    }

    public CustomerPersonalAccountSystem getCustomerPersonalAccountSystem(){
        return customerPersonalAccountSystem;
    }

    public int registerNewCustomer(String name, String email, String password,String path) {

        int newID = customers.size() + 1;

        for (Customer customer : customers) {
            if (customer.getEmail().equals(email)) {
                return 0;
            }
        }

//        System.out.println(customers.size());
        Customer newCustomer = new Customer(newID, name, email, password,0);
        customers.add(newCustomer);

        if(name.isEmpty()|| email.isEmpty()|| password.isEmpty()){
            return -1;
        }
        try {
            FileWriter fileWriter = new FileWriter(path, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(newID + ", " + name + ", " + email + ", " + password + ", 0"+ "\n");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

}
