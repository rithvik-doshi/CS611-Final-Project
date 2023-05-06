package Model;

public class Customer extends Person {
    private PersonalAccount personalAccount;
    private TradingAccount tradingAccount;
    private double balance;
    
    public Customer(int ID, String name, String email, String password, double balance) {
        super(ID,name,email,password);
        this.balance = balance;
    }


    /**
    public Customer(String name, String password) {
        super(name, password, "C", generateCurrentId());
        this.id = generateCurrentId();
        this.personalAccount = new PersonalAccount(this.id);
        this.tradingAccount = null;
    }
    */

    //send request to manager to open a trading account
    public void openTradingAccount(){
        if(tradingAccount == null){
        Request request = new Request(this.getID()+"");
        request.writeRequestToDB();}

        else{
            System.out.println("You already have a trading account");
        }
    }
    

    public PersonalAccount getPersonalAccount() {
        return personalAccount;
    }

    public void setPersonalAccount(PersonalAccount personalAccount) {
        this.personalAccount = personalAccount;
    }

    public TradingAccount getTradingAccount() {
        return tradingAccount;
    }

    public void setTradingAccount(TradingAccount tradingAccount) {
        this.tradingAccount = tradingAccount;
    }

    public static String generateCurrentId(){
        return "C" + getCurrentID();
    }

    // TODO: Add a method to get the current ID from DataBase
    private static int getCurrentID(){
        return 0;
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public boolean checkTradingAccountExit() {
        return tradingAccount != null;
    }
}
