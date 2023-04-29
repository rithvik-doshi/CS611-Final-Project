package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RequestFactory {
    
    private final String filePath = "TradingFinalProject/src/Database/DBFiles/AccountCreationRequests.txt";
    
    public RequestFactory() {
    }
    
    public ArrayList<Request> createRequests() {
        ArrayList<Request> requests = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                System.out.println(parts[0]);
                String sender = parts[0].trim().replaceAll("\"", "");
                String status = parts[1].trim().replaceAll("\"", "");
                Request request = new Request(sender, status);
                requests.add(request);
                System.out.println(request);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return requests;
    }
    
}

