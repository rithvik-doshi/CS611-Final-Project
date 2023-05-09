package Controller;

import Model.Customer;
import Model.PersonalAccount;
import Model.SMProxy;
import Model.TradingAccount;

import java.util.HashMap;

public class CustomerStockTradingSystem {

    private Customer customer;
    private PersonalAccount personalAccount;
    private TradingAccount tradingAccount;
    private SMProxy stockMarket = SMProxy.instance;

    public CustomerStockTradingSystem(Customer customer, PersonalAccount personalAccount) {
        this.customer = customer;
        this.personalAccount = personalAccount;
        this.tradingAccount = new TradingAccount(customer.getID());
    }

    public double getBalance(){
        return personalAccount.getBalance();
    }

    public HashMap<String, Integer> getStockHoldings(){
        return tradingAccount.getStockHoldings();
    }

    // Allow money to be added/deducted from a personal account
    public void addFunds(double amount) {
        personalAccount.deposit(amount);
    }

    public void deductFunds(double amount) {
        if(personalAccount.getBalance() < amount){
//            System.out.println("not enough funds");
        }
        else {
            personalAccount.withdraw(amount);
        }
    }

    // Trade existing stocks
    public boolean buyStock(String name, int quantity) {
        double stockPrice = stockMarket.getStockPrice(name);
        double cost = stockPrice * quantity;

        if (personalAccount.getBalance() >= cost) {
            tradingAccount.buyStock(name, quantity, stockPrice);
            deductFunds(cost);
            return true;
        } else {

//            System.out.println("Insufficient funds for this transaction.");
            return false;
        }
    }

    public void sellStock(String name, int quantity) {
        if (tradingAccount.hasSufficientShares(name, quantity)) {
            double stockPrice = stockMarket.getStockPrice(name);
            double revenue = stockPrice * quantity;

            tradingAccount.sellStock(name, quantity, stockPrice);
            addFunds(revenue);
        } else {
//            System.out.println("Insufficient shares for this transaction.");
        }
    }

    // See personal realized and unrealized profits
    public double getRealizedProfit() {
        return tradingAccount.getRealizedProfit();
    }

    public double getUnrealizedProfit() {
        return tradingAccount.getUnrealizedProfit(stockMarket);
    }

    public boolean canHaveDerivativeAccount(){
        return tradingAccount.canHaveDerivativeAccount();
    }

    public static void  main(String[]arg){

    }

    }
