package Model;
import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PersonalHistory {
    private TransactionPersonalHistory transactionPersonalHistory;
    private ArrayList<TransactionPersonalHistory> personalHistories = new ArrayList<>();
    private Customer customer;
    private static String Path;

    public PersonalHistory(TransactionPersonalHistory transactionPersonalHistory, Customer customer){
        this.transactionPersonalHistory = transactionPersonalHistory;
        this.customer = customer;
        String customerName = customer.getName();
        Path = Paths.get("").toAbsolutePath().toString()+"\"/TradingFinalProject/src/Database/DBFiles/"+customerName+"_PersonalHistory.txt";
        loadTransactions(Path);
    }


    public static ArrayList<TransactionPersonalHistory> loadTransactions(String filePath) {
        ArrayList<TransactionPersonalHistory> personalHistories = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                String line;
                while ((line = reader.readLine()) != null) {
                    TransactionPersonalHistory transaction = new TransactionPersonalHistory(line);
                    personalHistories.add(transaction);
                }
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return personalHistories;
    }

}
