package Model;

public class Account{
    protected String id;
    protected String type;
    protected double balance;
    protected String ownerID;

//    For personal account init
    public Account(){}

    public Account(double balance, String ownerID){
        this.type = "Personal";
        this.balance = balance;
        this.ownerID = ownerID;
    }

    public Account(String id, String type, double balance, String ownerID) {
        this.type = type;
        this.balance = balance;
        this.ownerID = ownerID;
        this.id = id;
    }

    //For create new account    
    public Account(String type, double balance, String ownerID) {
        this.type = type;
        this.balance = balance;
        this.ownerID = ownerID;
        this.id = generateCurrentId();
    }

    public Account(String ownerID, String type) {
        this.type = type;
        this.balance = 0;
        this.id = generateCurrentId();
        this.ownerID = ownerID;
    }

    private String generateCurrentId(){
        return "A" + getCurrentID();
    }

    protected static int getCurrentID(){
        return 0;
    }


    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }



    public String getOwnerID() {
        return ownerID;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        return "ID: " + id + " Type: " + type + " Balance: " + balance + " OwnerID: " + ownerID;
    }

}
