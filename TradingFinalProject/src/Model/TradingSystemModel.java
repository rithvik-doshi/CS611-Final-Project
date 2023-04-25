package Model;

/**
 * This is the top-level composite class for the Trading System Model.
 */
public class TradingSystemModel {
    private PortfolioManagementSystem PMS;
    private CustomerStockTradingSystem CSTS;
    private static final SMProxy SMP = SMProxy.instance;

//    login, load user/create user, and then enter them into the correct system

    public static void main(String[] args) {

        Manager manager = new Manager(1, "Rithvik Doshi", "bruh@buh.com", "password");
//        This is not the correct way to initialize a manager, doing it for testing purposes
        System.out.println(manager.keyValue());

        System.out.println(SMP.addStock("BRUH", 100, manager.getID(), manager.getKey()));
        System.out.println(SMP.addStock("BRUH", 10230.2, manager.getID(), manager.getKey()));
        System.out.println(SMP.removeStock("BRUH", manager.getID(), manager.getKey()));
        System.out.println(SMP.addStock("BRUH", 10230.2, manager.getID(), manager.getKey()));
        System.out.println(SMP.setStockPrice("BRUH", 23, manager.getID(), manager.getKey()));
    }


}

