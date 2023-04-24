package Model;

public class Customer extends Person {
    private int ID;
    private String name;
    private String email;
    private String password;

    public Customer(int ID, String name, String email, String password) {
        super(ID,name,email,password);
    }

}
