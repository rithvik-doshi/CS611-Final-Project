package Model;

public abstract class Transaction {
    //Behaviour should be sell or buy, Deposit or withdraw depending on the specific transaction
    private final String behaviour;
    private final double money;

    public Transaction(String Behaviour, double money){
        this.behaviour = Behaviour;
        this.money = money;
    }

    public String getBehaviour() {
        return behaviour;
    }

    public double getMoney() {
        return money;
    }

}
