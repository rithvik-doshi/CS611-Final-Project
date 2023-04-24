package Model;

public class Customer extends Person {
    private Account personalAccount;
    private Account tradingAccount;
    public Customer(String name, String password, String type, String id) {
        super(name, password, type, id);
        //TODO Auto-generated constructor stub
        this.type = "C";
    }


    public Customer(String name, String password) {
        this.type = "C";
        this.id = generateCurrentId();
        this.personalAccount = new Account("Personal", 0);
        this.tradingAccount = new Account("Trading", 0);
    }

    public Account getPersonalAccount() {
        return personalAccount;
    }

    public void setPersonalAccount(Account personalAccount) {
        this.personalAccount = personalAccount;
    }

    public Account getTradingAccount() {
        return tradingAccount;
    }

    public void setTradingAccount(Account tradingAccount) {
        this.tradingAccount = tradingAccount;
    }


    // TODO: Add a method to get the current ID from DataBase
    private static int getCurrentID(){
        return 0;
    }

    public static String generateCurrentId(){
        return "C" + getCurrentID();
    }
}
