package Model;

import Controller.CustomerStockTradingSystem;
import Controller.PortfolioManagementSystem;

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

    public boolean login(String email, String password) {

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
                if (parts[2].equals(email) && parts[3].equals(password)) {
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
                if (parts[2].equals(email) && parts[3].equals(password)) {
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

    public ModelMode getMode() {
        return mode;
    }

    public UserStatus getStatus() {
        return status;
    }


}

