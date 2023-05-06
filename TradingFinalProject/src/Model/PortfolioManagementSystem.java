package Model;
//- Manage the stocks available to trade and current prices
// - Approve new customers
// - Track (receive reports) of all customers and their unrealized and/or realized
// profits
// - Notify all customers who have made more than 10k in realized trading 
// gains they have the opportunity to create a derivative trading account
// (allowing them to trade options). The ability to trade options is not to be 
// implemented.

public class PortfolioManagementSystem {

    private  Manager manager ;
    SMProxy stockMarket = SMProxy.instance;
    

    public PortfolioManagementSystem(Manager manager) {
        this.manager = manager;
    }

    public void approveRequest(Request request) {
        manager.approveRequest(request);
        System.out.println("Request approved by manager");
    }

    public void rejectRequest(Request request) {
        manager.rejectRequest(request);
        System.out.println("Request rejected by manager");
    }

    public void updateStockPrice(MarketStock marketStock, double price) {
        if(price < 0){System.out.println("Invalid Price. Change Price failed");}
        else{
        marketStock.setMoney(price);
        System.out.println("Stock price updated");
    }}

    //add a new stock to the stock market
    public void addStock(String name, double price, int ID, ManagerKey key) {
        if(price < 0){System.out.println("Invalid Price. Add Stock failed");}
        else{}
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


//TODO:- Notify all customers who have made more than 10k in realized trading

}
