package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerLogin {
    private ArrayList<Customer> customers = new ArrayList<>();

    public CustomerLogin(String filePath) {
        try {
            Scanner scanner = new Scanner(new File(filePath));
            if (scanner.hasNextLine()) { // Skip the first line if there's a header
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(", ");
                if (parts.length != 4) {
                    continue;
                }
                Customer customer = new Customer(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3]);
                customers.add(customer);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean checkLogin(String username, String password) {
        for (Customer customer : customers) {
            if (customer.getName().equals(username) && customer.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
