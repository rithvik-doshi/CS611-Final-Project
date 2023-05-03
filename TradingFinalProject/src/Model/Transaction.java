package Model;

public abstract class Transaction {
    //Behaviour should be sell or buy, Deposit or withdraw depending on the specific transaction
    private final String behaviour;
    private final double money;

//    timestamp
    private final String timestamp;

    public Transaction(String Behaviour, double money){
        this.behaviour = Behaviour;
        this.money = money;
        this.timestamp = java.time.LocalDateTime.now().toString();
    }

    public Transaction(String Behaviour, double money, String timestamp){
        this.behaviour = Behaviour;
        this.money = money;
        this.timestamp = timestamp;
    }

    public String getBehaviour() {
        return behaviour;
    }

    public double getMoney() {
        return money;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public abstract String[] getInfoAsArray();

}
