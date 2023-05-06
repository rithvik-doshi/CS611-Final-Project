package Model;

import View.EntryInterface;
import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class RequestFactory {
    private ArrayList<Request> requests = new ArrayList<>();
    private final String filePath = Paths.get("").toAbsolutePath().toString() + "/TradingFinalProject/src/Database/DBFiles/AccountCreationRequests.txt";
    
    public RequestFactory() {
    }
    
    public ArrayList<Request> createRequests() {
        System.out.println(filePath);

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                System.out.println(parts[0]);

                String sender = parts[0].trim();
                String status = parts[1].trim();
                Request request = new Request(sender, status);
                requests.add(request);
                System.out.println(request);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return requests;
    }

    public ArrayList<Request> removeApprovedAndRejectedRequest(){
        ArrayList<Request> result = new ArrayList<>();
        for(Request r :requests){
            if(r.getStatus().equals("Pending")){
                result.add(r);
            }
        }
        return result;
    };



    public static void main(String[] args) {

        RequestFactory createRequests = new RequestFactory();

        ArrayList<Request> requests = new ArrayList<>();
        requests = createRequests.createRequests();
        System.out.println(requests.size());
    }

}

