package Model;

public class PersonalTransaction extends Transaction {
    public PersonalTransaction(String behavior, double money) {
        super(behavior, money);
    }

    public String toString() {
        return this.getBehaviour() + ", " + this.getMoney();
    }
}
