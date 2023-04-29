package Model;

public class TransactionHistory {

    private TransactionHistory(){
        //TODO: load history from file based on input params
    }

    public boolean addToHistory(Transaction t){
        //TODO: add to history
        return true;
    }

    public static TransactionHistory getTransactionHistory(){
        return new TransactionHistory();
    }

}
