package Model;

public class PersonalTransaction extends Transaction {
    public PersonalTransaction(String behavior, double money) {
        super(behavior, money);
    }

    public PersonalTransaction(String behavior, double money, String timestamp) {
        super(behavior, money, timestamp);
    }

    @Override
    public String[] getInfoAsArray() {
        return new String[]{this.getBehaviour(), String.valueOf(this.getMoney())};
    }

    public String toString() {
        return this.getBehaviour() + ", " + this.getMoney();
    }
}
