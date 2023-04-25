package Model;

public class MarketStock extends Stock implements usesMoney {
    private double price;

    public MarketStock(String name, double price) {
        super(name);
        if (price < 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        this.price = price;
    }

    @Override
    public double getMoney() {
        return price;
    }

    @Override
    public void setMoney(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        this.price = price;
    }

    @Override
    protected String stringify() {
        return "Price: " + price;
    }
}
