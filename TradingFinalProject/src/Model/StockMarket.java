package Model;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class StockMarket {

    public static final StockMarket instance;

    static {
        instance = new StockMarket();
    }

    String currentPath = Paths.get("").toAbsolutePath() + "/TradingFinalProject/src/Database/DBFiles/";

    private StockMarket() {
        try {
            Scanner scanner = new Scanner(new File(currentPath + "MarketStocks.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length != 2) {
                    continue;
                }
                MarketStock stock = new MarketStock(parts[0], Double.parseDouble(parts[1]));
                stocks.add(stock);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean updateStocks(){
        String fileName = "MarketStocks.txt";
        try {
            FileWriter fileWriter = new FileWriter(currentPath + fileName);
            fileWriter.write("");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (MarketStock stock : stocks) {
                bufferedWriter.write(stock.getName() + "," + stock.getMoney() + "\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ArrayList<MarketStock> stocks = new ArrayList<>();

    public ArrayList<MarketStock> getStocks() {
        return stocks;
    }

    public boolean addStock(String name, double price) {
        for (MarketStock stock : stocks) {
            if (stock.getName().equals(name)) {
                return false;
            }
        }
        stocks.add(StockFactory.createMarketStock(name, price));
        return updateStocks();
    }

    public boolean removeStock(String name) {
        for (MarketStock stock : stocks) {
            if (stock.getName().equals(name)) {
                stocks.remove(stock);
                break;
            }
        }
        return updateStocks();
    }

    public double getStockPrice(String name) {
        for (MarketStock stock : stocks) {
            if (stock.getName().equals(name)) {
                return stock.getMoney();
            }
        }
        return 0;
    }

    public boolean setStockPrice(String name, double price) {
        for (MarketStock stock : stocks) {
            if (stock.getName().equals(name)) {
                stock.setMoney(price);
            }
        }
        return updateStocks();
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (MarketStock stock : stocks) {
            result.append(stock.toString()).append("\n");
        }
        return result.toString();
    }
}
