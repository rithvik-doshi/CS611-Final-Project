package Model;

public class PersonalAccount extends Account {

    // Read from the database
    public PersonalAccount(String id, String type, double balance, String ownerID) {
        super(id, type, balance, ownerID);
    }

    // Construct new Personal Account
    public PersonalAccount(int id){
        super();
        this.id = id;
        this.balance = 0;
    }


    public static String generateCurrentId() {
        return "A" + getCurrentID();
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public boolean withdraw(double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    public boolean transferMoney(double amount, TradingAccount account) {
        if (this.withdraw(amount)) {
            account.saveMoney(amount);
            return true;
        } else {
            return false;
        }
    }
}
