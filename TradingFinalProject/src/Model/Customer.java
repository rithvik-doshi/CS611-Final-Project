package Model;

public class Customer extends Person {
    private Account personalAccount;
    private Account tradingAccount;
    
    public Customer(int ID, String name, String email, String password) {
        super(ID,name,email,password);
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

    public static String generateCurrentId(){
        return "C" + getCurrentID();
    }

    // TODO: Add a method to get the current ID from DataBase
    private static int getCurrentID(){
        return 0;
    }


}
