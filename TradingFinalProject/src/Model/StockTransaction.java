package Model;

public class StockTransaction extends Transaction{

    private final String stockName;

    private final int quantity;

    public StockTransaction(String behavior, String stockName, int quantity, double price) {
        super(behavior, price);
        this.stockName = stockName;
        this.quantity = quantity;
    }

    public String getStockName() {
        return stockName;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String[] getInfoAsArray() {
        return new String[]{this.getBehaviour(), this.getStockName(), String.valueOf(this.getQuantity()), String.valueOf(this.getMoney())};
    }

    public String toString() {
        return this.getBehaviour() + "," + this.getStockName() + "," + this.getQuantity() + "," + this.getMoney();
    }

}
