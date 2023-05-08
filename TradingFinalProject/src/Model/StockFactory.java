package Model;

public class StockFactory {

    public static MarketStock createMarketStock(String name, double price) {
        return new MarketStock(name, price);
    }

    public static OwnedStock createOwnedStock(String name, int quantity, double purchasePrice) {
        return new OwnedStock(name, quantity, purchasePrice);
    }

}
