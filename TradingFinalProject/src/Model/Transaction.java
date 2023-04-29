package Model;

public class Transaction {
    //Behaviour should be sell or buy. Deposit or withdraw
private String Behaviour;
private int quantity;

public Transaction(String Behaviour, int quantity){
    this.Behaviour = Behaviour;
    this.quantity = quantity;
}

    public String getBehaviour() {
        return Behaviour;
    }

    public void setBehaviour(String behaviour) {
        Behaviour = behaviour;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
