package Model;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class Request {
    private String sender;
    // private String receiver;
    private String status;
    private boolean isApproved;

    public Request(String sender) {
        this.sender = sender;
        this.status = "Pending";
        // this.receiver = receiver;
    }
    // Constructor for read data from database
    public Request(String sender2, String status2) {
        this.sender = sender2;
        this.status = status2;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void approve() {
        this.status = "Approved";
        this.isApproved = true;
        setApproved();
        createCustomerStockDBFiles();
        createStockHisotyDBFiles();
        changeStatusInDBFile("Approved");
    }

    public void changeStatusInDBFile(String newStatus){
        String currentPath = Paths.get("").toAbsolutePath().toString();
        currentPath = currentPath + "/TradingFinalProject/src/Database/DBFiles/AccountCreationRequests.txt";
        Path path = Paths.get(currentPath);
        try {
            List<String> fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
            int lineNumber = -1;

            for (int i = 0; i < fileContent.size(); i++) {
                String[] fields = fileContent.get(i).split(" ");
//                System.out.println(fields[0]+ " "+ fields[1]);
//                System.out.println(getSender());
                if (fields[0].equals(getSender())) {

                    lineNumber = i;
                    break;
                }
            }

            if (lineNumber != -1) {
                String[] fields = fileContent.get(lineNumber).split(" ");
//                System.out.println(fields[0]+ " "+ fields[1]);
                fields[1] = newStatus;
                fileContent.set(lineNumber, String.join(" ", fields));
                Files.write(path, fileContent, StandardCharsets.UTF_8);
//                System.out.println("Request status changed to Approved SenderID: "+ getSender());
            }  }
        catch (IOException e) {
            e.printStackTrace();
        }


    }



    public void createStockHisotyDBFiles(){
        String currentPath = Paths.get("").toAbsolutePath().toString();
        currentPath = currentPath + "/TradingFinalProject/src/Database/DBFiles/CustomerStockHistory/"+ getSender() +"_StockHistory.txt";
        Path path = Paths.get(currentPath);
        boolean fileExists = Files.exists(path);
        if(fileExists){return;}
        else{
            try {
                File file = new File("/TradingFinalProject/src/Database/DBFiles/CustomerStockHistory", getSender() +"_StockHistory.txt");
                Files.createFile(path);
                try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.WRITE)) {
                    String header = "behavior, item name, quantity, total price" +
                            "\n";
                    writer.write(header);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void createCustomerStockDBFiles(){
        String currentPath = Paths.get("").toAbsolutePath().toString();
        currentPath = currentPath + "/TradingFinalProject/src/Database/DBFiles/CustomerStocks/"+ getSender() +"_Stocks.txt";
        Path path = Paths.get(currentPath);
        boolean fileExists = Files.exists(path);
        if(fileExists){return;}
        else{
            try {
                File file = new File("/TradingFinalProject/src/Database/DBFiles/CustomerStocks", getSender() +"_Stocks.txt");
                Files.createFile(path);
                try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.WRITE)) {
                    String header = "itemname, quantity" +
                            "\n";
                    writer.write(header);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void reject() {
        this.isApproved = false;
        setRejected();
        changeStatusInDBFile("Rejected");
    }

    public String getSender() {
        return sender;
    }

    //set status to Approved
    public void setApproved() {
        this.status = "Approved";
    }
    //set status to Rejected
    public void setRejected() {
        this.status = "Rejected";
    }

    public String toString(){
        return "Request: {Sender: " + sender + "; Status: " + status+"}";
    }

    public String getStatus() {
        return status;
    }

    //TODO write request to database
    public void writeRequestToDB() {

        RequestFactory rf = new RequestFactory();

        rf.createRequests();

        if (rf.senderInRequests(sender)) {
//            System.out.println("Request already exists in DB");
            return;
        }

        String path = "TradingFinalProject/src/Database/DBFiles/AccountCreationRequests.txt";
        String data = sender + " " + status+"\n";

        try {
            FileWriter fw = new FileWriter(path, true);
            fw.write(data);
            fw.close();
//            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
//            System.out.println("An error occurred while writing request to DBfile.");
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        Request r = new Request("1", "Pending");
//
//    }
}
