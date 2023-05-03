package Model;

public class CustomerStockTradingSystem {

    private Customer customer;
    private PersonalAccount personalAccount;
    private TradingAccount tradingAccount;
    private SMProxy stockMarket = SMProxy.instance;

    public CustomerStockTradingSystem(Customer customer, PersonalAccount personalAccount) {
        this.customer = customer;
        this.personalAccount = personalAccount;
        this.tradingAccount = null;
    }

    // Request to create a trading account
    public void createTradingAccount() {
        if (customer.getTradingAccount() == null) {
            this.tradingAccount = new TradingAccount(Integer.toString(customer.getID()));
            customer.setTradingAccount(tradingAccount);
        } else {
            System.out.println("You already have a trading account.");
        }
    }

    // Allow money to be added/deducted from a personal account
    public void addFunds(double amount) {
        if (personalAccount.withdraw(amount)) {
            tradingAccount.saveMoney(amount);
        } else {
            System.out.println("Insufficient funds in the personal account.");
        }
    }

    public void deductFunds(double amount) {
        if (tradingAccount.withdrawMoney(amount)) {
            personalAccount.deposit(amount);
        } else {
            System.out.println("Insufficient funds in the trading account.");
        }
    }

    // Trade existing stocks
    public void buyStock(String name, int quantity) {
        double stockPrice = stockMarket.getStockPrice(name);
        double cost = stockPrice * quantity;

        if (tradingAccount.getBalance() >= cost) {
            tradingAccount.buyStock(name, quantity, stockPrice);
            deductFunds(cost);
        } else {
            System.out.println("Insufficient funds for this transaction.");
        }
    }

    public void sellStock(String name, int quantity) {
        if (tradingAccount.hasSufficientShares(name, quantity)) {
            double stockPrice = stockMarket.getStockPrice(name);
            double revenue = stockPrice * quantity;

            tradingAccount.sellStock(name, quantity, stockPrice);
            addFunds(revenue);
        } else {
            System.out.println("Insufficient shares for this transaction.");
        }
    }

    // See personal realized and unrealized profits
    public double getRealizedProfit() {
        return tradingAccount.getRealizedProfit();
    }

    public double getUnrealizedProfit() {
        return tradingAccount.getUnrealizedProfit(stockMarket);
    }
}
