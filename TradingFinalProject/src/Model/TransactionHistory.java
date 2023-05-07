package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class TransactionHistory {

    private final ArrayList<Transaction> history;
    private final String fileName;

    private static final String currentPath = Paths.get("").toAbsolutePath() + "/TradingFinalProject/src/Database/DBFiles/";

    protected TransactionHistory(String customerID, String name) {
        //TODO: load history from file based on input params
        String fileIdentity = customerID;

        fileName = historyType(fileIdentity);

        File file = new File(currentPath + fileName);

        history = new ArrayList<>();

        try {
            if (!file.isFile()) {
                file.createNewFile();
                System.out.println("Created new file: " + file.getAbsolutePath());
//                add a line to the file
                FileWriter fw = new FileWriter(file, true);
                fw.write("Behavior, quantity\n");
                return;
            }
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length < 2){
                    continue;
                }
                history.add(getTransaction(parts));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract String historyType(String fileIdentity);

    public abstract Transaction getTransaction(String[] parts);

    public boolean addToHistory(Transaction t){
        //TODO: add to history
        history.add(t);

        File file = new File(currentPath + fileName);

        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file, true));
            fileWriter.write(t.toString() + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Transaction transaction : history){
            sb.append(transaction).append("\n");
        }
        return sb.toString();
    }

}
