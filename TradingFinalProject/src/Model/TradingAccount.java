package Model;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TradingAccount extends Account {
    int customerId;
    double netProfit;
    public boolean found = false;
    double realizedProfit;
    private StockTransactionHistory stockTransactionHistory;

    // double unrealizedProfit;
    private HashMap<String, Integer> stockHoldings = new HashMap<>();
    private HashMap<String, Double> purchasePrices = new HashMap<>();

    public TradingAccount(int customerId){
        this.customerId = customerId;
        found = readFromCustomerStockDBFile(customerId);
    }

    public void createStockTransactionHistory(){
        this.stockTransactionHistory = new StockTransactionHistory(Integer.toString(customerId), "");
    }

    public static String generateCurrentId(){
        return "T" + getCurrentID();
    }


    public boolean readFromCustomerStockDBFile(int customerId){
        String currentPath = Paths.get("").toAbsolutePath().toString();
        currentPath = currentPath + "/TradingFinalProject/src/Database/DBFiles/CustomerStocks/"+customerId+"_Stocks.txt";

//        String filePath = personalTransactionHistory.historyType(customer.getID()+"");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(currentPath));
            reader.readLine();
            String line; // skip header line
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
//                System.out.println("Trading Account Read from DB");
//                System.out.println(fields[0]);
//                System.out.println(fields[0]+ " "+ fields[1] +" "+ fields[2]);
                String name = fields[0];
                        double purchasePrice = Double.valueOf(fields[2]);
                                int quantity = Integer.valueOf(fields[1]);
                stockHoldings.put(name, stockHoldings.getOrDefault(name, 0) + quantity);
                purchasePrices.put(name, purchasePrice);
//                                ownedStocks.add(new OwnedStock(name, quantity, purchasePrice));


            }
            reader.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public HashMap<String, Integer> getStockHoldings(){
        return stockHoldings;
    }

    public HashMap<String, Double> getPurchasePrices(){
        return purchasePrices;
    }


//    public ArrayList<OwnedStock> getAllOwnedStocks(){
//        return ownedStocks;
//    }
// determine if the trading account can have a derivative account by checking the net profit over 10000

    public boolean canHaveDerivativeAccount(){

        if(this.getRealizedProfit() > 10000){
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

//        ownedStocks.add( new OwnedStock(name, quantity, price));
        addlineToStockHistoryDBFiles("buy", name, quantity, quantity*price);
        if(stockHoldings.get(name)!=null){
//            System.out.println("Stock found in holding Stock");
            changeStockQuantity(name, -1*quantity);}
        else{
        addLineToCustomerStocks(name, quantity, price);}
        stockHoldings.put(name, stockHoldings.getOrDefault(name, 0) + quantity);
        purchasePrices.put(name, price);
    }

    public void addlineToStockHistoryDBFiles(String option, String itemName, int quantity, double totalPrice){
        String currentPath = Paths.get("").toAbsolutePath().toString();
        currentPath = currentPath + "/TradingFinalProject/src/Database/DBFiles/CustomerStockHistory/"+customerId+"_StockHistory.txt";
        String data = option + ", " + itemName+", "+quantity+", "+totalPrice+"\n";

        try {
            FileWriter fw = new FileWriter(currentPath, true);
            fw.write(data);
            fw.close();
//            System.out.println("Successfully add line.");
        } catch (IOException e) {
//            System.out.println("An error occurred while writing request to DBfile.-----addlineToStockHistoryDBFiles");
            e.printStackTrace();
        }
    }

    public void addLineToCustomerStocks(String name, int quantity, double pruchasePrice){
        String currentPath = Paths.get("").toAbsolutePath().toString();
        currentPath = currentPath + "/TradingFinalProject/src/Database/DBFiles/CustomerStocks/"+customerId+"_Stocks.txt";
        String data = name + "," + quantity+","+pruchasePrice+"\n";

        try {
            FileWriter fw = new FileWriter(currentPath, true);
            fw.write(data);
            fw.close();
//            System.out.println("Successfully wrote to the file.----addLineToCustomerStocks"+" "+name+" "+quantity+" "+purchasePrices);
        } catch (IOException e) {
//            System.out.println("An error occurred while writing request to DBfile.----addLineToCustomerStocks");
            e.printStackTrace();
        }
    }

    public void changeStockQuantity(String name , int reduceNumber){
        String currentPath = Paths.get("").toAbsolutePath().toString();
        currentPath = currentPath + "/TradingFinalProject/src/Database/DBFiles/CustomerStocks/"+customerId+"_Stocks.txt";
        Path path = Paths.get(currentPath);
        try {
            List<String> fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
            int lineNumber = -1;

            for (int i = 1; i < fileContent.size(); i++) {
                String[] fields = fileContent.get(i).split(",");
//                System.out.println(fields[0]+ " "+ fields[1]+" "+ fields[2]);
////                System.out.println();
                if (fields[0].equals(name)) {

                    lineNumber = i;
                    break;
                }
            }

            if (lineNumber != -1) {
                String[] fields = fileContent.get(lineNumber).split(",");
//                System.out.println(fields[0]+ " "+ fields[1]);
                fields[1] = Integer.valueOf(fields[1])-reduceNumber+"";
                fileContent.set(lineNumber, String.join(",", fields));
                Files.write(path, fileContent, StandardCharsets.UTF_8);
//                System.out.println("Change Stock Quantity: "+ name + -1*reduceNumber);
            }  }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sellStock(String name, int quantity, double price) {
        int currentShares = stockHoldings.getOrDefault(name, 0);
        if (currentShares >= quantity) {

            stockHoldings.put(name, currentShares - quantity);
            changeStockQuantity(name, quantity);
            if (stockHoldings.get(name) == 0) {
                stockHoldings.remove(name);
                updateCustomerStocksDBFile();
            }

            double purchasePrice = purchasePrices.getOrDefault(name, 0.0);
            realizedProfit += (price - purchasePrice) * quantity;
            addlineToStockHistoryDBFiles("sell", name, quantity, quantity*price);

        } else {
//            System.out.println("Insufficient shares to sell.");
        }

    }

    private void updateCustomerStocksDBFile() {
        try {
            String currentPath = Paths.get("").toAbsolutePath().toString();
            currentPath = currentPath + "/TradingFinalProject/src/Database/DBFiles/CustomerStocks/"+customerId+"_Stocks.txt";
            FileWriter fileWriter = new FileWriter(currentPath, false);
            fileWriter.write("itemname, quantity\n");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (String name : stockHoldings.keySet()) {
                bufferedWriter.write(name + "," + stockHoldings.get(name)+","+purchasePrices.get(name)+"\n");
            }
            bufferedWriter.close();
//            System.out.println("Successfully updated the file.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean hasSufficientShares(String name, int quantity) {
        return stockHoldings.getOrDefault(name, 0) >= quantity;
    }

    public double getRealizedProfit() {
        if (stockTransactionHistory == null) {
            createStockTransactionHistory();
        }
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
