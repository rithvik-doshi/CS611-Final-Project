package Model;

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


    //TODO write request to database
    public void writeRequestToDB() {
    }
    


    
}
