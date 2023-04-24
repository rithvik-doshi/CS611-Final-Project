package Model;

public class PersonalAccount extends Account{

        //read from database
        public PersonalAccount(String id, String type, double balance, String ownerID) {
        super(id, type, balance, ownerID);
        //TODO Auto-generated constructor stub
    }
        
    //create new account
    public PersonalAccount(String ownerID) {
        super(ownerID, "Personal");
        this.id = generateCurrentId();
    }

    public static String generateCurrentId(){
        return "A" + getCurrentID();
    }

    public void saveMoney(double amount){
        this.balance += amount;
    }

    public boolean withdrawMoney(double amount){
        if(this.balance >= amount){
            this.balance -= amount;
            return true;
        }else{
            return false;
        }   
    }

    public boolean transferMoney(double amount, TradingAccount account){
        if(this.withdrawMoney(amount)){
            account.saveMoney(amount);
            return true;
        }
        else{
            return false;
        }

    }




    //TODO: Add a method to get the current ID from DataBase

}
