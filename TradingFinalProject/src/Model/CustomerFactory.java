package Model;

public class CustomerFactory {
    public static Customer createCustomer(int ID, String name, String email, String password) {
        return new Customer(ID, name, email, password);
    }

}
