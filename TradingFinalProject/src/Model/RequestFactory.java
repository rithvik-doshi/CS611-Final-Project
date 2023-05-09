package Model;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;

public class RequestFactory {
    private ArrayList<Request> requests = new ArrayList<>();
    private final String filePath = Paths.get("").toAbsolutePath() + "/TradingFinalProject/src/Database/DBFiles/AccountCreationRequests.txt";
    
    public RequestFactory() {
    }
    
    public ArrayList<Request> createRequests() {
//        System.out.println(filePath);

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
//                System.out.println(parts[0]);

                String sender = parts[0].trim();
                String status = parts[1].trim();
                Request request = new Request(sender, status);
                requests.add(request);
//                System.out.println(request);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return requests;
    }

    public boolean removeApprovedAndRejectedRequest(){
        ArrayList<Request> result = new ArrayList<>();
        for(Request r :requests){
            if(r.getStatus().equals("Pending")){
                result.add(r);
            }
        }
        requests = result;
        return setRequests();
    }

    public boolean senderInRequests(String sender){
        for(Request r : requests){
            if(r.getSender().equals(sender)){
                return true;
            }
        }
        return false;
    }

    public boolean setRequests(){
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write("");
            for (Request request : requests) {
                fileWriter.write(request.getSender() + " " + request.getStatus() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}

