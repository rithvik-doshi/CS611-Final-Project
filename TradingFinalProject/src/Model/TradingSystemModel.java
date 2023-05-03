package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * This is the top-level composite class for the Trading System Model.
 */
public class TradingSystemModel {
    private PortfolioManagementSystem PMS;
    private CustomerStockTradingSystem CSTS;
    private static final SMProxy SMP = SMProxy.instance;
    private ModelMode mode = ModelMode.NONE;
    private UserStatus status = UserStatus.LOGGED_OUT;

    private static final String currentPath = Paths.get("").toAbsolutePath() + "/TradingFinalProject/src/Database/DBFiles/";

//    login, load user/create user, and then enter them into the correct system

    public boolean login(String username, String password) {

//        Check if user exists in customer database
        File file = new File(currentPath + "Customer.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length < 4) {
                    continue;
                }
                if (parts[2].equals(username) && parts[3].equals(password)) {
                    mode = ModelMode.CUSTOMER;
                    status = UserStatus.LOGGED_IN;
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

//        Check if user exists in manager database
        file = new File(currentPath + "Manager.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length < 4) {
                    continue;
                }
                if (parts[2].equals(username) && parts[3].equals(password)) {
                    mode = ModelMode.MANAGER;
                    status = UserStatus.LOGGED_IN;
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        mode = ModelMode.NONE;
        status = UserStatus.LOGGED_OUT;
        return false;
    }

    public boolean register(String username, String password) {

        return true;
    }

    public static void main(String[] args) {

        Manager manager = new Manager(1, "Rithvik Doshi", "bruh@buh.com", "password");
//        This is not the correct way to initialize a manager, doing it for testing purposes
        System.out.println(manager.keyValue());

        System.out.println(SMP.addStock("BRUH", 100, manager.getID(), manager.getKey()));
        System.out.println(SMP.addStock("BRUH", 10230.2, manager.getID(), manager.getKey()));
        System.out.println(SMP.removeStock("BRUH", manager.getID(), manager.getKey()));
        System.out.println(SMP.addStock("BRUH", 10230.2, manager.getID(), manager.getKey()));
        System.out.println(SMP.setStockPrice("BRUH", 23, manager.getID(), manager.getKey()));

        PersonalTransactionHistory pth = new PersonalTransactionHistory("32134", "Rithvik Doshi");
        pth.addToHistory(new PersonalTransaction("WITHDRAW", 345));
        System.out.println(pth);

        StockTransactionHistory sth = new StockTransactionHistory("2", "Yuxi");
        sth.addToHistory(new StockTransaction("BUY", "BRUH", 100, 100));
        System.out.println(sth);

    }


}

