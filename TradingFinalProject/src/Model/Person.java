package Model;


public abstract class Person {
    private int ID;
    private String name;
    private String email;
    private String password;

    public Person(int ID, String name, String email, String password){
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
//    public String toString() {
//        return "Name: " + name + " Password: " + password + " Type: " + type + " ID: " + id;
//    }

    public boolean equals(Object o) {
        if (o instanceof Person) {
            Person p = (Person) o;
            return p.getID() == ID;
        }
        return false;
    }
}
