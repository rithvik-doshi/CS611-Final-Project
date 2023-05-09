package Model;

import java.util.ArrayList;
import java.util.Arrays;

public class StockTransactionHistory extends TransactionHistory{


    public StockTransactionHistory(String customerID, String name) {
        super(customerID, name);
    }

    public String historyType(String fileIdentity) {
        return "CustomerStockHistory/" + fileIdentity + "_StockHistory.txt";
    }

    public Transaction getTransaction(String[] parts) {
        String behaviour = parts[0];
//        System.out.println("In get transaction: " + Arrays.toString(parts));
        double money = Double.parseDouble(parts[3]);
        int quantity = Integer.parseInt(parts[2]);
        String stockName = parts[1];
        return new StockTransaction(behaviour, stockName, quantity, money);
    }

    @Override
    public String fileHeader() {
        return "behavior, item name, quantity, total price\n";
    }

    @Override
    public double getProfit(ArrayList<Transaction> history) {
        double profit = 0;
        for (Transaction t : history) {

            if (t instanceof StockTransaction) {
                if (t.getBehaviour().toLowerCase().equals("buy")) {
                    profit -= t.getMoney();
                } else {
                    profit += t.getMoney();
                }
            }
        }
//        System.out.println("Profit: " + profit);
        return profit;
    }

}
