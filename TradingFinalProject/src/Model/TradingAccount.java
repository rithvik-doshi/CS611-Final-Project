package Model;
import java.util.HashMap;

public class TradingAccount extends Account {

    double netProfit;
    double realizedProfit;
    // double unrealizedProfit;
    private HashMap<String, Integer> stockHoldings = new HashMap<>();
    private HashMap<String, Double> purchasePrices = new HashMap<>();

    private StockTransactionHistory stockTransactionHistory;

    public TradingAccount(String ownerID) {
        super(ownerID, "Trading");
        this.id = generateCurrentId();
        this.stockTransactionHistory = new StockTransactionHistory(ownerID, id);
    }

    public static String generateCurrentId(){
        return "T" + getCurrentID();
    }
// determine if the trading account can have a derivative account by checking the net profit over 10000

    public boolean canHaveDerivativeAccount(){
        if(this.realizedProfit > 10000){
            return true;
        }else{
            return false;
        }
    }
    

    public void saveMoney(double amount){
        this.balance += amount;
    }

    public boolean withdrawMoney(double amount){
        if(this.balance >= amount){
            this.balance -= amount;
            return true;
        }else{
            return false;
        }   
    }

    public boolean transferMoney(double amount, PersonalAccount account){
        if(this.withdrawMoney(amount)){
            account.deposit(amount);
            return true;
        }
        else{
            return false;
        }

    }


    //add by Yuxi Ge
    public void buyStock(String name, int quantity, double price) {
        stockHoldings.put(name, stockHoldings.getOrDefault(name, 0) + quantity);
        purchasePrices.put(name, price);
    }

    public void sellStock(String name, int quantity, double price) {
        int currentShares = stockHoldings.getOrDefault(name, 0);
        if (currentShares >= quantity) {
            stockHoldings.put(name, currentShares - quantity);

            if (stockHoldings.get(name) == 0) {
                stockHoldings.remove(name);
            }

            double purchasePrice = purchasePrices.getOrDefault(name, 0.0);
            realizedProfit += (price - purchasePrice) * quantity;
        } else {
            System.out.println("Insufficient shares to sell.");
        }
    }

    public boolean hasSufficientShares(String name, int quantity) {
        return stockHoldings.getOrDefault(name, 0) >= quantity;
    }

    public double getRealizedProfit() {
        return stockTransactionHistory.getProfit();
    }

    public double getUnrealizedProfit(SMProxy stockMarket) {
        double unrealizedProfit = 0.0;

        for (String name : stockHoldings.keySet()) {
            int shares = stockHoldings.get(name);
            double currentPrice = stockMarket.getStockPrice(name);
            double purchasePrice = purchasePrices.getOrDefault(name, 0.0);

            unrealizedProfit += (currentPrice - purchasePrice) * shares;
        }

        return unrealizedProfit;
    }
}
