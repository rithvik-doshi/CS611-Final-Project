package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TransactionData {
    private ArrayList<String> transactionHistory;

    public TransactionData(String filePath) {
        transactionHistory = new ArrayList<>();
        readTransactionHistory(filePath);
    }

    private void readTransactionHistory(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                transactionHistory.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getTransactionHistory(String path) {
        readTransactionHistory(path);
        return transactionHistory;
    }
}
