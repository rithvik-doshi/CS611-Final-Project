package Model;

public class TransactionHistory {

    private TransactionHistory(String customerID, String name, HistoryType type){
        //TODO: load history from file based on input params
    }

    public <T extends Transaction> boolean addToHistory(T t){
        //TODO: add to history
        return true;
    }

    public static TransactionHistory getTransactionHistory(String customerID, String name, HistoryType type){
        return new TransactionHistory(customerID, name, type);
    }

}
