package Model;

public abstract class Transaction {
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
