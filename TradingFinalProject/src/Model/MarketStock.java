package Model;

public class MarketStock extends Stock implements usesMoney {
    private double price;

    @Override
    public double getMoney() {
        return price;
    }

    @Override
    public void setMoney(double money) {

    }
}
