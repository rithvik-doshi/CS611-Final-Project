package Model;
import java.io.FileWriter;
import java.io.IOException;

public class Request {
    private String sender;
    // private String receiver;
    private String status;
    private boolean isApproved;

    public Request(String sender) {
        this.sender = sender;
        this.status = "Pending";
        writeRequestToDB();
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
        this.isApproved = true;
        setApproved();
    }

    public void reject() {
        this.isApproved = false;
        setRejected();
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
        String path = "TradingFinalProject/src/Database/DBFiles/AccountCreationRequests.txt";
        String data = "\""+sender + "\" \"" + status+"\"\n";

        try {
            FileWriter fw = new FileWriter(path, true);
            fw.write(data);
            fw.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing request to DBfile.");
            e.printStackTrace();
        }
    }

}
