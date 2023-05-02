package Model;

public class StockTransactionHistory extends TransactionHistory{

    public StockTransactionHistory(String customerID, String name) {
        super(customerID, name);
    }

    public String historyType(String fileIdentity) {
        return "CustomerStockHistory/" + fileIdentity + "_StockHistory.txt";
    }

    public Transaction getTransaction(String[] parts) {
        String behaviour = parts[0];
        double money = Double.parseDouble(parts[3]);
        int quantity = Integer.parseInt(parts[2]);
        String stockName = parts[1];
        String timestamp = parts[4];
        return new StockTransaction(behaviour, stockName, quantity, money, timestamp);
    }

}
