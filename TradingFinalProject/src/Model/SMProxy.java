package Model;

import java.util.ArrayList;

public class SMProxy {
    private static final StockMarket SM = StockMarket.instance;
    public static final SMProxy instance;

    static {
        instance = new SMProxy();
    }

    private boolean validate(int ID, String key) {

        ManagerKey managerKey = new ManagerKey(ID);

        System.out.println(key + " " + managerKey.getKeyValue());

        if (managerKey.getKeyValue().equals(key)) {
            return true;
        }

        return false;
    }

    public double getStockPrice(String name) {
        return SM.getStockPrice(name);
    }

    public boolean addStock(String name, double price, int ID, ManagerKey key) {
        if (validate(ID, key.getKeyValue())) {
            return SM.addStock(name, price);
        }
        return false;
    }

    public boolean removeStock(String name, int ID, ManagerKey key) {
        if (validate(ID, key.getKeyValue())) {
            return SM.removeStock(name);
        }
        return false;
    }

    public boolean setStockPrice(String name, double price, int ID, ManagerKey key) {
        if (validate(ID, key.getKeyValue())) {
            return SM.setStockPrice(name, price);
        }
        return false;
    }

    public ArrayList<MarketStock> getAllStocks() {
        return SM.getStocks();
    }

}
