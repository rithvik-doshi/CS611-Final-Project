package Model;

public class TradingAccount extends Account {


    double netProfit;

    public TradingAccount(String type, double balance, String ownerID) {
        super(type, balance, ownerID);
        //TODO Auto-generated constructor stub
    }
    
    public TradingAccount(String id, String type, double balance, String ownerID) {
        super(id, type, balance, ownerID);
        //TODO Auto-generated constructor stub
    }

    public TradingAccount(String ownerID) {
        super(ownerID, "Trading");
        this.id = generateCurrentId();
    }

    public static String generateCurrentId(){
        return "T" + getCurrentID();
    }
// determine if the trading account can have a derivative account by checking the net profit over 10000

    public boolean canHaveDerivativeAccount(){
        if(this.netProfit > 10000){
            return true;
        }else{
            return false;
        }
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

    public boolean transferMoney(double amount, PersonalAccount account){
        if(this.withdrawMoney(amount)){
            account.saveMoney(amount);
            return true;
        }
        else{
            return false;
        }

    }

}
