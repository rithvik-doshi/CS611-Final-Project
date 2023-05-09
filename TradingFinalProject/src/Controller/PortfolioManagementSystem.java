package Controller;

import Model.*;

import java.nio.file.Paths;
import java.util.ArrayList;

public class PortfolioManagementSystem {

    private final Manager manager ;
    SMProxy stockMarket = SMProxy.instance;

    ArrayList<Customer> customerInfor = new CustomerLogin(Paths.get("").toAbsolutePath() +"/TradingFinalProject/src/Database/DBFiles/Customer.txt").getCustomersList();

    public PortfolioManagementSystem(Manager manager) {
        this.manager = manager;
    }

    public void approveRequest(Request request) {
        manager.approveRequest(request);
//        System.out.println("Request approved by manager");
    }

    public ArrayList<Customer> getCustomerInfor(){
        return this.customerInfor;
    }

    public String getNamefromId(int id){
        return customerInfor.get(id-1).getName();
    }

    public void rejectRequest(Request request) {
        manager.rejectRequest(request);
        System.out.println("Request rejected by manager");
    }

    public void updateStockPrice(MarketStock marketStock, double price) {
        if (price < 0) {
            System.out.println("Invalid Price. Change Price failed");
        } else {
            marketStock.setMoney(price);
            System.out.println("Stock price updated");
        }
    }

    //add a new stock to the stock market
    public void addStock(String name, double price, int ID, ManagerKey key) {
        if (price < 0) {
            System.out.println("Invalid Price. Add Stock failed");
        }
        stockMarket.addStock(name, price, ID, key);
        System.out.println("Stock added, Name: " + name + " Price: " + price);
    }

    //remove a stock from the stock market
    public void removeStock(String name, int ID, ManagerKey key) {
        stockMarket.removeStock(name, ID, key);
        System.out.println("Stock removed, Name: " + name);
    }

    //get customer's unrealized profit
    public double getUnrealizedProfit(Customer customer) {
        return customer.getTradingAccount().getUnrealizedProfit(stockMarket);
    }

    //get customer's realized profit
    public double getRealizedProfit(Customer customer) {
        return customer.getTradingAccount().getRealizedProfit();
    }


}
