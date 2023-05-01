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
    Manager manager;
    StockMarket stockMarket;
    

    public PortfolioManagementSystem(Manager manager, StockMarket stockMarket) {
        this.manager = manager;
        this.stockMarket = stockMarket;
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
    public void addStock(String name, double price) {
        if(price < 0){System.out.println("Invalid Price. Add Stock failed");}
        else{}
        stockMarket.addStock(name, price);
        System.out.println("Stock added, Name: " + name + " Price: " + price);
    }

    //remove a stock from the stock market
    public void removeStock(String name) {
        stockMarket.removeStock(name);
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
